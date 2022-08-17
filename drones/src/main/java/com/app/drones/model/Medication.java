package com.app.drones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "medications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int weight;

    @NotNull
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid code")
    private String code;

    @NotNull
    @Pattern(regexp = "^[^\\\\s]+(\\.(jpe?g|png|gif|bmp))$", message = "Invalid image string")
    private String image;

}
