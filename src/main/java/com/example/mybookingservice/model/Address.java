package com.example.mybookingservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@SQLDelete(sql = "UPDATE addresses SET is_deleted = TRUE WHERE id=?")
@SQLRestriction("is_deleted=FALSE")

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String cityName;
    @NotBlank
    @Column(nullable = false)
    private String str;
    @NotBlank
    @Column(nullable = false)
    private String numberOfBuilding;
    @NotNull
    private Integer numberOfApartment;
    @Column(nullable = false)
    private boolean isDeleted = false;
}
