package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.service.ClinicianService;
import com.mccreadie.springlabworklocator.service.PatientService;
import com.mccreadie.springlabworklocator.service.ProsthesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private ClinicianService clinicianService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private ProsthesisService prosthesisService;


	@RequestMapping("/")
	public String getHome(Model model) {

		System.out.println("THE VERSION IS  " + SpringVersion.getVersion());

		List<Prosthesis> labworkDueToday = prosthesisService.workDueToday();
		List<Prosthesis> labworkDueTomorrow = prosthesisService.workDueTomorrow();
		List<Prosthesis> labworkOverdue = prosthesisService.workOverdue();


		model.addAttribute("labworkDueToday", labworkDueToday);
		model.addAttribute("labworkDueTomorrow", labworkDueTomorrow);
		model.addAttribute("labworkOverdue", labworkOverdue);

		return "home";

	}



	
}
