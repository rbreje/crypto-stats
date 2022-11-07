package ro.breje.cryptostats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ro.breje.cryptostats.model.response.BinanceAllSymbolsResponse;

import java.time.Duration;

@Controller
public class BinanceDataController {

    @Autowired
    private WebClient webClient;

    public Mono<BinanceAllSymbolsResponse> getAllSymbols() {
        return webClient.get()
                        .uri("/api/v3/exchangeInfo")
                        .retrieve()
                        .bodyToMono(BinanceAllSymbolsResponse.class)
                        .timeout(Duration.ofSeconds(10));
    }

}
