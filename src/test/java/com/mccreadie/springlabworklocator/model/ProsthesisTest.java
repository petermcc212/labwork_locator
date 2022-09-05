package com.mccreadie.springlabworklocator.model;

import com.mccreadie.springlabworklocator.repository.ProsthesisRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProsthesisTest {

    private Prosthesis prosthesis;
    @BeforeEach
    void setUp() {
        prosthesis = new Prosthesis();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void compareTo() {
    }

    @Test // U04 create  prosthesis
    public void createProsthesisTest(){

        assertNotEquals(prosthesis, null);
        // test laboratory
        Laboratory testLab = new Laboratory();
        prosthesis.setLaboratory(testLab);
        assertEquals(prosthesis.getLaboratory(), testLab);
        // test clinician
        Clinician testClinician = new Clinician();
        prosthesis.setClinician(testClinician);
        assertEquals(prosthesis.getClinician(), testClinician);
        // test patient
        Patient testPatient = new Patient();
        prosthesis.setPatient(testPatient);
        assertEquals(prosthesis.getPatient(), testPatient);
        // test setting the date
        // check date in the past
        LocalDate dateDue = LocalDate.now();
        dateDue = LocalDate.now().minusDays(1);
        prosthesis.setDateDue(dateDue);
        assertEquals(prosthesis.getDateDue(), dateDue);
        // check today's date
        dateDue = LocalDate.now();
        prosthesis.setDateDue(dateDue);
        assertEquals(prosthesis.getDateDue(), dateDue );
        // check tomorrow's date
        dateDue = LocalDate.now().plusDays(1);
        prosthesis.setDateDue(dateDue);
        assertEquals(prosthesis.getDateDue(), dateDue);
        // test status
        // sent
        prosthesis.setStatus(Prosthesis.Pros_type.SENT);
        assertEquals(prosthesis.getStatus(), Prosthesis.Pros_type.SENT);
        // returned
        prosthesis.setStatus(Prosthesis.Pros_type.RETURNED);
        assertEquals(prosthesis.getStatus(), Prosthesis.Pros_type.RETURNED);
        // completed
        prosthesis.setStatus(Prosthesis.Pros_type.COMPLETED);
        assertEquals(prosthesis.getStatus(), Prosthesis.Pros_type.COMPLETED);
        // requires attention
        prosthesis.setStatus(Prosthesis.Pros_type.REQUIRES_ATTENTION);
        assertEquals(prosthesis.getStatus(), Prosthesis.Pros_type.REQUIRES_ATTENTION);
        // void
        prosthesis.setStatus(Prosthesis.Pros_type.VOID);
        assertEquals(prosthesis.getStatus(), Prosthesis.Pros_type.VOID);
        // test additional notes
        prosthesis.setNotes("Test note");
        assertEquals(prosthesis.getNotes(), "Test note");
    }



}