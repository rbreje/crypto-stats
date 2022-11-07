package ro.breje.cryptostats.model.response;

public class SymbolDataResponse {

    private String symbol;

    private String appearances;

    private String medianPrice;

    private String latestPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAppearances() {
        return appearances;
    }

    public void setAppearances(String appearances) {
        this.appearances = appearances;
    }

    public String getMedianPrice() {
        return medianPrice;
    }

    public void setMedianPrice(String medianPrice) {
        this.medianPrice = medianPrice;
    }

    public String getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(String latestPrice) {
        this.latestPrice = latestPrice;
    }
}
