package com.learn.springbootredis.controller;

import com.learn.springbootredis.entity.Customer;
import com.learn.springbootredis.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(service.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getCustomer(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.create(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.update(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> getCustomers(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }
}
