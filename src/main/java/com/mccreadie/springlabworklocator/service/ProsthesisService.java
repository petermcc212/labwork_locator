package com.mccreadie.springlabworklocator.service;


import com.mccreadie.springlabworklocator.model.Prosthesis;

import java.util.List;

public interface ProsthesisService {

    Prosthesis getById(int id);
    void save(Prosthesis prosthesis);
    void delete (int id);
    List<Prosthesis> getAll();
    List<Prosthesis> getAllInOrderOfCreationDate();

    List<Prosthesis> workDueToday();

    List<Prosthesis> workDueTomorrow();

    List<Prosthesis> workOverdue();

}
