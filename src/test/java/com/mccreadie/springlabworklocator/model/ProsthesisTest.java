package com.mccreadie.springlabworklocator.model;

import com.mccreadie.springlabworklocator.repository.ProsthesisRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

//    U06 FR18
    @Test
    public void updateProsthesisStatusTest(){
        // check SENT status
        prosthesis.setStatus(Prosthesis.Pros_type.SENT);
        assertThat(prosthesis.getStatus()).isEqualTo(Prosthesis.Pros_type.SENT);
        // check COMPLETED status
        prosthesis.setStatus(Prosthesis.Pros_type.COMPLETED);
        assertThat(prosthesis.getStatus()).isEqualTo(Prosthesis.Pros_type.COMPLETED);
        // check REQUIRES_ATTENTION status
        prosthesis.setStatus(Prosthesis.Pros_type.REQUIRES_ATTENTION);
        assertThat(prosthesis.getStatus()).isEqualTo(Prosthesis.Pros_type.REQUIRES_ATTENTION);
        // check RETURNED status
        prosthesis.setStatus(Prosthesis.Pros_type.RETURNED);
        assertThat(prosthesis.getStatus()).isEqualTo(Prosthesis.Pros_type.RETURNED);
        // check VOID status
        prosthesis.setStatus(Prosthesis.Pros_type.VOID);
        assertThat(prosthesis.getStatus()).isEqualTo(Prosthesis.Pros_type.VOID  );
    }


    @Test // U04 FR10, FR11
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

    // U07 FR19
    @Test
    public void updateProsthesisDetailsTest(){
        // check the date
        LocalDate dueDate = LocalDate.now();
        prosthesis.setDateDue(dueDate);
        assertThat(prosthesis.getDateDue()).isEqualTo(dueDate);
        // change the date
        dueDate = LocalDate.now().plusDays(10);
        prosthesis.setDateDue(dueDate);
        assertThat(prosthesis.getDateDue()).isEqualTo(dueDate);
    }

    // U08 //FR20
    @Test
    public void preventIllegalUpdateTest(){
        // it must not be possible to update the patient once set
        Patient firstPatient = new Patient();
        prosthesis.setPatient(firstPatient);
        Patient secondPatient = new Patient();
        prosthesis.setPatient(secondPatient);
        // check to ensure patient associated with prosthesis is still firstPatient
        assertThat(prosthesis.getPatient()).isEqualTo(firstPatient);
        // It must not be possible to update the clinician once set
        Clinician firstClinician = new Clinician();
        prosthesis.setClinician(firstClinician);
        Clinician secondClinician = new Clinician();
        prosthesis.setClinician(secondClinician);
        assertThat(prosthesis.getClinician()).isEqualTo(firstClinician);
    }



}