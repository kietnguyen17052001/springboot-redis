package com.learn.springbootredis.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("customer")
    public class Customer implements Serializable {
    @Id
    private Long id;
    private String name;
    private int age;
    private String country;
    private String city;
}
