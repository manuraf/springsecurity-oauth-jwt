package com.example.jwt.examplejwt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.jwt.examplejwt.domain.User;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface UserRepository extends MongoRepository<User, String> { //CrudRepository<User, Long> {
    User findByUsername(String username);
}
