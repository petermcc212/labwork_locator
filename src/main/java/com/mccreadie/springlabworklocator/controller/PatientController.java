package com.mccreadie.springlabworklocator.controller;


import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.service.ClinicianService;
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
import java.util.List;

@Controller
public class PatientController {

    private final PatientService patientService;

    private final ClinicianService clinicianService;

    private final ProsthesisService prosthesisService;

    public PatientController(PatientService patientService, ClinicianService clinicianService, ProsthesisService prosthesisService) {
        this.patientService = patientService;
        this.clinicianService = clinicianService;
        this.prosthesisService = prosthesisService;
    }


    @GetMapping("/addPatient")
    public String newPatient(Model model){
        List<Clinician> clinicians = clinicianService.getAll();
        List<Prosthesis> prosthesisList = prosthesisService.getAll();
        model.addAttribute("clinicians", clinicians);
        model.addAttribute("prosthesis", prosthesisList);
        model.addAttribute("patient", new Patient());
        return "patient/new-patient-form";
    }

    @GetMapping("/addPatientDuringProsthesisCreation")
    public String addPatientDuringProsthesisCreation(Model model){
        List<Clinician> clinicians = clinicianService.getAll();
        List<Prosthesis> prosthesisList = prosthesisService.getAll();
        model.addAttribute("clinicians", clinicians);
        model.addAttribute("prosthesis", prosthesisList);
        model.addAttribute("patient", new Patient());
        return "patient/new-patient-during-pros-creation-form";
    }




    @PostMapping(value = "/processNewPatientFromNewProsthesis")
    public String processNewPatientFromNewProsthesis(@Valid @ModelAttribute Patient patient,
                                                     Model model, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
         List<Clinician> clinicians = clinicianService.getAll();
         model.addAttribute("clinicians", clinicians);
         return "patient/new-patient-during-pros-creation-form";
        }
        patientService.save(patient);
        model.addAttribute("patient", patient);
        String patientID = Integer.toString(patient.getId());
        return"redirect:/addProsthetic/" + patientID ;
    }


    @PostMapping("/processNewPatient")
    public String processNewPatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            List<Clinician> clinicians = clinicianService.getAll();
            model.addAttribute("clinicians", clinicians);
            return "patient/new-patient-form";
        }
        patientService.save(patient);
        return"redirect:/";
    }


    @GetMapping("/selectPatient")
    public String selectPatient(Model model){
        List<Patient> patients = patientService.getAll();
        model.addAttribute("patients", patients);
        return "patient/patient-list";
    }


    @GetMapping("/viewPatients")
    public String getPatients(Model model){
        List<Patient> patients = patientService.getAll();
        model.addAttribute("patients", patients);
        return "patient/patients-summary";
    }

    @Transactional
    @GetMapping("/viewPatientsLabWork/{patientId}")
    public String viewPatientsLabWork(@PathVariable int patientId, Model model)
    {

        Patient thePatient = patientService.getById(patientId);
        List<Prosthesis> prosthesisList = thePatient.getProstheses();
        model.addAttribute("patient", thePatient);
        model.addAttribute("patientlabwork", prosthesisList);
        return "patient/patients-lab-work";
    }

    @Transactional
    @GetMapping("/editPatientDetails/{patientId}")
    public String editPatientDetails(@PathVariable int patientId, Model model){
        Patient thePatient = patientService.getById(patientId);
        System.out.println(thePatient);
        model.addAttribute("patient", thePatient);
        model.addAttribute("clinicians", clinicianService.getAll());
        return "patient/edit-patient-form";
    }

    @GetMapping("/search")
    public String search(Model model,  String keyword){

        List<Patient> thePatientsFound = patientService.nameStartsWith(keyword);
        model.addAttribute("patients", thePatientsFound);
        return "patient/patients-search";

    }

}

