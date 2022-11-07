package ro.breje.cryptostats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BinanceSymbol {

    private String symbol;

    public BinanceSymbol() {
    }

    public BinanceSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonIgnore
    public String getAsStreamName() {
        return symbol.toLowerCase() + "@aggTrade";
    }

    @Override
    public String toString() {
        return getAsStreamName();
    }
}
