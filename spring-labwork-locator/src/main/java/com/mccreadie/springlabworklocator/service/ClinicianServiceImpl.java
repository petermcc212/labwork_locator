package com.mccreadie.springlabworklocator.service;
import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.model.Role;
import com.mccreadie.springlabworklocator.repository.ClinicianRepository;
import com.mccreadie.springlabworklocator.repository.RoleRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicianServiceImpl implements ClinicianService{

    @Autowired
    private ClinicianRepository clinicianRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Clinician getById(int id) {
        return clinicianRepository.getOne(id);
    }

    @Override
    public void createNewClinician(Clinician clinician) {
        clinician.setEnabled(true);
        clinician.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(clinician.getPassword()));
        clinicianRepository.save(clinician);

        Role role = new Role();
        role.setLogin(clinician.getLogin());
        role.setRole("ROLE_CLINICIAN");
        roleRepository.save(role);

    }



    @Override
    public void delete(int id) {

    }

    @Override
    public List<Clinician> getAll() {
        return clinicianRepository.findAll();
    }

}
