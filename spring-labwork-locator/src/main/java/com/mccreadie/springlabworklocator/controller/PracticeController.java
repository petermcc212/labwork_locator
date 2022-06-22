package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.service.ClinicianService;
import com.mccreadie.springlabworklocator.service.LaboratoryService;
import com.mccreadie.springlabworklocator.service.PatientService;
import com.mccreadie.springlabworklocator.service.ProsthesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class PracticeController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private ClinicianService clinicianService;

    @Autowired
    private ProsthesisService prosthesisService;

    @Autowired
    private PatientService patientService;


    @Transactional
    @GetMapping("/viewAllLabWork")
    public String viewAllLabWork(Model model)
    {
        List<Prosthesis> allLabWork = prosthesisService.getAllInOrderOfCreationDate();
        model.addAttribute("allLabWork", allLabWork);
        return "practice/practice-lab-work";
    }




//    @Transactional
//    @GetMapping("/viewPatientsLabWork/{patientId}")
//    public String viewPatientsLabWork(@PathVariable int patientId, Model model)
//    {
//
//        Patient thePatient = patientService.getById(patientId);
//        List<Prosthesis> prosthesisList = thePatient.getProstheses();
//        System.out.println(prosthesisList);
//        model.addAttribute("patient", thePatient);
//        model.addAttribute("patientlabwork", prosthesisList);
//        return "patient/patients-lab-work";
//    }


}
