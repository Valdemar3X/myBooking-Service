package com.example.mybookingservice.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeAccommodation type;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Address location;
    @NotBlank
    @Column(nullable = false)
    private String size;
    @ElementCollection
    @CollectionTable(name = "accommodation_amenities",
            joinColumns = @JoinColumn(name = "accommodation_id"))
    @Column(name = "amenity")
    @NotNull
    private List<String> amenities;
    @NotNull
    @Column(nullable = false)
    private BigDecimal dailyRate;
    @NotNull
    @Column(nullable = false)
    private Integer availability;

    public enum TypeAccommodation {
        HOUSE, APARTMENT, CONDO, VACATION_HOME
    }
}
