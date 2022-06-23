package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Laboratory;
import com.mccreadie.springlabworklocator.service.LaboratoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LaboratoryController {

    private final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @GetMapping("/addLaboratory")
    public String newLaboratory(Model model){

        model.addAttribute("laboratory", new Laboratory());
        return "laboratories/new-laboratory-form";
    }

    @PostMapping("/processNewLaboratory")
    public String showLaboratoryData(@Valid @ModelAttribute Laboratory laboratory, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return"laboratories/new-laboratory-form";
        }
        try{
            laboratoryService.save(laboratory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:showLaboratories";
    }

    @GetMapping("/showLaboratories")
    public String getLaboratories(Model model){
        List<Laboratory> laboratories = laboratoryService.getAll();
        model.addAttribute("laboratories", laboratories);
        return "laboratories/laboratories";
    }




}
