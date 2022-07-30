package com.learn.springbootredis.repository;

import com.learn.springbootredis.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepo {
    private static final String HASH_KEY = "customer";
    private final RedisTemplate template;

    public Customer save(Customer customer) {
        template.opsForHash().put(HASH_KEY, customer.getId(), customer);
        return customer;
    }

    public List<Customer> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Customer findById(Long id) {
        return (Customer) template.opsForHash().get(HASH_KEY, id);
    }

    public Customer update(Customer customer) {
        template.opsForHash().put(HASH_KEY, customer.getId(), customer);
        return customer;
    }

    public void deleteById(Long id) {
        template.opsForHash().delete(HASH_KEY, id);
    }
}
