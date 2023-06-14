package org.example.controller;

import org.example.model.Employee;
import org.example.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/employee/list")
    public ModelAndView employeePage() throws IOException {
        ModelAndView modelAndView = new ModelAndView("list/employeelist");

        List<Employee> allEmployees = employeeService.getAllEmployee();

        modelAndView.addObject("count",allEmployees.size());
        modelAndView.addObject("listOfEmployees", allEmployees);
        return modelAndView;
    }

    @GetMapping("/employee/byEquipment")
    public ModelAndView getEmployByEquip(@RequestParam("idEquip") Long number, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("employeeByEquip");

        List<Employee> allRevals = employeeService.getEmployeeByEquipmentId(number);

        modelAndView.addObject("count",allRevals.size());
        modelAndView.addObject("listOfEmployees", allRevals);
        return modelAndView;
    }
}
