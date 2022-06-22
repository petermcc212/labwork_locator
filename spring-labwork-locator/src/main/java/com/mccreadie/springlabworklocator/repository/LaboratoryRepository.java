package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Integer> {




}
