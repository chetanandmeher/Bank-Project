package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.District;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DistrictRepository extends CrudRepository<District, Integer> {
    public Optional<District> getDistrictByStateId(Integer stateId);
}
