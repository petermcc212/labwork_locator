package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.repository.ClinicianRepository;
import com.mccreadie.springlabworklocator.service.ClinicianService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ClinicianControllerTest {

    @Mock
    private ClinicianRepository clinicianRepository;
    @MockBean
    private ClinicianService clinicianService;


    @Before
    public void setUp(){



    }

    @Test
    void editClinicianDetails() {


    }


    @Test
    void newClinicianForm() {




    }
}