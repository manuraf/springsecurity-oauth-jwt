package com.example.jwt.examplejwt.service;

import java.util.List;

import com.example.jwt.examplejwt.domain.User;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

//    List<RandomCity> findAllRandomCities();
}
