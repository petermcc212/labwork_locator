package com.mccreadie.springlabworklocator.model;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import java.util.GregorianCalendar;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatientTest {

    @Test // U03
    public void createPatientTest(){
        Patient patient = new Patient();
        assertNotEquals(patient, null);
        patient.setFirstName("testFirst");
        assertEquals(patient.getFirstName(), "testFirst");
        patient.setLastName("testLast");
        assertEquals(patient.getLastName(), "testLast");
        Date date = new GregorianCalendar(2022, Calendar.APRIL, 8).getTime();
        patient.setDateOfBirth(date);
        assertEquals(patient.getDateOfBirth(), date);


    }
}
