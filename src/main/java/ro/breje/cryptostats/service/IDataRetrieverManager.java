package ro.breje.cryptostats.service;

import ro.breje.cryptostats.exceptions.ConnectionException;
import ro.breje.cryptostats.exceptions.SubscribeException;

import java.util.List;

public interface IDataRetrieverManager {

    String connect(String symbols) throws ConnectionException;

    void subscribe(String webSockerClientId, List<String> symbols) throws SubscribeException;

}
