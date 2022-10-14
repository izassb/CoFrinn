package com.example.course.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@EqualsAndHashCode

@Getter
@Setter
public class ExitEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer Id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    private String description;

    private String type;

    private String category;

    private double value;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ExitEntity)) return false;
        ExitEntity exit = (ExitEntity) obj;
        return Objects.equals(getId(), exit.getId());
    }

}
