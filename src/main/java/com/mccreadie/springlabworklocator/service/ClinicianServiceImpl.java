package com.mccreadie.springlabworklocator.service;
import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.model.Role;
import com.mccreadie.springlabworklocator.repository.ClinicianRepository;
import com.mccreadie.springlabworklocator.repository.RoleRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicianServiceImpl implements ClinicianService{

    private final ClinicianRepository clinicianRepository;
    private final RoleRepository roleRepository;

    public ClinicianServiceImpl(ClinicianRepository clinicianRepository, RoleRepository roleRepository) {
        this.clinicianRepository = clinicianRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Clinician getById(int id) {
        return clinicianRepository.getOne(id);
    }

    @Override
    public void createNewClinician(Clinician clinician) {
        clinician.setEnabled(true);
        clinician.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(clinician.getPassword()));
        try{
            clinicianRepository.save(clinician);
        }
        catch(Exception e){
            e.printStackTrace();
        }

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
