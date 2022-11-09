package com.example.course.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class LauchEntity {
    private static final long serialVersion = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private String description;
    private String type;
    private String category;
    private double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof LauchEntity))
            return false;
        LauchEntity lauch = (LauchEntity) obj;

        return Objects.equals(getId(), lauch.getId());
    }


}
