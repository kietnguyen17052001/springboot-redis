package com.learn.springbootredis.service;

import com.learn.springbootredis.entity.Customer;
import com.learn.springbootredis.exception.NotFoundException;
import com.learn.springbootredis.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;

    private void waitSomeTime() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @CacheEvict(value = "customer", allEntries = true)
    public Customer create(Customer customer) {
        return repo.save(customer);
    }

    @CachePut(value = "customer", key = "#customer.id")
    public Customer update(Customer customer) {
        return repo.save(customer);
    }

    @Cacheable(value = "customer")
    public List<Customer> getCustomers() {
        waitSomeTime();
        return repo.findAll();
    }

    @Cacheable(value = "customer", key = "#id", unless = "#result == null")
    public Customer getCustomer(Long id) {
        waitSomeTime();
        return repo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found customer");
        });
    }

    @Caching(evict = { @CacheEvict(value = "customer", key = "#id"),
            @CacheEvict(value = "customer", allEntries = true) })
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
