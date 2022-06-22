package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.User;

public interface UserService {

    public void createNewAccount(User user);

    public boolean loginExists (String login);


}
