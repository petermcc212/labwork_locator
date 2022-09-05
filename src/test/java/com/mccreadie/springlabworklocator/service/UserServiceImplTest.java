package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.controller.ClinicianController;
import com.mccreadie.springlabworklocator.model.Role;
import com.mccreadie.springlabworklocator.model.User;
import com.mccreadie.springlabworklocator.repository.RoleRepository;
import com.mccreadie.springlabworklocator.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository mockUserRepository;



    /*
    Test this method correctly checks if a user exists with
    the sme login
     */
    @Test
    void testLoginExists() {
        when(mockUserRepository.existsByLogin("testLogin")).thenReturn(true);

        // Ensure method returns true when names are the same
        userServiceImpl.loginExists("testLogin");

        // Ensure method returns false when names are not the same
        assertFalse(userServiceImpl.loginExists("wrongLoginName"));

    }




    /*
    Test to check method enables a user,encrypts the password,

     */

    @Test
    void testCreateNewAccount() {
        User user = new User();
        user.setId(1);
        user.setLogin("testName");
        user.setPassword("password");



        assertFalse(user.isEnabled());
        userServiceImpl.createNewAccount(user);
        assert(user.isEnabled());


    }



}