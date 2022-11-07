package ro.breje.cryptostats.service.helper;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import ro.breje.cryptostats.model.response.TradeDataResponse;
import ro.breje.cryptostats.repository.AppRepository;

import javax.websocket.MessageHandler;

public class PayloadMessageHandler implements MessageHandler.Whole<String> {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private Gson gson;

    @Override
    public void onMessage(String message) {
        TradeDataResponse dataResponse = gson.fromJson(message, TradeDataResponse.class);
        appRepository.processData(dataResponse);
    }

}
