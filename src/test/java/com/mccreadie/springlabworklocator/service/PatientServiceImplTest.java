package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.repository.PatientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    private PatientService patientService;

    private PatientRepository patientRepository;


    @BeforeEach
    void setUp(){
        patientRepository = Mockito.mock(PatientRepository.class);
        patientService = new PatientServiceImpl(patientRepository);
    }



    @Test
    void getById() {

        // Create a new patient and assign it an ID
        Patient expected = new Patient();
        expected.setId(1);
        // Use Mockito to mock the expected response from the patient repository
        when(patientRepository.getReferenceById(1)).thenReturn(expected);

        // The actual return value of the method
        Patient actual = patientService.getById(1);
        // Test to check if the actual response matches the expected
        Assert.assertEquals(expected, actual);
    }

    @Test
    void nameStartsWith() {
        // Create a new patient and set the name
        Patient expected = new Patient();
        expected.setFirstName("test");
        // Create a list of patients and add the newly created patient
        List<Patient> expectedList = new ArrayList<>();
        // Use Mockito to mock the expected response from the patient repository
        when(patientRepository.findByKeyword("test")).thenReturn(expectedList);
        // The actual return value of the method when searching for a partial name
        List<Patient> actualList = patientService.nameStartsWith("te");
        // Test to check if the actual response matches the expected
        Assert.assertEquals(expectedList, actualList);


    }
}