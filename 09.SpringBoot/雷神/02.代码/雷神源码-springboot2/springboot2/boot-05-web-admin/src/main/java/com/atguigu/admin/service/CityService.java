package com.atguigu.admin.service;

import com.atguigu.admin.bean.City;
import com.atguigu.admin.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface CityService {

     City getById(Long id);

     void saveCity(City city);

}
