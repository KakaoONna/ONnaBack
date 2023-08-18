package com.onna.onnaback.domain.place.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.onna.onnaback.domain.apply.spark.domain.Spark;
import com.onna.onnaback.global.utils.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

    @Column(name = "detailAddress")
    private String detailAddress;

    @Column(name = "phoneNum")
    private String phoneNum;

    @Column(name = "businessHour")
    private String businessHour;

    @Column(name = "description")
    private String description;

    @Column(name = "detailInfo")
    private String detailInfo;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private PlaceType placeType;

    @Column(name = "lng")
    private Double longitude;

    @Column(name = "lat")
    private Double latitude;

    @OneToMany(mappedBy = "place")
    List<Spark> sparkList = new ArrayList<>();

}
