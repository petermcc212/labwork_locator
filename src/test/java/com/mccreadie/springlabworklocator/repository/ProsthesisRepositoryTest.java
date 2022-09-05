package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Prosthesis;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProsthesisRepositoryTest {

    // NOTE
    // This is an integration test - note the DataJpaTest


    @Autowired
    private ProsthesisRepository prosthesisRepository;
    @Autowired
    private PatientRepository patientRepository;



    // Ensure prosthetics table is empty before running each test
    @BeforeEach
    public void clear(){
        List<Prosthesis> toDelete = prosthesisRepository.findAll();
        for(Prosthesis p : toDelete){
            prosthesisRepository.delete(p);
        }
    }

    // IT04 FR12
    @Test
    void saveProsthesisTest() {
        // Create a new prosthesis due today
        Prosthesis prosthesis = new Prosthesis();
        LocalDate currentDate = LocalDate.now();
        prosthesis.setDateDue(currentDate);
        prosthesis.setStatus(Prosthesis.Pros_type.SENT);

        // Testing save functionality

        Prosthesis savedProsthesis = prosthesisRepository.save(prosthesis);

        // verify output
        assertThat(savedProsthesis).isNotNull();
        assertThat(savedProsthesis.getId()).isGreaterThan(0);
        assertThat(savedProsthesis.getDateDue()).isEqualTo(currentDate);
        // clear the database
        prosthesisRepository.deleteAll();
    }

    //    IT05 FR21
    @Test
    void findOverdueWork() {
        // Create a new prosthesis due yesterday
        Prosthesis p1 = new Prosthesis();
        p1.setDateDue(LocalDate.now().minusDays(10));
        // Status must be sent
        p1.setStatus(Prosthesis.Pros_type.SENT);
        prosthesisRepository.save(p1);
        // Create another new prosthesis due in 10 days
        Prosthesis p2 = new Prosthesis();
        p2.setDateDue(LocalDate.now().plusDays(10));
        p2.setStatus(Prosthesis.Pros_type.SENT);
        prosthesisRepository.save(p2);
        // Query the repository to find work due today
        List<Prosthesis> prosthesisListReturned = prosthesisRepository.findOverdueWork(LocalDate.now());
        // Test to ensure only one item is returned as only one item is due today
        Assert.assertEquals("Only one item overdue", 1, prosthesisListReturned.size());
    }



    //IT06 FR22
    @Test
    void dueProsthesisTest() {
        // Create a new prosthesis due today
        Prosthesis p1 = new Prosthesis();
        p1.setDateDue(LocalDate.now());
        // Status must be sent
        p1.setStatus(Prosthesis.Pros_type.SENT);
        prosthesisRepository.save(p1);
        // Create another new prosthesis due in 10 days
        Prosthesis p2 = new Prosthesis();
        p2.setDateDue(LocalDate.now().plusDays(10));
        p2.setStatus(Prosthesis.Pros_type.SENT);
        prosthesisRepository.save(p2);
        // Query the repository to find work due today
        List<Prosthesis> prosthesisListReturned = prosthesisRepository.findWorkDueToday(LocalDate.now());

        // Test to ensure only one item is returned as only one item is due today
        Assert.assertEquals("Only one item due today", 1, prosthesisListReturned.size());
        prosthesisRepository.deleteAll();
    }

    //IT07
    @Test
    void findWorkDueTomorrow() {
        // Create a new prosthesis due tomorrow
        Prosthesis p1 = new Prosthesis();
        p1.setDateDue(LocalDate.now().plusDays(1));
        // Status must be sent
        p1.setStatus(Prosthesis.Pros_type.SENT);
        prosthesisRepository.save(p1);
        // Create another new prosthesis due in 10 days
        Prosthesis p2 = new Prosthesis();
        p2.setDateDue(LocalDate.now().plusDays(10));
        p2.setStatus(Prosthesis.Pros_type.SENT);
        prosthesisRepository.save(p2);
        // Query the repository to find work due today
        List<Prosthesis> prosthesisListReturned = prosthesisRepository.findWorkDueTomorrow(LocalDate.now().plusDays(1));

        // Test to ensure only one item is returned as only one item is due today
        Assert.assertEquals("Only one item due tomorrow", 1, prosthesisListReturned.size());
    }



}