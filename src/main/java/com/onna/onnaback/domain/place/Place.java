package com.onna.onnaback.domain.place;

import com.onna.onnaback.domain.spark.Spark;
import com.onna.onnaback.global.utils.BaseEntity;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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

    @Column(name="detailAddress")
    private String detailAddress;
    @Column(name="phoneNum")
    private String phoneNum;
    @Column(name="businessHour")
    private String businessHour;
    @Lob
    @Column(name="description")
    private String description;
    @Lob
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
