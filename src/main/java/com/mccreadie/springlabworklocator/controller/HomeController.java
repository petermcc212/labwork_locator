package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Prosthesis;
import com.mccreadie.springlabworklocator.service.ProsthesisService;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {



	private final ProsthesisService prosthesisService;

	public HomeController(ProsthesisService prosthesisService) {
		this.prosthesisService = prosthesisService;
	}


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
