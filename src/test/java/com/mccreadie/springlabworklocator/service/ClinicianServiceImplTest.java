package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.repository.ClinicianRepository;
import com.mccreadie.springlabworklocator.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;

class ClinicianServiceImplTest {

    private  ClinicianRepository clinicianRepository;
    private  RoleRepository roleRepository;
    private ClinicianService clinicianService;


    @BeforeEach
    void setupService(){
        clinicianRepository = mock(ClinicianRepository.class);
        roleRepository = mock(RoleRepository.class);
        clinicianService = new ClinicianServiceImpl(clinicianRepository, roleRepository);
    }

    @Test
    void getById() {
        
    }

    @Test
    void createNewClinician() {

        Clinician clinician = new Clinician();
        clinician.setId(1);
        clinician.setFirstName("Peter");
        clinician.setLastName("McCreadie");

        assertThat(clinician.getFirstName()).isEqualTo("Peter");




    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}