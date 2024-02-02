package com.multithreading.backend.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class MyserviceTest {

    @Mock
     Myservice myservice;

    @Test
    public void testPerformTaskAsync() throws ExecutionException, InterruptedException {
        // Mocking the async service response
        when(myservice.performTaskAsync()).thenReturn(CompletableFuture.completedFuture("Mocked response"));

        // Calling the method under test
        CompletableFuture<String> future = myservice.performTaskAsync();
        String result = future.get();
        assertEquals("Mocked response", result);
    }


    @Test
    public void testPerformTaskAsync_Timeout() throws InterruptedException, ExecutionException {
        // Create a mock instance of MyAsyncService
        Myservice Service = mock(Myservice.class);
        CompletableFuture<String> future = new CompletableFuture<>();
        when(Service.performTaskAsync()).thenReturn(future);
        CompletableFuture<String> resultFuture = Service.performTaskAsync();
        assertThrows(TimeoutException.class, () -> resultFuture.get(1, TimeUnit.SECONDS));
    }
}
