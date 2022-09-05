package com.mccreadie.springlabworklocator.security;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;


    // IT07 FR24
    // test administrator access
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void adminAuthorisedTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .accept(MediaType.ALL))
                        .andExpect(status().isOk());
    }



    // IT08 FR24
    // test clinician access
    @Test
    @WithMockUser(username = "clinician", password = "clinician", roles = "CLINICIAN")
    public void clinicianAuthorisedTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    // IT09 FR24
    // test receptionist access
    @Test
    @WithMockUser(username = "receptionist", password = "receptionist", roles = "RECEPTIONIST")
    public void receptionistAuthorisedTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    // IT10 FR25
    // Unauthorised login
    @Test
    public void unauthorisedAccessTest() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(redirectedUrlPattern("**/login"));
    }

    //IT11 FR26
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void logoutTest() throws Exception {
        // test access to home page after authentication
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
        // logout
        this.mockMvc.perform(logout());
        // test redirect to login page
        this.mockMvc.perform(get("/")).andExpect(redirectedUrlPattern("**/login"));

    }

}
