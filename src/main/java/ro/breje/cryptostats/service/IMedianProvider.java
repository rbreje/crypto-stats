package ro.breje.cryptostats.service;

public interface IMedianProvider {

    long size();

    void add(float number);

    float getMedian();

}
