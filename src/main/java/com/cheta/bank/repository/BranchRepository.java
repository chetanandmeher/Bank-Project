package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Integer> {
    Branch findByName( String name);
}
