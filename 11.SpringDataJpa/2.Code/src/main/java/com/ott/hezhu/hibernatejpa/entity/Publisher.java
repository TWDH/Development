package com.ott.hezhu.hibernatejpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.NavigableMap;

/**
 * @author He Zhu
 * @date 2021-08-26 12:26 p.m.
 */

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    @Column(name = "publisher_name")
    private String name;

    @Column(name = "publihser_country")
    private String country;

    @Column(name = "publisher_city")
    private String city;
}
