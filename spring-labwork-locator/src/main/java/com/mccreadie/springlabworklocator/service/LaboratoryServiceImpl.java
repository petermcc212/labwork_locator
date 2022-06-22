package com.mccreadie.springlabworklocator.service;


import com.mccreadie.springlabworklocator.model.Laboratory;
import com.mccreadie.springlabworklocator.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryServiceImpl implements LaboratoryService{

    
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    
    @Override
    public Laboratory getById(int id) {

        return laboratoryRepository.getOne(id);
    }

    @Override
    public void save(Laboratory laboratory) {
        laboratoryRepository.save(laboratory);
    }

    @Override
    public void delete(int id) {
        laboratoryRepository.deleteById(id);
    }

    @Override
    public List<Laboratory> getAll() {
        return laboratoryRepository.findAll();
    }
}
