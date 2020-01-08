package repositories;

import com.example.webshop.models.Article;
import com.example.webshop.models.Order;
import com.example.webshop.models.OrderLine;
import com.example.webshop.repositories.OrderLineRepository;
import com.example.webshop.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockOrderLineRepository implements OrderLineRepository {
    OrderLine orderLine = new OrderLine();
    Order order = new Order();
    Article article = new Article();


    public MockOrderLineRepository() {
        article.setName("1");
        article.setId(1);
        article.setPrice(1);
        order.setId(1);
        orderLine.setId(1);
        orderLine.setOrder(order);
        orderLine.setArticle(article);
    }

    @Override
    public List<OrderLine> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<OrderLine> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<OrderLine> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderLine> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        orderLine  = null;

    }

    @Override
    public void delete(OrderLine orderLine) {

    }

    @Override
    public void deleteAll(Iterable<? extends OrderLine> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends OrderLine> S save(S s) {
        return null;
    }

    @Override
    public <S extends OrderLine> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<OrderLine> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends OrderLine> S saveAndFlush(S s) {
        return (S) orderLine;
    }

    @Override
    public void deleteInBatch(Iterable<OrderLine> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public OrderLine getOne(Integer integer) {
        return orderLine;
    }

    @Override
    public <S extends OrderLine> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends OrderLine> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends OrderLine> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends OrderLine> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends OrderLine> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends OrderLine> boolean exists(Example<S> example) {
        return false;
    }
}
