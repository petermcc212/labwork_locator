package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Role;
import com.mccreadie.springlabworklocator.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    // NOTE
    // This is an integration test - note the DataJpaTest


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;




    @BeforeEach
    public void clear(){
        // Ensure user table is empty before running each test
        List<User> toDelete = userRepository.findAll();
        for(User user : toDelete){
            userRepository.delete(user);
        }
    }


    // IT01 FR2 FR3
    @Test
    void existsByLoginTest() {
        // test administrator
        User administrator = new User();
        administrator.setLogin("admin");
        administrator.setPassword("password");
        administrator.setRole("ADMIN");
        Role adminRole = new Role();
        adminRole.setLogin(administrator.getLogin());
        adminRole.setRole(administrator.getRole());
        roleRepository.save(adminRole);
        userRepository.save(administrator);
        Assert.assertTrue(userRepository.existsByLogin("admin"));
        // test clinician
        User clinician = new User();
        clinician.setLogin("clinician");
        clinician.setPassword("password");
        clinician.setRole("CLINICIAN");
        Role clinicianRole = new Role();
        clinicianRole.setLogin(administrator.getLogin());
        clinicianRole.setRole(administrator.getRole());
        roleRepository.save(clinicianRole);
        userRepository.save(clinician);
        Assert.assertTrue(userRepository.existsByLogin("clinician"));
        // Test receptionist
        User receptionist = new User();
        receptionist.setLogin("receptionist");
        receptionist.setPassword("password");
        receptionist.setRole("RECEPTIONIST");
        Role receptionistRole = new Role();
        receptionistRole.setLogin(receptionist.getLogin());
        receptionistRole.setRole(receptionist.getRole());
        roleRepository.save(receptionistRole);
        userRepository.save(receptionist);
        Assert.assertTrue(userRepository.existsByLogin("receptionist"));
    }


    @Test
    void existsByLoginFalse() {
        User user = new User();
        user.setLogin("username");
        user.setPassword("password");
        userRepository.save(user);

        Assert.assertFalse(userRepository.existsByLogin("test"));
    }

}
