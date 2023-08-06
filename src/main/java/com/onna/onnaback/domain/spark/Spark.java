package com.onna.onnaback.domain.spark;

import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.memberSparkMapping.MemberSparkMapping;
import com.onna.onnaback.domain.place.Place;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.beans.JavaBean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "Spark")
public class Spark {
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

    //[연관관계] 1(Member) : N(Spark)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="memberId")
    private Member host;

    //[연관관계] 1(place) : N(spark)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "placeId")
    private Place place;

    // [연관관계] 1(Member) : N (MemberSparkMapping)
    @OneToMany(mappedBy = "applySpark")
    List<MemberSparkMapping> memberSparkMappingList=new ArrayList<>();


    private enum DurationHour{
        THIRTY_MIN,ONE_HOUR,TWO_HOUR
    }

    private enum SparkType{
        CLASS,MEETING

    }
}
