package com.app.drones.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drone {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name="id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    @Length(min = 1, max = 100)
    private String serialNumber;

    @OneToMany(mappedBy = "drone")
    private List<Medication> medications;

    @NotNull
    @Min(value = 1, message = "Invalid Weight")
    @Max(value = 500,message = "Exceeded Weight Limit (500g)")
    private int weight;

    @NotNull
    @Min(0)
    @Max(100)
    private int battery;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Model model;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private State state;
}
