package com.weatherservice.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedBy
    protected Long createdBy;

    @CreatedDate
    @Column(columnDefinition = "timestamp default now()")
    protected LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    protected Long lastModifiedBy;

    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditable auditable = (Auditable) o;
        return Objects.equals(id, auditable.id) && Objects.equals(createdBy, auditable.createdBy) && Objects.equals(createdDate, auditable.createdDate) && Objects.equals(lastModifiedBy, auditable.lastModifiedBy) && Objects.equals(lastModifiedDate, auditable.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }

    @Override
    public String toString() {
        return "Auditable{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}

