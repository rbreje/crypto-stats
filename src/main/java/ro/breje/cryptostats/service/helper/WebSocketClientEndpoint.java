package ro.breje.cryptostats.service.helper;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import ro.breje.cryptostats.model.request.SubscribeRequest;

import javax.websocket.*;
import java.io.IOException;

@ClientEndpoint
public class WebSocketClientEndpoint extends Endpoint {

    private Session session;

    @Autowired
    private Gson gson;

    @Autowired
    private MessageHandler.Whole<String> messageHandler;

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        // FIXME this method is not working as expected
        this.session = session;
        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                System.out.println("Received: " + message);
            }
        });
    }

    public void setSession(Session session) {
        this.session = session;
        this.session.addMessageHandler(messageHandler);
    }

    public void subscribe(SubscribeRequest subscribeRequest) throws IOException {
        session.getBasicRemote()
               .sendText(gson.toJson(subscribeRequest));
    }
}
