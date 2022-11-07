package ro.breje.cryptostats.model;

import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("e")
    private String eventType;

    @SerializedName("E")
    private long eventTime;

    @SerializedName("s")
    private String symbol;

    @SerializedName("t")
    private long tradeId;

    @SerializedName("p")
    private String price;

    @SerializedName("q")
    private String quantity;

    @SerializedName("b")
    private long buyerOrderId;

    @SerializedName("a")
    private long sellerOrderId;

    @SerializedName("T")
    private long tradeTime;

    @SerializedName("m")
    private boolean isMarketMaker;

    @SerializedName("M")
    private boolean ignore;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public long getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(long buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }

    public long getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(long sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public boolean isMarketMaker() {
        return isMarketMaker;
    }

    public void setMarketMaker(boolean marketMaker) {
        isMarketMaker = marketMaker;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }
}
