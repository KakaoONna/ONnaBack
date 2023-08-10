package com.onna.onnaback.global.utils;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    private Status status= Status.valueOf(Status.ACTIVE.toString());

    public enum Status {
        ACTIVE,
        DELETE
    }

    public void updateStatus(Status status){
        this.status=status;
    }
}