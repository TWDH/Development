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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HibernateJpaApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void contextLoads() {

        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };

        Specification<Customer> tSpecification = Specification.where(specification);

    }


    @Test
    void testMap() {

        List<String> testList = new ArrayList<>();
        testList.add("apple");
        testList.add("banana");

        Map<Integer, List<String>> map = new HashMap<>();
        for(String s: testList){
            List<String> stringList = map.get(1);
            if (stringList == null) {
                stringList = new ArrayList<>();
            }
            stringList.add(s);
            map.put(1, stringList);
        }
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

}
