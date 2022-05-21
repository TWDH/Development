package com.atguigu.securitydemo1.service;

import com.atguigu.securitydemo1.entity.Users;
import com.atguigu.securitydemo1.mapper.UsersMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询数据库
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        Users users = usersMapper.selectOne(wrapper);


        // 判断
        if(users == null) {
            // 数据库没有用户名，认证失败
            throw  new UsernameNotFoundException("用户名不存在！");
        }

        // 设置用户权限
        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_sale");

        // 从查询数据库返回users对象
        // 设置 User(username, password, role)
        return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }
}
















