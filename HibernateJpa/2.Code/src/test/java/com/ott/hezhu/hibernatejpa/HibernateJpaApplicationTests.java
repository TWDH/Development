package com.ott.hezhu.hibernatejpa;

import com.ott.hezhu.hibernatejpa.dao.CustomerRepository;
import com.ott.hezhu.hibernatejpa.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SpringBootTest
class HibernateJpaApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void contextLoads() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("firstName"), "王");
                return predicate;
            }
        };
    }

}
