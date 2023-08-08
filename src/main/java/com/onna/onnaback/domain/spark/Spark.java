package com.onna.onnaback.domain.spark;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.memberSparkMapping.MemberSparkMapping;
import com.onna.onnaback.domain.place.Place;

import com.onna.onnaback.global.utils.BaseEntity;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.beans.JavaBean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Table(name = "Spark")

public class Spark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sparkId")
    private Long sparkId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    @Lob
    private String description;

    @Column(name="type")
    @Enumerated(value = EnumType.STRING)
    private SparkType type;

    @Column(name="sparkDate")
    private LocalDateTime sparkDate;

    @Column(name="memberCount")
    private Long memberCount;

    @Column(name="price")
    private Long price;

    @Column(name="capacity")
    private Long capacity;

    @Column(name="duration")
    @Enumerated(value = EnumType.STRING)
    private DurationHour duration;

    @Column(name="hostDetail")
    @Lob
    private String hostDetail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="memberId")
    private Member host;




    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "placeId")
    private Place place;


    @OneToMany(mappedBy = "applySpark")
    List<MemberSparkMapping> memberSparkMappingList=new ArrayList<>();



}
