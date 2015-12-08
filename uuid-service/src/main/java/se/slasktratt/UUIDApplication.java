package se.slasktratt;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class UUIDApplication {

    public static void main(String[] args) {
        SpringApplication.run(UUIDApplication.class, args);
    }
    
    @RequestMapping("/uuid")
    public UUID uuid() {
    	return UUID.randomUUID();
    }
}
