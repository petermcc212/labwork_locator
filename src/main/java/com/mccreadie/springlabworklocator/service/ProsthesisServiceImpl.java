package com.mccreadie.springlabworklocator.service;

import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.repository.ProsthesisRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProsthesisServiceImpl implements ProsthesisService{

    private final ProsthesisRepository prosthesisRepository;

    public ProsthesisServiceImpl(ProsthesisRepository prosthesisRepository) {
        this.prosthesisRepository = prosthesisRepository;
    }

    @Override
    public Prosthesis getById(int id) {
        return prosthesisRepository.getReferenceById(id);
    }

    @Override
    public Prosthesis save(Prosthesis prosthesis) {
        try{
            prosthesisRepository.save(prosthesis);
        }catch (Exception e){
            e.printStackTrace();
        }
        return prosthesis;
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
        return prosthesisRepository.findAll(Sort.by(Sort.Direction.ASC, "creationDate"));
    }

    @Override
    public List<Prosthesis> workDueToday() {
        LocalDate todaysDate = LocalDate.now();
        return prosthesisRepository.findWorkDueToday(todaysDate);
    }

    @Override
    public List<Prosthesis> workDueTomorrow() {
        LocalDate tomorrowsDate = LocalDate.now().plusDays(1);
        return prosthesisRepository.findWorkDueTomorrow(tomorrowsDate);
    }

    @Override
    public List<Prosthesis> workOverdue() {
        LocalDate todaysDate = LocalDate.now();
        return prosthesisRepository.findOverdueWork(todaysDate);
    }
}
