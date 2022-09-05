package com.mccreadie.springlabworklocator.model;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatientTest {

    private Patient patient;
    private Date date;
    private Clinician clinician;
    @BeforeEach
    void setUp(){
        patient = new Patient();
        patient.setFirstName("testFirst");
        patient.setLastName("testLast");
        date = new GregorianCalendar(2022, Calendar.APRIL, 8).getTime();
        patient.setDateOfBirth(date);
        clinician = new Clinician();
        clinician.setFirstName("first");
        clinician.setLastName("last");

    }

    @Test // U03 FR7 FR8
    public void createPatientTest(){
        // create patient FR7
        assertNotEquals(patient, null);
        assertEquals(patient.getFirstName(), "testFirst");
        assertEquals(patient.getLastName(), "testLast");
        assertEquals(patient.getDateOfBirth(), date);
        // FR8 - associate patient with clinician
        patient.setClinician(clinician);
        assertThat(patient.getClinician()).isEqualTo(clinician);
    }

    // U05 FR15
    @Test
    public void patientProstheticsTest() {
        Prosthesis prosthesis = new Prosthesis();
        prosthesis.setPatient(patient);
        assertThat(prosthesis.getPatient().getFirstName()).isEqualTo("testFirst");
    }

}
