package com.mccreadie.springlabworklocator.repository;


import com.mccreadie.springlabworklocator.model.Laboratory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LaboratoryRepositoryTest {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    //IT04 FR6
    @Test
    void saveLaboratoryTest(){
        // Create new laboratory
        Laboratory laboratory = new Laboratory();
        laboratory.setName("testLab");
        laboratory.setAddress("testAddress");
        laboratory.setPhoneNumber("0800454787");
        // Testing save functionality
        Laboratory savedLaboratory = laboratoryRepository.save(laboratory);
        // verify output
        assertThat(savedLaboratory).isNotNull();
        assertThat(savedLaboratory.getId()).isGreaterThan(0);
        assertThat(savedLaboratory.getName()).isEqualTo("testLab");
        assertThat(savedLaboratory.getAddress()).isEqualTo("testAddress");
        assertThat(savedLaboratory.getPhoneNumber()).isEqualTo("0800454787");
    }



}
