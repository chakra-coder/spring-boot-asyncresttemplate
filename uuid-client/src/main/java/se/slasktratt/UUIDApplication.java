package se.slasktratt;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class UUIDApplication {

    public static void main(String[] args) {
        SpringApplication.run(UUIDApplication.class, args);
    }
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    RibbonLoadBalancerClient ribbonClient;
    
    @RequestMapping("/")
    public String uuid() throws Exception {
    	URI server = ribbonClient.choose("uuid-service").getUri();
    	AsyncRestTemplate template = new AsyncRestTemplate();
    	return "From uuid-service: " + server + " : " + template.getForEntity(server +"/uuid", UUID.class).get().getBody().toString();
    }
    
    @RequestMapping("/choose")
    public String getServices() {
    	return ribbonClient.choose("uuid-service").getUri().toString();
    }
}
