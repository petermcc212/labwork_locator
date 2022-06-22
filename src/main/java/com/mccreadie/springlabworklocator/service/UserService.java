package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.User;

public interface UserService {

    void createNewAccount(User user);

    boolean loginExists (String login);


}
