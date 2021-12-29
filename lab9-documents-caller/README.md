# Lab9

This is a simple project which represents a microservice deployed with Open Liberty which makes calls to the documents microservice. 

First of all, I created a class Document, identical to the entity from the microservice so that I can map the result I get from the call. Then I created an interface Service and with the @RegisterRestClient annotation I registered the url of the document microservice. In the HelloController I exposed a new endpoint which will call the microservice through the Service interface injection with @RestClient annotation.