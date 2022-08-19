package com.app.drones.listeners;

import com.app.drones.event.CheckBatteryLevelEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

@Service
@Slf4j
public class CheckBatteryLevelListener implements ApplicationListener<CheckBatteryLevelEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(CheckBatteryLevelEvent event) {
        String logDetails = "Time: " + LocalDateTime.now() + ", Drone: " + event.getDrone().getSerialNumber() + ", Battery Level: " + event.getDrone().getBattery() + "%";
        String filePath = new File("").getAbsolutePath() + "/src/main/resources/logs/battery-level.txt";
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logDetails);
            bufferedWriter.newLine();
            bufferedWriter.close();
    }
}
