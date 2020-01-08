package services;


import com.example.webshop.models.Article;
import com.example.webshop.models.Customer;
import com.example.webshop.services.CustomerService;
import com.example.webshop.services.OrderService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.*;
import repositories.MockCustomerRepository;
import repositories.MockOrderLineRepository;
import repositories.MockOrderRepository;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerServiceTest {
    private CustomerService customerService;

    @Before
    public void setup() {
        OrderService orderService = new OrderService();
        customerService = new CustomerService();
        orderService.setOrderRepository(new MockOrderRepository());
        customerService.setRepositoriesAndServices(new MockCustomerRepository(), new MockOrderRepository(),
                new MockOrderLineRepository(), orderService);
    }


    @Test
    public void getOneCustomerTest() {
        Optional<Customer> customer = customerService.getOne(1);
        if (customer.isPresent()) {
            Assert.assertEquals(1, customer.get().getId());

        } else Assert.assertFalse(customer.isPresent());

    }

    @Test
    public void LoginCustomerTest() {
        Customer customer = customerService.login("1", "1");
        Assert.assertEquals(1, customer.getId());
    }

    @Test
    public void checkoutTest() {
        Article article1 = new Article();
        Article article2 = new Article();
        ArrayList<Article> articleArrayList = new ArrayList<>();
        articleArrayList.add(article1);
        articleArrayList.add(article2);
        Assert.assertTrue(customerService.checkout(1, articleArrayList));
    }

    @Test
    public void getAllCustomersTest() {
        Assert.assertEquals(customerService.getAll(), new ArrayList<>());
    }
}

