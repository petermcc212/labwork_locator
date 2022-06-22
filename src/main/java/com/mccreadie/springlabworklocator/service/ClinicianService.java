package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.Clinician;

import java.util.List;

public interface ClinicianService {

    Clinician getById(int id);
    void createNewClinician(Clinician clinician);

    void delete(int id);
    List<Clinician> getAll();





}
