package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.LaboratoryProduct;

import java.util.List;

public interface LaboratoryProductService {


    LaboratoryProduct getById(int id);

    void save(LaboratoryProduct laboratoryProduct);

    void delete (int id);

    List<LaboratoryProduct> getAll();



}
