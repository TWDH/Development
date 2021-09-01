package com.ott.hezhu.hibernatejpa;

import com.ott.hezhu.hibernatejpa.dao.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateJpaApplicationTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void contextLoads() {
        customerRepository.findall;
    }

}
