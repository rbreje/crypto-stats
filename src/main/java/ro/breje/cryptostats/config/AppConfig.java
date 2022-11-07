package ro.breje.cryptostats.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import ro.breje.cryptostats.model.SimpleDictionary;
import ro.breje.cryptostats.model.impl.SimpleHashDictionary;
import ro.breje.cryptostats.model.request.RequestFactory;
import ro.breje.cryptostats.model.request.impl.BinanceRequestFactory;
import ro.breje.cryptostats.service.IMedianProvider;
import ro.breje.cryptostats.service.helper.PayloadMessageHandler;
import ro.breje.cryptostats.service.helper.WebSocketClientEndpoint;
import ro.breje.cryptostats.service.impl.QueuesMedianProvider;

import javax.websocket.ContainerProvider;
import javax.websocket.MessageHandler;
import javax.websocket.WebSocketContainer;
import java.util.Set;
import java.util.TreeSet;

@Configuration
public class AppConfig {

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                        .exchangeStrategies(ExchangeStrategies.builder()
                                                              .codecs(configurer -> configurer
                                                                      .defaultCodecs()
                                                                      .maxInMemorySize(16 * 1024 * 1024))
                                                              .build())
                        .baseUrl("https://api.binance.com")
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();
    }

    @Bean
    public WebSocketContainer getWebSocketContainer() {
        return ContainerProvider.getWebSocketContainer();
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public RequestFactory getRequestFactory() {
        return new BinanceRequestFactory();
    }

    @Bean
    @Scope("prototype")
    public WebSocketClientEndpoint getWebSocketClientEndpoint() {
        return new WebSocketClientEndpoint();
    }

    @Bean
    @Scope("prototype")
    public MessageHandler.Whole<String> getMessageHandler() {
        return new PayloadMessageHandler();
    }

    @Bean
    @Scope("prototype")
    public SimpleDictionary getBeMap() {
        return new SimpleHashDictionary();
    }

    @Bean
    public Set<String> getSymbolsSet() {
        return new TreeSet<>();
    }

    @Bean
    @Scope("prototype")
    public IMedianProvider getMedianProvider() {
        return new QueuesMedianProvider();
    }
}
