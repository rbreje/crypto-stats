package ro.breje.cryptostats.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import ro.breje.cryptostats.model.SimpleDictionary;
import ro.breje.cryptostats.model.BinanceSymbol;
import ro.breje.cryptostats.model.Payload;
import ro.breje.cryptostats.model.response.SymbolDataResponse;
import ro.breje.cryptostats.model.response.TradeDataResponse;
import ro.breje.cryptostats.service.IMedianProvider;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AppRepository {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private SimpleDictionary<String> symbolsMedian;

    @Autowired
    private SimpleDictionary<String> symbolLatestPrice;

    @Autowired
    private Set<String> symbolsSet;

    @Autowired
    private SimpleDictionary<IMedianProvider> medianServiceMap;

    public List<String> getAllSymbols() {
        return symbolsSet.stream().collect(Collectors.toList());
    }

    public boolean exists(String symbolId) {
        return symbolsSet.contains(symbolId.toLowerCase() + "@aggTrade");
    }

    public void addSymbol(BinanceSymbol symbol) {
        symbolsSet.add(symbol.getSymbol().toLowerCase() + "@aggTrade");
    }

    public synchronized void processData(TradeDataResponse dataResponse) {
        try {
            String name = dataResponse.getStream();
            Payload payload = dataResponse.getData();

            if (Objects.nonNull(payload)) {
                symbolLatestPrice.put(name, payload.getPrice());

                float floatedPrice = Float.parseFloat(payload.getPrice());
                IMedianProvider medianProvider = medianServiceMap.get(name);

                if (Objects.nonNull(medianProvider)) {
                    medianProvider.add(floatedPrice);
                } else {
                    medianProvider = context.getBean(IMedianProvider.class);
                    medianProvider.add(floatedPrice);
                    medianServiceMap.put(name, medianProvider);
                }

                symbolsMedian.put(name, String.valueOf(medianProvider.getMedian()));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public SymbolDataResponse getSymbolData(String symbolId) {
        SymbolDataResponse symbolDataResponse = new SymbolDataResponse();
        symbolDataResponse.setSymbol(symbolId);
        symbolDataResponse.setMedianPrice(symbolsMedian.get(symbolId + "@aggTrade"));
        symbolDataResponse.setLatestPrice(symbolLatestPrice.get(symbolId + "@aggTrade"));
        IMedianProvider medianProvider = medianServiceMap.get(symbolId + "@aggTrade");
        if (Objects.nonNull(medianProvider)) {
            symbolDataResponse.setAppearances(String.valueOf(medianProvider.size()));
        }
        return symbolDataResponse;
    }

}
