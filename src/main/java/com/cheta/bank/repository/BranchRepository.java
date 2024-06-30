package com.cheta.bank.repository;

import com.cheta.bank.mysql.model.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<Branch, Integer> {
}
