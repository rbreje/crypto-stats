package ro.breje.cryptostats.model.response;

import ro.breje.cryptostats.model.BinanceSymbol;

import java.util.List;

public class BinanceAllSymbolsResponse {

    private List<BinanceSymbol> symbols;

    public List<BinanceSymbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<BinanceSymbol> symbols) {
        this.symbols = symbols;
    }
}
