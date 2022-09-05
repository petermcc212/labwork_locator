package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.model.PatientTest;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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


//    IT03 FR9
    @Test
    void testPatientSaves() {

        // Create a new patient
        Patient patient = new Patient();
        // First name, last name and date of birth cannot be null
        // and so values are set
        patient.setFirstName("First");
        patient.setLastName("Last");
        Date date = new Date();
        patient.setDateOfBirth(date);

        // Testing save functionality
        Patient savedPatient = patientRepository.save(patient);

        // verify output
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isGreaterThan(0);
        assertThat(savedPatient.getFirstName()).isEqualTo("First");
        assertThat(savedPatient.getLastName()).isEqualTo("Last");
        assertThat(savedPatient.getDateOfBirth()).isEqualTo(date);
    }

    // IT05 FR13
    @Test
    void retrievePatientTest(){
        // create patient and save patient
        Patient patient = new Patient();
        patient.setFirstName("first");
        patient.setLastName("last");
        patient.setDateOfBirth(new Date());
        // retrieve the patient
        Patient savedPatient = patientRepository.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isGreaterThan(0);
        assertThat(savedPatient.getFirstName()).isEqualTo("first");
        assertThat(savedPatient.getLastName()).isEqualTo("last");
    }





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