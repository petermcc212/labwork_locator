package com.mccreadie.springlabworklocator.service;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.repository.ProsthesisRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProsthesisServiceImplTest {

    // System under test
    private ProsthesisService prosthesisService;

    // Mock
    private ProsthesisRepository prosthesisRepository;

    @BeforeEach
    void setUp(){
        prosthesisRepository = Mockito.mock(ProsthesisRepository.class);
        prosthesisService = new ProsthesisServiceImpl(prosthesisRepository);
    }



    /*
    Checking the prosthesis service can return a specific prosthesis
     */
    @Test
    void getById() {
        // Create a new prosthesis and assign an ID
        Prosthesis expected = new Prosthesis();
        expected.setId(1);
        // Use Mockito to mock the expected response from the prosthesis repository
        when(prosthesisRepository.getReferenceById(1)).thenReturn(expected);

        // The actual return value of the method
        Prosthesis actual = prosthesisService.getById(1);
        // Test to check if the actual response matches the expected response
        Assert.assertEquals(expected, actual);
    }



    @Test
    void getAll() {
        // Create two new prosthesis items and add to an array list
        Prosthesis pros1 = new Prosthesis();
        pros1.setId(1);
        Prosthesis pros2 = new Prosthesis();
        pros2.setId(2);
        List<Prosthesis> listSent = new ArrayList<>();
        listSent.add(pros1);
        listSent.add(pros2);

        // Use Mockito to mock the expected response from the prosthesis repository
        when(prosthesisRepository.findAll()).thenReturn(listSent);
        List<Prosthesis> listReceived = prosthesisService.getAll();
        // Test to check if the actual response matches the expected response
        Assert.assertEquals(listSent, listReceived);


    }


    @Test
    void workDueToday() {
        // Create a new prosthesis with today's date and add it to an array list
        LocalDate date = LocalDate.now();
        Prosthesis pros1 = new Prosthesis();
        pros1.setDateDue(date);
        List<Prosthesis> allWorkSent = new ArrayList<>();
        allWorkSent.add(pros1);

        // Use Mockito to mock the expected response from the prosthesis repository
        when(prosthesisRepository.findWorkDueToday(date)).thenReturn(allWorkSent);
        List<Prosthesis> received = prosthesisService.workDueToday();
        // Test to check the method has returned the expected list
        Assert.assertEquals(allWorkSent, received);
    }

    @Test
    void workDueTomorrow() {
        // Create a new prosthesis with tomorrow's date and add it to an array list
        LocalDate date = LocalDate.now().plusDays(1);
        Prosthesis pros1 = new Prosthesis();
        pros1.setDateDue(date);
        List<Prosthesis> allWorkSent = new ArrayList<>();
        allWorkSent.add(pros1);
        // Use Mockito to mock the expected response from the prosthesis repository
        when(prosthesisRepository.findWorkDueTomorrow(date)).thenReturn(allWorkSent);
        List<Prosthesis> received = prosthesisService.workDueTomorrow();
        // Test to check the method has returned the expected list
        Assert.assertEquals(allWorkSent, received);

    }

    @Test
    void workOverdue() {
        // Create a new prosthesis with yesterday's date and add it to an array list
        LocalDate date = LocalDate.now();
        Prosthesis pros1 = new Prosthesis();
        pros1.setDateDue(date);
        List<Prosthesis> allWorkSent = new ArrayList<>();
        allWorkSent.add(pros1);
        // Use Mockito to mock the expected response from the prosthesis repository
        when(prosthesisRepository.findOverdueWork(date)).thenReturn(allWorkSent);
        List<Prosthesis> received = prosthesisService.workOverdue();
        // Test to check the method has returned the expected list
        Assert.assertEquals(allWorkSent, received);


    }
}