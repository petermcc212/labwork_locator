package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.Laboratory;

import com.mccreadie.springlabworklocator.repository.LaboratoryRepository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LaboratoryServiceImplTest {

    private LaboratoryService laboratoryService;

    private LaboratoryRepository laboratoryRepository;


    @BeforeEach
    void setUp(){
        laboratoryRepository = Mockito.mock(LaboratoryRepository.class);
        laboratoryService = new LaboratoryServiceImpl(laboratoryRepository);
    }


    @Test
    void getById() {
        // Create a new laboratory and assign it an ID
        Laboratory expected = new Laboratory();
        expected.setId(1);
        // Use Mockito to mock the expected response from the laboratory repository
        when(laboratoryRepository.getReferenceById(1)).thenReturn(expected);

        // The actual return value of the method
        Laboratory actual = laboratoryService.getById(1);
        // Test to check if the actual response matches the expected
        Assert.assertEquals(expected, actual);


    }
}