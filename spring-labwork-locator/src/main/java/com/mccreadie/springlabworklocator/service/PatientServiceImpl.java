package com.mccreadie.springlabworklocator.service;


import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getById(int id) {
        return patientRepository.getOne(id);
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);

    }

    @Override
    public void delete(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> nameStartsWith(String name) {
        return patientRepository.findByKeyword(name);
    }
}
