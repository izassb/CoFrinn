package com.example.course.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExitEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "value")
    private double value;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExitEntity that = (ExitEntity) o;
        return Id != null && Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
