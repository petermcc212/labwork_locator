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
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
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
        patient.setFirstName("First");
        patient.setDateOfBirth(new Date());
        patientRepository.save(patient);

        // Create a second patient with a different first name
        Patient patient1 = new Patient();
        patient1.setFirstName("Second");
        patient1.setDateOfBirth(new Date());
        patientRepository.save(patient1);
        // Add the patients to a list
        List<Patient> sentPatients = new ArrayList<>();
        sentPatients.add(patient);
        sentPatients.add(patient1);

        // Perform the query on the repository and save the result
        List<Patient> returnedList = patientRepository.findByKeyword("First");
        // Test to ensure only one item is returned by the keyword search of a partial name
        Assert.assertEquals("One item returned as expected", 1, returnedList.size()  );

    }
}