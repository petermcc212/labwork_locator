package com.mccreadie.springlabworklocator.model;

import com.mccreadie.springlabworklocator.repository.PatientRepository;
import com.mccreadie.springlabworklocator.service.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {


    @Test // U01 FR1
    public void createNewUser(){

        //test administrator
        User admin = new User();
        admin.setRole("ADMIN");
        admin.setLogin("admin");
        admin.setPassword("password");
        assertThat(admin.getRole()).isEqualTo("ROLE_ADMIN");
        assertThat(admin.getLogin()).isEqualTo("admin");
        assertThat(admin.getPassword()).isEqualTo("password");
        // test clinician
        User clinician = new User();
        clinician.setRole("CLINICIAN");
        clinician.setLogin("clinician");
        clinician.setPassword("password");
        assertThat(clinician.getRole()).isEqualTo("ROLE_CLINICIAN");
        assertThat(clinician.getLogin()).isEqualTo("clinician");
        assertThat(clinician.getPassword()).isEqualTo("password");
        // test receptionist
        User receptionist = new User();
        receptionist.setRole("RECEPTIONIST");
        receptionist.setLogin("receptionist");
        receptionist.setPassword("password");
        assertThat(receptionist.getRole()).isEqualTo("ROLE_RECEPTIONIST");
        assertThat(receptionist.getLogin()).isEqualTo("receptionist");
        assertThat(receptionist.getPassword()).isEqualTo("password");

    }
}
