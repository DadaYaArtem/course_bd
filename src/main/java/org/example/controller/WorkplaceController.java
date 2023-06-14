package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.EquipWorkplaceMovement;
import org.example.model.Workplace;
import org.example.service.WorkplaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class WorkplaceController {

    private final WorkplaceService workplaceService;

    @GetMapping("/workplace/list")
    public ModelAndView workplacePage() throws IOException {
        ModelAndView modelAndView = new ModelAndView("list/workplacelist");

        List<Workplace> allWorkplaces = workplaceService.getAllWorkplaces();

        modelAndView.addObject("count",allWorkplaces.size());
        modelAndView.addObject("listOfWorkplaces", allWorkplaces);
        return modelAndView;
    }

    @GetMapping("/workplace/byEquipment")
    public ModelAndView workplaceByEquipmentPage(@RequestParam("idEquipW") Long number) throws IOException {
        ModelAndView modelAndView = new ModelAndView("workplacesByEquip");

        List<EquipWorkplaceMovement> allWorkplaces = workplaceService.getWorkplacesByEquip(number);
        System.out.println("allWorkplaces = " + allWorkplaces);

        modelAndView.addObject("count",allWorkplaces.size());
        modelAndView.addObject("listOfWorkplaces", allWorkplaces);
        return modelAndView;
    }
}
