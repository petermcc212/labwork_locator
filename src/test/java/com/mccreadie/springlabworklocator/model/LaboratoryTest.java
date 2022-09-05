package com.mccreadie.springlabworklocator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LaboratoryTest {

    @Test // U02
    public void createLaboratoryTest(){
        Laboratory laboratory = new Laboratory();
        assertNotEquals(laboratory, null);
        laboratory.setName("TestName");
        assertEquals(laboratory.getName(), "TestName");
    }

}
