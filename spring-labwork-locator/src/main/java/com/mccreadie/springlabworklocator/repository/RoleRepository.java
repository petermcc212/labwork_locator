package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {



}
