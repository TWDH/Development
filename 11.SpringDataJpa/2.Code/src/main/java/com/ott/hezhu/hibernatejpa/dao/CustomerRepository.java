package com.ott.hezhu.hibernatejpa.dao;

import com.ott.hezhu.hibernatejpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends JpaSpecificationExecutor<Customer>,JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    // This is Comment
    List<Customer> findByFirstNameIs(String firstName);

    Customer findById(long id);


}
