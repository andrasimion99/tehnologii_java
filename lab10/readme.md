# Lab10

In lab10 project I refactored the project from the previous lab and added MicroProfile Resilient Api, MicroProfile Metrics api and the HealthCheck.

I deployed the microservice on the docker, in the last lab.(lab9)

In the DocumentController I added healthcheck support implementing the HealthCheck interface and with the help of Readiness and Liveness annotations. These help us to check if our microservice is available for making requests.

Also, in the DocumentController I added monitoring for the ViewDocumentsService method which returns all the documents. Now, when we go to the metrics page we can see the number of invocations and the response time for this method since the server is up and running.

For the resilient api I've created a separate controller to test the scenarios: ResilienceController. Here we have the following methods:
- checkTimeout, implementing the timeout fallback procedure. The fallback method is called when the timeout is reached.
- retryTimeout, implementing the timeout fallback procedure with retries. A retry will be made after the TimeoutException is thrown and this exception will be thrown after the timeout is reached and the fallback method will be called then.
- checkCircuit, which called the method() which somethimes fails. After it failed once, for the next 5 seconds, the method will not be called anymore as the circuit will be on and will consider that the method is throwing exception.
- checkConcurrentRequests, which implements the Bulkhead procedure with the semaphore implementation and limits the number of concurrent requests to 2
- checkConcurrentRequestsAsync, which implements the Bulkhead procedure with the thread pool implementation and allows some requests in the waiting queue
