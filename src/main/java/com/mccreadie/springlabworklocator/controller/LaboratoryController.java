package com.mccreadie.springlabworklocator.controller;

import com.mccreadie.springlabworklocator.model.Laboratory;
import com.mccreadie.springlabworklocator.model.LaboratoryProduct;
import com.mccreadie.springlabworklocator.service.LaboratoryProductService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class LaboratoryController {

    private final LaboratoryService laboratoryService;
    private final LaboratoryProductService laboratoryProductService;

    public LaboratoryController(LaboratoryService laboratoryService,
                                LaboratoryProductService laboratoryProductService) {
        this.laboratoryService = laboratoryService;
        this.laboratoryProductService = laboratoryProductService;
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

    @GetMapping("/labServices/{laboratoryId}")
    public String labServices(@PathVariable int laboratoryId, Model model){
        Laboratory laboratory = laboratoryService.getById(laboratoryId);


        List<LaboratoryProduct> laboratoryProducts =
                laboratoryProductService.getAll();
        List<LaboratoryProduct> filteredLaboratoryProducts = new ArrayList<>();

        // Return only products of current lab
        for(LaboratoryProduct product : laboratoryProducts){
            if(product.getLaboratory().getId() == laboratoryId){
                filteredLaboratoryProducts.add(product);
            }
        }

        System.out.println("SIZE OF LIST IS " + laboratoryProducts.size());

        model.addAttribute("laboratory", laboratory);
        model.addAttribute("laboratoryProducts", filteredLaboratoryProducts);
        return "laboratories/laboratory-services";
    }

    @GetMapping("/addLabService/{laboratoryId}")
    public String addLabService(@PathVariable int laboratoryId, Model model){
        Laboratory laboratory = laboratoryService.getById(laboratoryId);


        model.addAttribute("laboratory", laboratory);
        model.addAttribute("labService", new LaboratoryProduct());


        return "laboratories/new-laboratory-service-form";
    }

    @PostMapping("/processNewService")
    public String processNewService(@Valid @ModelAttribute LaboratoryProduct labService ,
                                    BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            // HANDLE ERRORS HERE
        }
        try{
            laboratoryProductService.save(labService);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }






}
