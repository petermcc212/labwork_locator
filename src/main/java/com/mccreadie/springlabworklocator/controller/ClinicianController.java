package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.service.ClinicianService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ClinicianController {

    private final ClinicianService clinicianService;



    public ClinicianController(ClinicianService clinicianService) {
        this.clinicianService = clinicianService;

    }

    @GetMapping("/addClinician")
    public String newClinicianForm(Model model){
        model.addAttribute("clinician", new Clinician());
        return "clinician/new-clinician-form";
    }


    @PostMapping("/processNewClinician")
    public String showClinicianData(@Valid @ModelAttribute Clinician clinician, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "clinician/new-clinician-form";
        }
        clinicianService.createNewClinician(clinician);
        return "redirect:showClinicians";
    }

    @GetMapping("/showClinicians")
    public String getClinicians(Model model){
        List<Clinician> clinicians = clinicianService.getAll();
        model.addAttribute("clinicians", clinicians);
        return "clinician/clinicians";
    }


    @Transactional
    @GetMapping("/viewCliniciansLabWork/{clinicianId}")
    public String viewPatientsLabWork(@PathVariable int clinicianId, Model model)
    {
        Clinician theClinician = clinicianService.getById(clinicianId);
        List<Prosthesis> prosthesisList = theClinician.getProstheses();
        model.addAttribute("clinician", theClinician);
        model.addAttribute("prostheisList", prosthesisList);
        return "clinician/clinicians-lab-work";
    }

}
