package com.example.jwt.examplejwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.jwt.examplejwt.domain.Role;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
