
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testUpdateCustomer_ActiveCustomer_ShouldReturnTrue() {
        Customer activeCustomer = new Customer(1, "Tao", "tao.act@gmail.com", 30, true);

        boolean result = customerService.updateCustomer(activeCustomer, "Hansen", "tao.hansen@example.com", 35);

        assertTrue(result, "O cliente ativo deveria ser atualizado com sucesso.");
        assertEquals("Hansen", activeCustomer.getName(), "O nome do cliente deveria ser atualizado.");
        assertEquals("tao.hansen@example.com", activeCustomer.getEmail(), "O e-mail do cliente deveria ser atualizado.");
        assertEquals(35, activeCustomer.getAge(), "A idade do cliente deveria ser atualizada.");
    }

    @Test
    void testUpdateCustomer_InactiveCustomer_ShouldReturnFalse() {
        Customer inactiveCustomer = new Customer(2, "Tao Inativo", "inactive.tao@gmail.com", 40, false);

        boolean result = customerService.updateCustomer(inactiveCustomer, "Inactive Tao", "inactive.tao@example.com", 45);

        assertFalse(result, "O cliente inativo não deveria ser atualizado.");
        assertEquals("Tao Inativo", inactiveCustomer.getName(), "O nome do cliente não deveria ser alterado.");
        assertEquals("inactive.tao@gmail.com", inactiveCustomer.getEmail(), "O e-mail do cliente não deveria ser alterado.");
        assertEquals(40, inactiveCustomer.getAge(), "A idade do cliente não deveria ser alterada.");
    }
}