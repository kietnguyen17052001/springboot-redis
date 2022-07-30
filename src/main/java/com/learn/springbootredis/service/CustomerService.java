package com.learn.springbootredis.service;

import com.learn.springbootredis.entity.Customer;
import com.learn.springbootredis.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;

    public Customer create(Customer customer) {
        return repo.save(customer);
    }

    public Customer update(Customer customer) {
        return repo.update(customer);
    }

    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    public Customer getCustomer(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
