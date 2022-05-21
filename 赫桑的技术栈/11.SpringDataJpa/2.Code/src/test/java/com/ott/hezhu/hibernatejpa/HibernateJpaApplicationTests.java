package com.ott.hezhu.hibernatejpa;

import com.ott.hezhu.hibernatejpa.dao.CustomerRepository;
import com.ott.hezhu.hibernatejpa.entity.Customer;
import com.ott.hezhu.hibernatejpa.entity.Role;
import com.ott.hezhu.hibernatejpa.entity.User;
import com.ott.hezhu.hibernatejpa.repository.RoleRepository;
import com.ott.hezhu.hibernatejpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HibernateJpaApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSave() {

        User user = new User();
        user.setUserName("雨听风说");


        Role role = new Role();
        role.setRoleName("程序猿");

        /*
         表达多对多的关系
          */
        role.getUsers().add(user);

        user.getRoles().add(role);

        //级联操作，在保存用户的同时保存用户的类型
        this.userRepository.save(user);

    }

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
