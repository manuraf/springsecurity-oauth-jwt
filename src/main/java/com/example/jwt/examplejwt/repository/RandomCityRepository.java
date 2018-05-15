package com.example.jwt.examplejwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.jwt.examplejwt.domain.RandomCity;

/**
 * Created by nydiarra on 10/05/17.
 */
public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {
}
