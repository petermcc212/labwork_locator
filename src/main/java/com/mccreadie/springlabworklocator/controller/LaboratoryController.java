package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Laboratory;
import com.mccreadie.springlabworklocator.service.LaboratoryService;
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
    public String showLaboratoryData(@Valid @ModelAttribute Laboratory laboratory, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            return"laboratories/new-laboratory-form";
        }
        try{
            laboratoryService.save(laboratory);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Laboratory> laboratories = laboratoryService.getAll();
        model.addAttribute("laboratories", laboratories);
        return "redirect:showAllLaboratories";
    }





    @GetMapping("/showAllLaboratories")
    public String getLaboratories(Model model){
        List<Laboratory> laboratories = laboratoryService.getAll();
        model.addAttribute("laboratories", laboratories);
        return "laboratories/view-all-laboratories";
    }

    @Transactional
    @GetMapping("/editLaboratory/{laboratoryId}")
    public String editLaboratoryDetails(@PathVariable int laboratoryId, Model model){
        Laboratory theLaboratory = laboratoryService.getById(laboratoryId);
        model.addAttribute(theLaboratory);
        List<Laboratory> laboratories = laboratoryService.getAll();
        model.addAttribute("laboratories", laboratories);
        return "laboratories/edit-laboratory-form";

    }







}
