package com.app.drones.service;

import com.app.drones.event.CheckBatteryLevelEvent;
import com.app.drones.exception.ResourceNotFoundException;
import com.app.drones.model.Drone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableAsync
@Service
@Slf4j
public class ScheduledTaskService {

    @Autowired
    DroneService droneService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    // Task runs every 30 seconds
    @Async
    @Scheduled(cron = "${com.drone.cron}")
    public void checkBatteryLevels() throws ResourceNotFoundException {
        List<Drone> drones = droneService.getAllDrones();
        for (Drone drone : drones) {
            CheckBatteryLevelEvent checkBatteryLevelEvent = new CheckBatteryLevelEvent(this, drone);
            applicationEventPublisher.publishEvent(checkBatteryLevelEvent);
            droneService.getBatteryLevel(drone.getSerialNumber());
        }
    }
}
