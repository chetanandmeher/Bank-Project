package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
