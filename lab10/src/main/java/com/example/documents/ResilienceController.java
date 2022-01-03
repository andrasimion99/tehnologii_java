package com.example.documents;

import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;

@Path("/resilience")
@ApplicationScoped
public class ResilienceController {
    @Fallback(fallbackMethod = "fallback")
    @Timeout(500)
    @GET
    @Path("timeout")
    public String checkTimeout() {
        try {
            Thread.sleep(700L);
        } catch (InterruptedException e) {
            //
        }
        return "Never from normal processing";
    }

    @GET
    @Path("retry")
    @Fallback(fallbackMethod = "fallback")
    @Retry(retryOn = TimeoutException.class, maxRetries = 2, delay = 200, jitter = 50)
    @Timeout(500)
    public String retryTimeout() throws TimeoutException, InterruptedException {
        System.out.println("trying...");
        Thread.sleep(3000);
        return "Never from normal processing";
    }

    @Fallback(fallbackMethod = "fallback")
    @Timeout(500)
    @GET
    @Path("circuit")
    @CircuitBreaker(requestVolumeThreshold = 2, failureRatio = 0.5, delay = 5000)
    public String checkCircuit() {
        method();
        return "Successfully called";
    }

    @GET
    @Path("semaphore")
    @Bulkhead(value = 2)
    @Fallback(fallbackMethod = "fallback")
    public String checkConcurrentRequests() throws InterruptedException {
        System.out.println("Called checkConcurrentRequests endpoint");
        Thread.sleep(10000);
        System.out.println("Finish the call");
        return "Successfully called";
    }

    @GET
    @Path("bulkhead")
    @Bulkhead(value = 2, waitingTaskQueue = 2)
    @Asynchronous
    @Fallback(fallbackMethod = "fallbackAsync")
    public CompletionStage<String> checkConcurrentRequestsAsync() {
        CompletableFuture<String> future = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("Called checkConcurrentRequests endpoint");
            Thread.sleep(10000);
            System.out.println("Finish the call");
            future.complete("Successfully called");
            return null;
        });
        return future;
    }

    public String fallback() {
        return "Fallback answer due to timeout";
    }

    public CompletionStage<String> fallbackAsync() {
        return CompletableFuture.completedFuture("Fallback answer due to timeout");
    }

    private void method() {
        System.out.println("trying to execute de method");
        Random rand = new Random();
        try {
            if (rand.nextInt(10) > 2) {
                Thread.sleep(700L); //it will timeout with a probability of 0.8%
            }
        } catch (InterruptedException e) {
            //
        }
    }
}
