package com.mccreadie.springlabworklocator.service;


import com.mccreadie.springlabworklocator.model.Patient;

import java.util.List;

public interface PatientService {

    Patient getById(int id);
    void save(Patient patient);
    void delete(int id);
    List<Patient> getAll();

    List<Patient> nameStartsWith(String name);


}
