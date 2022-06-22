package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.repository.ProsthesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProsthesisServiceImpl implements ProsthesisService{

    @Autowired
    private ProsthesisRepository prosthesisRepository;

    @Override
    public Prosthesis getById(int id) {
        return prosthesisRepository.getOne(id);
    }

    @Override
    public void save(Prosthesis prosthesis) {
        prosthesisRepository.save(prosthesis);
    }

    @Override
    public void delete(int id) {
        prosthesisRepository.deleteById(id);
    }

    @Override
    public List<Prosthesis> getAll() {
        return prosthesisRepository.findAll();
    }

    @Override
    public List<Prosthesis> getAllInOrderOfCreationDate() {
        List<Prosthesis> allWorkInCreationDateOrder = prosthesisRepository.findAll(Sort.by(Sort.Direction.ASC, "creationDate"));
        return allWorkInCreationDateOrder;
    }

    @Override
    public List<Prosthesis> workDueToday() {
        LocalDate todaysDate = LocalDate.now();
        List<Prosthesis> allWorkDueToday = prosthesisRepository.findWorkDueToday(todaysDate);
        return allWorkDueToday;
    }

    @Override
    public List<Prosthesis> workDueTomorrow() {
        LocalDate tomorrowsDate = LocalDate.now().plusDays(1);
        List<Prosthesis> allWorkDueTomorrow = prosthesisRepository.findWorkDueTomorrow(tomorrowsDate);
        return allWorkDueTomorrow;
    }

    @Override
    public List<Prosthesis> workOverdue() {
        LocalDate todaysDate = LocalDate.now();
        List<Prosthesis> allOverdueWork = prosthesisRepository.findOverdueWork(todaysDate);
        return allOverdueWork;
    }
}
