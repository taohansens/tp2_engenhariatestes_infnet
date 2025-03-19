
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {
    private final CustomerService customerService = new CustomerService();

    @Test
    public void testRegisterCustomer_WithMinimumValidAge_ShouldReturnTrue() {
        Customer customer = new Customer(1, "Alexandre", "ale@email.com", 18, true);
        assertTrue(customerService.registerCustomer(customer),
                "O cadastro deveria ser permitido para idade mínima válida (18).");
    }

    @Test
    public void testRegisterCustomer_WithMaximumValidAge_ShouldReturnTrue() {
        Customer customer = new Customer(2, "Tao", "tao@tao.com", 99, true);
        assertTrue(customerService.registerCustomer(customer),
                "O cadastro deveria ser permitido para idade máxima válida (99).");
    }

    @Test
    public void testRegisterCustomer_WithBelowMinimumAge_ShouldReturnFalse() {
        Customer customer = new Customer(3, "João", "joaao@gmail.com", 17, true);
        assertFalse(customerService.registerCustomer(customer),
                "O cadastro não deveria ser permitido para idade abaixo do mínimo (17).");
    }


    @Test
    public void testRegisterCustomer_WithAboveMaximumAge_ShouldReturnFalse() {
        Customer customer = new Customer(4, "Francisco", "fraanc@gmail.com", 100, true);
        assertFalse(customerService.registerCustomer(customer),
                "O cadastro não deveria ser permitido para idade acima do máximo (100).");
    }
}