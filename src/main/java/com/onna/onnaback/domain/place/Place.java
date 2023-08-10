package com.onna.onnaback.domain.place;

import com.onna.onnaback.domain.spark.Spark;

import com.onna.onnaback.global.utils.BaseEntity;

import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Table(name = "Place")

public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placeId")
    private Long placeId;

    @Column(name = "name")
    private String name;
    @Column(name = "img")
    private String img;

    @Column(name="detailAddress")
    private String detailAddress;
    @Column(name="phoneNum")
    private String phoneNum;
    @Column(name="businessHour")
    private String businessHour;

    @Column(name="description")
    private String description;

    @Column(name="detailInfo")
    private String detailInfo;

    @Column(name="type")
    @Enumerated(value = EnumType.STRING)
    private PlaceType type;

    @Column(name="lng")
    private Double lng;

    @Column(name="lat")
    private Double lat;


    @OneToMany(mappedBy = "place")
    List<Spark> sparkList=new ArrayList<>();


}
