I had some problems using Eureka lookips with Spring's AsyncRestTemplate. 

The normal approach where the url http://service/uuid is automatically expanded into http://some.server:8080/uuid didn't work. 

I never figured out why. It seems that the normal RestTemplate is 'load balancer aware' but the AsyncRestTemplate, which uses RestTemplate, is *not* load balancer aware. 

To try this project, build all three projects and then star them in the following order

* eureka-serice
* uuid-service (a few instances perhaps? With --server.port=XYZXYZ)
* uuid-client

Then visit http://localhost:8080 and http://localhost:8080/choose
