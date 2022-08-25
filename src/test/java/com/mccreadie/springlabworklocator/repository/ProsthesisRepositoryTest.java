package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import net.bytebuddy.asm.Advice;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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



    // Ensure prosthetics table is empty before running each test
    @BeforeEach
    public void clear(){
        List<Prosthesis> toDelete = prosthesisRepository.findAll();
        for(Prosthesis p : toDelete){
            prosthesisRepository.delete(p);
        }
    }

    /*
    T01
    This test includes a prosthetic device which is due today
    and so should assert true
     */
    @Test
    void findWorkDueTodayCorrectValue() {
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
        /*
        T02
        This test tests the borderline value for prosthetics due today. No prosthetic due
        today but one due tomorrow
        */
    @Test
    void findWorkDueTodayNoItemsDue() {
        // Create a new prosthesis due today
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
        List<Prosthesis> prosthesisListReturned = prosthesisRepository.findWorkDueToday(LocalDate.now());

        // Test to ensure only one item is returned as only one item is due today
        Assert.assertEquals("No items due today", 0, prosthesisListReturned.size());
    }

    /*
    T03
    This test tests the borderline value for prosthetics due today. No prosthetic due
    today but one due tomorrow
    */
    @Test
    void findWorkDueTodayItemDueYesterday() {
        // Create a new prosthesis due today
        Prosthesis p1 = new Prosthesis();
        p1.setDateDue(LocalDate.now().minusDays(1));
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
        Assert.assertEquals("No items due today", 0, prosthesisListReturned.size());
    }

    /*
    T04
    This test tests the borderline value for prosthetics due today. No prosthetic due
    today but one due yesterday
    */
    @Test
    void findWorkDueYesterday() {
        // Create a new prosthesis due today
        Prosthesis p1 = new Prosthesis();
        p1.setDateDue(LocalDate.now().minusDays(1));
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
        Assert.assertEquals("No items due today", 0, prosthesisListReturned.size());
    }



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
}