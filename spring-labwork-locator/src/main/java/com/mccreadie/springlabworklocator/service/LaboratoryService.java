package com.mccreadie.springlabworklocator.service;


import com.mccreadie.springlabworklocator.model.Laboratory;

import java.util.List;

public interface LaboratoryService {

    Laboratory getById(int id);
    void save (Laboratory laboratory);
    void delete (int id);
    List<Laboratory> getAll();

}
