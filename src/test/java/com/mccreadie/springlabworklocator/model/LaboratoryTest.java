package com.mccreadie.springlabworklocator.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LaboratoryTest {

    @Test // U02 FR4
    public void createLaboratoryTest(){
        Laboratory laboratory = new Laboratory();
        assertNotEquals(laboratory, null);
        laboratory.setName("TestName");
        // test a new laboratory (FR7)
        assertEquals(laboratory.getName(), "TestName");
        // test laboratory can be associated with a new prosthesis (FR8)
        Prosthesis prosthesis = new Prosthesis();
        prosthesis.setLaboratory(laboratory);
        assertThat(prosthesis.getLaboratory()).isEqualTo(laboratory);
    }

}
