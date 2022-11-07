package ro.breje.cryptostats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.breje.cryptostats.model.BinanceSymbol;

import java.util.List;

@RestController
@RequestMapping("/internal-api")
public class InternalController {

    @Autowired
    private BinanceDataController dataController;

    @RequestMapping(value = "/symbols", method = RequestMethod.GET)
    public List<BinanceSymbol> getSymbols() {
        return dataController.getAllSymbols()
                             .block()
                             .getSymbols();
    }

}
