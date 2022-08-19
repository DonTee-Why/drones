package com.app.drones.controller;

import com.app.drones.model.Drone;
import com.app.drones.model.Medication;
import com.app.drones.model.Model;
import com.app.drones.model.State;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class DroneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Drone drone = new Drone();
    Medication medication1 = new Medication();
    Medication medication2 = new Medication();
    Medication medication3 = new Medication();

    @BeforeEach
    void setUp() {
        this.drone.setModel(Model.MIDDLEWEIGHT);
        this.drone.setBattery(56);
        this.drone.setWeightLimit(400);

        // Create medications
        this.medication1.setId(1L);
        this.medication1.setName("Medicine_123");
        this.medication1.setWeight(100);
        this.medication1.setCode("MED_1");
        this.medication1.setImage("med1.jpg");

        this.medication2.setId(2L);
        this.medication2.setName("Medicine_124");
        this.medication2.setWeight(35);
        this.medication2.setCode("MED_12");
        this.medication2.setImage("med2.jpg");

        this.medication3.setId(3L);
        this.medication3.setName("Medicine_122");
        this.medication3.setWeight(50);
        this.medication3.setCode("MED_3");
        this.medication3.setImage("med4.jpg");

    }

    @Test
    @DisplayName(value = "Get All Drones Returns List With Status 200")
    void testGetAllDronesReturnsListWithStatus200() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultString = mvcResult.getResponse().getContentAsString();
        ArrayList<?> expected = objectMapper.readValue(resultString, ArrayList.class);
        assertEquals(expected.getClass(), ArrayList.class);
    }

    @Test
    @DisplayName(value = "Register Drone Returns Status 200")
    void testRegistersReturnsStatus201() throws Exception {
        String body = objectMapper.writeValueAsString(this.drone);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Register Returns Status 400 When Required Params Are Omitted")
    void testRegisterReturnsStatus400WhenRequiredParamsAreOmitted() throws Exception {
        Drone drone = new Drone();
        String body = objectMapper.writeValueAsString(drone);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Get Drone Returns Status 200")
    void testGetDroneReturnsStatus200() throws Exception {
        String body = objectMapper.writeValueAsString(this.drone);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        // Get the serial number for the new drone
        String resultString = result.getResponse().getContentAsString();
        Drone newDrone = objectMapper.readValue(resultString, Drone.class);
        String droneId = newDrone.getSerialNumber();
        // Test get drone endpoint with the serial number as parameter
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/drones/{serialNumber}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Get Drone Returns Status 404 When Serial Number Is Wrong")
    void testGetDroneReturnsWithStatus404WhenSerialNumberIsWrong() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/drones/{serialNumber}", 12223 - 123 - 4343)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Load Drone Loads Drone And Returns Status 200")
    void testLoadDroneReturnsStatus200() throws Exception {
        // Create a list of medications
        Set<Medication> medications = new HashSet<>();
        medications.add(this.medication1);
        medications.add(this.medication2);
        medications.add(this.medication3);

        String body = objectMapper.writeValueAsString(this.drone);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        // Get the serial number for the new drone
        String resultString = result.getResponse().getContentAsString();
        Drone newDrone = objectMapper.readValue(resultString, Drone.class);
        String droneId = newDrone.getSerialNumber();
        // Test load drone endpoint with the serial number as parameter
        String medicationBody = objectMapper.writeValueAsString(medications);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones/load/{serialNumber}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(medicationBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications",
                        Matchers.notNullValue(List.class)))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Load Drone Returns Status 404 When Serial Number Is Wrong")
    void testLoadDroneReturnsStatus404WhenSerialNumberIsWrong() throws Exception {
        // Create a list of medications
        Set<Medication> medications = new HashSet<>();
        medications.add(this.medication1);
        medications.add(this.medication2);
        medications.add(this.medication3);

        // Test load drone endpoint with the serial number as parameter
        String medicationBody = objectMapper.writeValueAsString(medications);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones/load/{serialNumber}", 2233 - 1234 - 2222)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(medicationBody))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Load Drone Returns Status 403 When Battery Is Low")
    void testLoadDroneReturnsStatus403WhenBatteryIsLow() throws Exception {

        // Create a list of medications
        Set<Medication> medications = new HashSet<>();
        medications.add(this.medication1);
        medications.add(this.medication2);
        medications.add(this.medication3);

        this.drone.setBattery(20);

        String body = objectMapper.writeValueAsString(drone);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        // Get the serial number for the new drone
        String resultString = result.getResponse().getContentAsString();
        Drone newDrone = objectMapper.readValue(resultString, Drone.class);
        String droneId = newDrone.getSerialNumber();
        // Test load drone endpoint with the serial number as parameter
        String medicationBody = objectMapper.writeValueAsString(medications);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones/load/{serialNumber}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(medicationBody))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andReturn();
        assertEquals(403, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Load Drone Returns With Status 403 When Weight Limit Is Exceeded")
    void testLoadDroneReturnsStatus403WhenWeightLimitIsExceeded() throws Exception {
        // Create a list of medications
        Set<Medication> medications = new HashSet<>();
        medications.add(medication1);
        medications.add(medication2);
        medications.add(medication3);

        this.drone.setWeightLimit(40);

        String body = objectMapper.writeValueAsString(drone);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        // Get the serial number for the new drone
        String resultString = result.getResponse().getContentAsString();
        Drone newDrone = objectMapper.readValue(resultString, Drone.class);
        String droneId = newDrone.getSerialNumber();
        // Test load drone endpoint with the serial number as parameter
        String medicationBody = objectMapper.writeValueAsString(medications);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones/load/{serialNumber}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(medicationBody))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andReturn();
        assertEquals(403, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Load Drone Returns With Status 403 When Drone Is Not Available")
    void testLoadDroneReturnsStatus403WhenDroneIsNotAvailable() throws Exception {
        // Create a list of medications
        Set<Medication> medications = new HashSet<>();
        medications.add(medication1);
        medications.add(medication2);
        medications.add(medication3);

        this.drone.setState(State.LOADED);

        String body = objectMapper.writeValueAsString(drone);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        // Get the serial number for the new drone
        String resultString = result.getResponse().getContentAsString();
        Drone newDrone = objectMapper.readValue(resultString, Drone.class);
        String droneId = newDrone.getSerialNumber();
        // Test load drone endpoint with the serial number as parameter
        String medicationBody = objectMapper.writeValueAsString(medications);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones/load/{serialNumber}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(medicationBody))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andReturn();
        assertEquals(403, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Get Loaded Medication Returns Status 200")
    void testGetLoadedMedicationReturnsStatus200() throws Exception {
        // Create a list of medications
        Set<Medication> medications = new HashSet<>();
        medications.add(medication1);
        medications.add(medication2);
        medications.add(medication3);

        String body = objectMapper.writeValueAsString(drone);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        // Get the serial number for the new drone
        String resultString = result.getResponse().getContentAsString();
        Drone newDrone = objectMapper.readValue(resultString, Drone.class);
        String droneId = newDrone.getSerialNumber();

        // Load drone endpoint with the serial number as parameter
        String medicationBody = objectMapper.writeValueAsString(medications);
        mockMvc.perform(MockMvcRequestBuilders.post("/drones/load/{serialNumber}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(medicationBody));

        // Test get loaded medications endpoint with the serial number as parameter
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/drones/loaded/{serialNumber}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        Matchers.is(3)))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Get Loaded Medication Returns Status 404 When Serial Number Is Wrong")
    void testGetLoadedMedicationReturnsWithStatus404WhenSerialNumberIsWrong() throws Exception {
        // Test get loaded medications endpoint with the serial number as parameter
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/drones/loaded/{serialNumber}", 123-1234-12345)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Get Available Drones Returns Available Drones Status 200")
    void testGetAvailableReturnsAvailableDronesWithStatus200() throws Exception {
        // Test get loaded medications endpoint with the serial number as parameter
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/drones/available")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resultString = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(resultString);
        ArrayList<Drone> availableDrones = objectMapper.convertValue(jsonNode, new TypeReference<>() {
        });
        for (Drone drone : availableDrones) {
            assertEquals(drone.getState(), State.IDLE);
        }
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Get Battery Level Returns Status 200")
    void testGetBatteryLevelReturnsWithStatus200() throws Exception {
        this.drone.setBattery(55);

        String body = objectMapper.writeValueAsString(drone);
        MvcResult droneResult = mockMvc.perform(MockMvcRequestBuilders.post("/drones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        // Get the serial number for the new drone
        String resultString = droneResult.getResponse().getContentAsString();
        Drone newDrone = objectMapper.readValue(resultString, Drone.class);
        String droneId = newDrone.getSerialNumber();

        // Test get loaded medications endpoint with the serial number as parameter
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/drones/battery/{droneId}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("55"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @DisplayName(value = "Get Battery Level Returns 404 Status When Serial Number Is Wrong")
    void testGetBatteryLevelReturnsWith404StatusWhenSerialNumberIsWrong() throws Exception {
        this.drone.setBattery(55);

        // Test get loaded medications endpoint with the serial number as parameter
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/drones/battery/{droneId}", 123-1234-12345)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals(404, result.getResponse().getStatus());

    }
}