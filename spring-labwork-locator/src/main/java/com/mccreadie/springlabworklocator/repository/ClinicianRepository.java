package com.mccreadie.springlabworklocator.repository;

import com.mccreadie.springlabworklocator.model.Clinician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicianRepository extends JpaRepository<Clinician, Integer> {
}
