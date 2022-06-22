package com.mccreadie.springlabworklocator.service;


import com.mccreadie.springlabworklocator.model.Role;
import com.mccreadie.springlabworklocator.model.User;
import com.mccreadie.springlabworklocator.repository.RoleRepository;
import com.mccreadie.springlabworklocator.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void createNewAccount(User user) {
        user.setEnabled(true);
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);


        Role role = new Role();
        role.setLogin(user.getLogin());
        System.out.println("AND SO THE ROLE IS " + user.getRole());
        role.setRole(user.getRole());
        roleRepository.save(role);
    }

    @Override
    public boolean loginExists(String login) {
        return userRepository.existsByLogin(login);
    }
}
