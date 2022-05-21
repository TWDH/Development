package ca.ott.saas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayMain {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain.class, args);
    }
}
