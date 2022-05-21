package com.ott.hezhu.hibernatejpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author He Zhu
 * @Date 2021-10-05 12:15 p.m.
 * @Version 1.0
 */
@Entity
@Table(name = "sys_user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    /**
     * 配置用户到角色的多对多关系
     * 配置多对多的映射关系
     * 1.声明表关系的配置
     *
     * @ManyToMany(targetEntity = Role.class)  //多对多
     * targetEntity：代表对方的实体类字节码
     * 2.配置中间表（包含两个外键）
     * @JoinTable name : 中间表的名称
     * joinColumns：配置当前对象在中间表的外键
     * @JoinColumn的数组 name：外键名
     * referencedColumnName：参照的主表的主键名
     * inverseJoinColumns：配置对方对象在中间表的外键
     */

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_user_role",
            // joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")},
            // inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}