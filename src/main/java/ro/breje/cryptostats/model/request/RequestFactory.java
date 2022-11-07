package ro.breje.cryptostats.model.request;

import java.util.List;

public interface RequestFactory {

    SubscribeRequest getSubscribeRequest(List<String> symbols);

    SubscribeRequest getUnsubscribeRequest();
}
