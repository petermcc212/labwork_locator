package com.mccreadie.springlabworklocator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicianTest {


    @Test // UT01
    public void createClinicianTest(){
        Clinician clinician = new Clinician();
        assertNotEquals(clinician, null);

    }


}