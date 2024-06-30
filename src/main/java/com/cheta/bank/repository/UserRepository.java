package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
