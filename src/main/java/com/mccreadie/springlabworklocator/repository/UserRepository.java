package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    public boolean existsByLogin (String login);

}
