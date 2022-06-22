package com.mccreadie.springlabworklocator.controller;


import com.mccreadie.springlabworklocator.model.Clinician;
import com.mccreadie.springlabworklocator.model.Patient;
import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.service.ClinicianService;
import com.mccreadie.springlabworklocator.service.PatientService;
import com.mccreadie.springlabworklocator.service.ProsthesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicianService clinicianService;

    @Autowired
    private ProsthesisService prosthesisService;





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
    public String processNewPatientFromNewProsthesis(@ModelAttribute Patient patient, Model model) {
        patientService.save(patient);
        model.addAttribute("patient", patient);
        String patientID = Integer.toString(patient.getId());

                return"redirect:/addProsthetic/" + patientID ;
    }


    @PostMapping("/processNewPatient")
    public String processNewPatient(@ModelAttribute Patient patient)
    {
        patientService.save(patient);
        return"home";
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
        System.out.println(prosthesisList);
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

