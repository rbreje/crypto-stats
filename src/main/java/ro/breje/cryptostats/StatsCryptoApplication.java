package ro.breje.cryptostats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ro.breje.cryptostats.controller.AppController;
import ro.breje.cryptostats.exceptions.ControllerException;

@SpringBootApplication
public class StatsCryptoApplication {

    @Autowired
    private AppController appController;

    public static void main(String[] args) {
        SpringApplication.run(StatsCryptoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void postInit() throws ControllerException {
        appController.init();
    }
}
