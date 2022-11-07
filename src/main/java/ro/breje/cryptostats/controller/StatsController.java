package ro.breje.cryptostats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ro.breje.cryptostats.model.BinanceSymbol;
import ro.breje.cryptostats.model.response.SymbolDataResponse;
import ro.breje.cryptostats.repository.AppRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StatsController {

    @Autowired
    private AppRepository appRepository;

    @RequestMapping(value = "/symbols", method = RequestMethod.GET)
    public List<BinanceSymbol> getSymbols() {
        List<String> rawSymbols = appRepository.getAllSymbols();

        List<BinanceSymbol> symbols = rawSymbols.stream()
                                                .map(rs -> new BinanceSymbol(rs.substring(0, rs.length() - 9)))
                                                .collect(Collectors.toList());

        return symbols;
    }

    @RequestMapping(value = "/symbols/{symbolId}", method = RequestMethod.GET)
    public SymbolDataResponse getSymbol(@PathVariable(value = "symbolId") String symbolId) {
        if (!appRepository.exists(symbolId.toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Symbol Not Found");
        }
        try {
            return appRepository.getSymbolData(symbolId.toLowerCase());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

}
