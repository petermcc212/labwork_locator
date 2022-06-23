package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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




    @BeforeEach
    public void clear(){
        // Ensure user table is empty before running each test
        List<User> toDelete = userRepository.findAll();
        for(User user : toDelete){
            userRepository.delete(user);
        }
    }


    @Test
    void existsByLoginTrue() {
        User user = new User();
        user.setLogin("user");
        user.setPassword("password");
        userRepository.save(user);

        Assert.assertTrue(userRepository.existsByLogin("test"));
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
