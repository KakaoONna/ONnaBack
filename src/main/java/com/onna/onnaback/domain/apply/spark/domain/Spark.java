package com.onna.onnaback.domain.apply.spark.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.onna.onnaback.domain.apply.domain.MemberSparkMapping;
import com.onna.onnaback.domain.member.domain.Member;
import com.onna.onnaback.domain.place.domain.Place;
import com.onna.onnaback.global.utils.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Spark")
@Getter
public class Spark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sparkId")
    private Long sparkId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    @Lob
    private String description;

    // 스파크 클래스, 스파크 미팅 구분
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private SparkType type;

    @Column(name = "sparkDate")
    private LocalDateTime sparkDate;

    @Column(name = "memberCount")
    private Long memberCount;

    @Column(name = "price")
    private Long price;

    @Column(name = "capacity")
    private Long capacity;

    // 스파크 지속시간
    @Column(name = "duration")
    @Enumerated(value = EnumType.STRING)
    private DurationHour durationHour;

    @Column(name = "hostDetail")
    @Lob
    private String hostDetail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "memberId")
    private Member host;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "placeId")
    private Place place;

    @OneToMany(mappedBy = "applySpark")
    List<MemberSparkMapping> memberSparkMappingList = new ArrayList<>();

}
