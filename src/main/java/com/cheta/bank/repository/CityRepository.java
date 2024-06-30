package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Integer> {
    public Optional<City> getCityByDistrictId(Integer districtId);
}
