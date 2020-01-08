package repositories;

import com.example.webshop.models.Customer;
import com.example.webshop.repositories.CustomerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockCustomerRepository implements CustomerRepository {
    Customer customer = new Customer();
    public MockCustomerRepository() {
        customer.setId(1);
        customer.setName("1");
        customer.setMail("1");
        customer.setPassword("1");
    }

    @Override
    public Optional<Customer> findByNameAndPassword(String name, String password) {
        return Optional.of(customer);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Customer> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
         customer = null;
    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void deleteAll(Iterable<? extends Customer> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Customer> S save(S s) {
        return null;
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer integer) {
        return Optional.of(customer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Customer> S saveAndFlush(S s) {
        return (S) customer;
    }

    @Override
    public void deleteInBatch(Iterable<Customer> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Customer getOne(Integer integer) {
        return customer;
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return false;
    }
}
