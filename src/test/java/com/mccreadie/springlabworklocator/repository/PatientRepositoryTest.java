package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Patient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PatientRepositoryTest {


    //NOTE
    // This is an integration test - note the DatJpaTest

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void findByKeyword() {

        // Create a new patient
        Patient patient = new Patient();
        // First name and date of birth cannot be null
        // and so values are set
        patient.setFirstName("testFirstName");
        patient.setDateOfBirth(new Date());
        patientRepository.save(patient);
        List<Patient> sentPatients = new ArrayList<>();
        sentPatients.add(patient);

        Assert.assertEquals(sentPatients, patientRepository.findByKeyword("tes"));




    }
}