package com.app.drones.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class Medication {

    @Id
    @GeneratedValue
    private String id;

    @OneToMany(mappedBy = "drone_id", fetch = FetchType.LAZY)
    private Drone drone;

    private String name;

    @NotNull
    private int weight;

    @NotNull
    @Min(0)
    @Max(100)
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Invalid code")
    private String code;

    @NotNull
    @Pattern(regexp = "^[^\\\\s]+(\\.(jpe?g|png|gif|bmp))$", message = "Invalid image string")
    private String image;

    public Medication(String name, int weight, String code, String image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
