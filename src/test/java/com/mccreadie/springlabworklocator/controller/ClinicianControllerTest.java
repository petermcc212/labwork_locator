package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.repository.ClinicianRepository;
import com.mccreadie.springlabworklocator.service.ClinicianService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClinicianControllerTest {

    @MockBean
    private ClinicianRepository clinicianRepository;

    @MockBean
    private ClinicianService clinicianService;



    @Test
    void editClinicianDetails() {
        System.out.println(clinicianService.getAll());
    }


    @Test
    void newClinicianForm() {

        Clinician clinician = new Clinician();


    }
}