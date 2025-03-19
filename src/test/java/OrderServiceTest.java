import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private PaymentProcessor paymentProcessorMock;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        paymentProcessorMock = Mockito.mock(PaymentProcessor.class);
        orderService = new OrderService(paymentProcessorMock);
    }

    @Test
    void testProcessOrder_SuccessfulPayment() {
        when(paymentProcessorMock.processPayment(100.0)).thenReturn(true);

        boolean result = orderService.processOrder(100.0);

        assertTrue(result, "O pedido deveria ser confirmado.");
        verify(paymentProcessorMock, times(1)).processPayment(100.0);
    }

    @Test
    void testProcessOrder_FailedPayment() {
        when(paymentProcessorMock.processPayment(200.0)).thenReturn(false);

        boolean result = orderService.processOrder(200.0);
        assertFalse(result, "O pedido deveria ser recusado.");
        verify(paymentProcessorMock, times(1)).processPayment(200.0);
    }

}
