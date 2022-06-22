package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Laboratory;
import com.mccreadie.springlabworklocator.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @GetMapping("/addLaboratory")
    public String newLaboratory(Model model){
        model.addAttribute("laboratory", new Laboratory());
        return "laboratories/new-laboratory-form";
    }

    @PostMapping("/processNewLaboratory")
    public String showLaboratoryData(@ModelAttribute Laboratory laboratory)
    {
        laboratoryService.save(laboratory);
        return "redirect:showLaboratories";
    }

    @GetMapping("/showLaboratories")
    public String getLaboratories(Model model){
        List<Laboratory> laboratories = laboratoryService.getAll();
        model.addAttribute("laboratories", laboratories);
        return "laboratories/laboratories";
    }




}
