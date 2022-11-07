package ro.breje.cryptostats.model.response;

import ro.breje.cryptostats.model.Payload;

public class TradeDataResponse {

    private String stream;

    private Payload data;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Payload getData() {
        return data;
    }

    public void setData(Payload data) {
        this.data = data;
    }
}
