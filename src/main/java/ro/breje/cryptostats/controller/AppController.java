package ro.breje.cryptostats.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ro.breje.cryptostats.exceptions.ConnectionException;
import ro.breje.cryptostats.exceptions.ControllerException;
import ro.breje.cryptostats.exceptions.SubscribeException;
import ro.breje.cryptostats.model.BinanceSymbol;
import ro.breje.cryptostats.repository.AppRepository;
import ro.breje.cryptostats.service.IDataRetrieverManager;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Controller
public class AppController {

    @Autowired
    private IDataRetrieverManager binanceDataRetrieverService;

    @Autowired
    private BinanceDataController binanceDataController;

    @Autowired
    private AppRepository appRepository;

    public void init() throws ControllerException {
        try {
            List<BinanceSymbol> binanceSymbols = binanceDataController.getAllSymbols()
                                                                      .block()
                                                                      .getSymbols();

            binanceSymbols.forEach(s -> appRepository.addSymbol(s));

            for (List<BinanceSymbol> batch : Lists.partition(binanceSymbols, 100)) {
                String connectionId = binanceDataRetrieverService.connect(getAggregatedEndpoint(batch));
                binanceDataRetrieverService.subscribe(connectionId, batch.stream()
                                                                         .map(BinanceSymbol::getAsStreamName)
                                                                         .collect(Collectors.toList()));
            }
        } catch (ConnectionException | SubscribeException e) {
            throw new ControllerException("Error occurred when retrieving the data from exchange!", e);
        }
    }

    private String getAggregatedEndpoint(List<BinanceSymbol> binanceSymbols) {
        StringJoiner sj = new StringJoiner("/");
        binanceSymbols.forEach(symbol -> sj.add(symbol.getAsStreamName()));
        return sj.toString();
    }

}
