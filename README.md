I had some problems using Eureka lookups with Spring's AsyncRestTemplate. 

The normal approach where the url http://service/uuid is automatically expanded into http://some.server:8080/uuid didn't work. 

I never figured out why. It seems that the normal RestTemplate is 'load balancer aware' but the AsyncRestTemplate, which uses RestTemplate, is *not* load balancer aware. 

To try this project, build all three projects and then start them in the following order

* eureka-serice
* uuid-service (a few instances perhaps? With --server.port=XYZXYZ)
* uuid-client

Then visit http://localhost:8080 and http://localhost:8080/choose

Basically what the code does it to autowire a RibbonLoadBalancerClient and takes care of the load balancing itself. Not too pretty, but it works. How would one go about trying to back off from servers that are down before they are removed from Eureka? Bad idea perhaps? Maybe lower the refresh intervals in Eureka instead....
