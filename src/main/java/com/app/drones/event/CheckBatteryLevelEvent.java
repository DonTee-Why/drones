package com.app.drones.event;

import com.app.drones.model.Drone;
import org.springframework.context.ApplicationEvent;


public class CheckBatteryLevelEvent extends ApplicationEvent {
    private final Drone drone;

    public CheckBatteryLevelEvent(Object source, Drone drone) {
        super(source);
        this.drone = drone;
    }

    public Drone getDrone() {
        return drone;
    }
}
