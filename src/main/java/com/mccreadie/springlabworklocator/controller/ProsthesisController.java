package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.model.Laboratory;
import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.service.ClinicianService;
import com.mccreadie.springlabworklocator.service.LaboratoryService;
import com.mccreadie.springlabworklocator.service.PatientService;
import com.mccreadie.springlabworklocator.service.ProsthesisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProsthesisController {

    private final LaboratoryService laboratoryService;

    private final ClinicianService clinicianService;

    private final ProsthesisService prosthesisService;

    private final PatientService patientService;

    public ProsthesisController(LaboratoryService laboratoryService, ClinicianService clinicianService, ProsthesisService prosthesisService, PatientService patientService) {
        this.laboratoryService = laboratoryService;
        this.clinicianService = clinicianService;
        this.prosthesisService = prosthesisService;
        this.patientService = patientService;
    }

    @Transactional
    @GetMapping("/addProsthetic/{patientId}")
    public String ShowLabWork(@PathVariable int patientId, Model model){
        List<Clinician> clinicians = clinicianService.getAll();
        List<Laboratory> laboratories = laboratoryService.getAll();
        Patient patient = patientService.getById(patientId);

        model.addAttribute("patient", patient);
        model.addAttribute("clinicians", clinicians);
        model.addAttribute("prosthesis", new Prosthesis());
        model.addAttribute("laboratories", laboratories);
        return "prosthesis/prosthetic-form";
    }




    @PostMapping("/processLabWork")
    public String addLabWork(@Valid @ModelAttribute Prosthesis prosthesis, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            int patientId = prosthesis.getPatient().getId();
            model.addAttribute("patient", patientService.getById(patientId));
            model.addAttribute("clinicians", clinicianService.getAll());
            model.addAttribute("prosthesis", prosthesis);
            model.addAttribute("laboratories", laboratoryService.getAll());

            return "prosthesis/prosthetic-form";
        }
        prosthesisService.save(prosthesis);
        return ("redirect:/");
    }

    @Transactional
    @GetMapping("/editProsthesis/{prosId}")
    public String editProsthesis(@PathVariable int prosId, Model model){
        Prosthesis theProsthesis = prosthesisService.getById(prosId);

        List<Laboratory> laboratories = laboratoryService.getAll();
        model.addAttribute("laboratories", laboratories);


        model.addAttribute("prosthesis", theProsthesis);

        List<Clinician> clinicians = clinicianService.getAll();
        model.addAttribute("clinicians", clinicians);

        Patient thePatient = theProsthesis.getPatient();
        model.addAttribute("patient", thePatient);

        List<Clinician>theClinician = new ArrayList<>();
        theClinician.add(theProsthesis.getClinician());
        model.addAttribute("clinician", theClinician);

        return "prosthesis/edit-prosthesis";
    }

    @GetMapping("/labWorkDueTomorrow")
    public String labWorkDueTomorrow(){
        List<Prosthesis> labWorkDueTomorrow = prosthesisService.workDueTomorrow();
        System.out.println("THIS IS ALL THE LAB WORK DUE TOMORROW");
        System.out.println("SIZE: " + labWorkDueTomorrow.size());
        for (Prosthesis p : labWorkDueTomorrow)
        {
            System.out.println(p.getPatient().getFirstName() + p.getPatient().getLastName());
        }
        return "redirect:/";
    }


    @GetMapping("/labWorkDueToday")
    public String labWorkDueToday(){

        List<Prosthesis> labWorkDueToday = prosthesisService.workDueToday();
        if(labWorkDueToday != null)
        {
            System.out.println("THIS IS ALL THE LAB WORK DUE TODAY");
            for (Prosthesis p : labWorkDueToday)
            {
                System.out.println(p.getPatient().getFirstName() + p.getPatient().getLastName());
            }
        }
        return "redirect:/";
    }



}
