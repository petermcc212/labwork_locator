package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.LaboratoryProduct;
import com.mccreadie.springlabworklocator.repository.LaboratoryProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryProductServiceImpl implements LaboratoryProductService {


    private final LaboratoryProductRepository laboratoryProductRepository;

    public LaboratoryProductServiceImpl(LaboratoryProductRepository laboratoryProductRepository) {
        this.laboratoryProductRepository = laboratoryProductRepository;
    }


    @Override
    public LaboratoryProduct getById(int id) {
        return this.laboratoryProductRepository.getReferenceById(id);
    }

    @Override
    public void save(LaboratoryProduct laboratoryProduct) {
        laboratoryProductRepository.save(laboratoryProduct);
    }

    @Override
    public void delete(int id) {
        laboratoryProductRepository.deleteById(id);
    }

    @Override
    public List<LaboratoryProduct> getAll() {
        return laboratoryProductRepository.findAll();
    }
}
