package ro.breje.cryptostats.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ro.breje.cryptostats.exceptions.ConnectionException;
import ro.breje.cryptostats.exceptions.SubscribeException;
import ro.breje.cryptostats.model.request.RequestFactory;
import ro.breje.cryptostats.model.request.SubscribeRequest;
import ro.breje.cryptostats.service.IDataRetrieverManager;
import ro.breje.cryptostats.service.helper.WebSocketClientEndpoint;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class BinanceDataRetrieverManager implements IDataRetrieverManager {

    private static final String BINANCE_WEBSOCKET_STREAM = "wss://stream.binance.com:9443/stream?streams=";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private RequestFactory requestFactory;

    @Autowired
    private WebSocketContainer container;

    private Map<String, WebSocketClientEndpoint> webSocketClientsPool = new HashMap<>();

    @Override
    public String connect(String symbolsEndpoint) throws ConnectionException {
        String webSocketId = UUID.randomUUID().toString();
        try {
            WebSocketClientEndpoint webSocketClient = context.getBean(WebSocketClientEndpoint.class);

            Session session = container.connectToServer(webSocketClient, new URI(BINANCE_WEBSOCKET_STREAM + symbolsEndpoint));
            webSocketClient.setSession(session);

            webSocketClientsPool.put(webSocketId, webSocketClient);
        } catch (DeploymentException | IOException | URISyntaxException e) {
            throw new ConnectionException("Error occurred when trying to connect!", e);
        }
        return webSocketId;
    }

    @Override
    public void subscribe(String webSocketClientId, List<String> symbols) throws SubscribeException {
        WebSocketClientEndpoint webSocketClientEndpoint = webSocketClientsPool.get(webSocketClientId);

        SubscribeRequest subscribeRequest = requestFactory.getSubscribeRequest(symbols);
        try {
            webSocketClientEndpoint.subscribe(subscribeRequest);
        } catch (IOException e) {
            throw new SubscribeException("Error occurred when subscribing to compound stream!", e);
        }
    }

}
