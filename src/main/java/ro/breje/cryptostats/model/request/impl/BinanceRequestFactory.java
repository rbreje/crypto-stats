package ro.breje.cryptostats.model.request.impl;

import ro.breje.cryptostats.model.request.RequestFactory;
import ro.breje.cryptostats.model.request.SubscribeRequest;

import java.util.List;
import java.util.stream.Collectors;

public class BinanceRequestFactory implements RequestFactory {

    public static final String SUBSCRIBE_METHOD = "SUBSCRIBE";

    public static int LATEST_ID = 0;

    @Override
    public SubscribeRequest getSubscribeRequest(List<String> symbols) {
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setMethod(SUBSCRIBE_METHOD);

        subscribeRequest.setParams(symbols.stream()
                                          .map(s -> s.toLowerCase() + "@trade")
                                          .collect(Collectors.toList())
                                          .toArray(String[]::new));

        subscribeRequest.setId(++LATEST_ID);
        return subscribeRequest;
    }

    // TODO check if can unsubscribe before closing application
    @Override
    public SubscribeRequest getUnsubscribeRequest() {
        throw new UnsupportedOperationException("This method is not supported!");
    }

}
