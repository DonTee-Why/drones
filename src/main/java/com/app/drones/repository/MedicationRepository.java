package com.app.drones.repository;

import com.app.drones.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicationRepository extends JpaRepository<Medication, Long> {
//    Medication findById(String serialNumber);
}
