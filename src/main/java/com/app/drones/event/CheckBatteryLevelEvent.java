package com.app.drones.event;

import com.app.drones.model.Drone;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CheckBatteryLevelEvent extends ApplicationEvent {
    private Drone drone;

    public CheckBatteryLevelEvent(Object source, Drone drone) {
        super(source);
        this.drone = drone;
    }

    public Drone getDrone() {
        return drone;
    }
}
