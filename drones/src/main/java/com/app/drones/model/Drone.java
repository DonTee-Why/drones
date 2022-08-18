package com.app.drones.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drone {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="serial_number", length = 100, updatable = false, nullable = false)
    private String serialNumber;

    @OneToMany
    @JoinColumn(name = "drone_id", referencedColumnName = "serial_number")
    private Set<Medication> medications;

    @NotNull(message = "Weight limit must not be null")
    @Min(value = 1, message = "Invalid Weight")
    @Max(value = 500,message = "Exceeded Weight Limit (500g)")
    @Column(name = "weight_limit")
    private int weightLimit;

    @NotNull
    @Min(0)
    @Max(100)
    private int battery;

    @NotNull(message = "Model must not be null")
    @Enumerated(value = EnumType.STRING)
    private Model model;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private State state = State.IDLE;
}
