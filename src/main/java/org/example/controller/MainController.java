package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.model.*;
import org.example.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final ProviderService providerService;
    private final PurchaseService purchaseService;
    private final CategoryService categoryService;
    private final WorkplaceService workplaceService;
    private final RepairmentService repairmentService;
    private final RevaluationService revaluationService;
    private final UserActionService userActionService;

    @GetMapping("/")
    public String showHomePage(HttpServletRequest request) {

        return "mainADMIN";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView processRegister(@RequestParam(name = "setEmail") String email,
                                        @RequestParam(name = "setPassword") String password) {
        ModelAndView modelAndView;

        if (userService.findByUsername(email).isPresent()) {
            modelAndView = new ModelAndView("register");
            modelAndView.addObject("message", "User with this email already exists");
        } else {
            userService.createUser(email, password, User.RoleType.USER1);
            modelAndView = new ModelAndView("login");
        }
        return modelAndView;

    }

    @GetMapping("/journal")
    public ModelAndView journal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        System.out.println("userActionService.getAllActions() = " + userActionService.getAllActions());

        if (byUsername.getRole() == User.RoleType.ADMIN) {
            ModelAndView modelAndView = new ModelAndView("journal");
            modelAndView.addObject("journal", userActionService.getAllActions());
            return modelAndView;
        }else {
            response.getWriter().write("restricted");
            return null;
        }
    }

    @GetMapping("/equip/list")
    public ModelAndView equipPage(HttpServletRequest request) throws IOException {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();
        userActionService.trackPageVisit(byUsername.getId(), "equip/list");

        if (byUsername.getRole() == User.RoleType.USER1) {
            ModelAndView modelAndView = new ModelAndView("list/equiplist");

            List<Equipment> allEquipment = equipmentService.getAllEquipment();
            modelAndView.addObject("count", allEquipment.size());
            modelAndView.addObject("listOfEquip", allEquipment);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("list/equiplistModif");

            List<Equipment> allEquipment = equipmentService.getAllEquipment();
            modelAndView.addObject("count", allEquipment.size());
            modelAndView.addObject("listOfEquip", allEquipment);
            return modelAndView;
        }
    }

    @GetMapping("/provider/list")
    public ModelAndView providerPage(HttpServletRequest request) throws IOException {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackPageVisit(byUsername.getId(), "provider/list");
        ModelAndView modelAndView = new ModelAndView("list/providerlist");

        List<Provider> allProviders = providerService.getAllProviders();

        modelAndView.addObject("count", allProviders.size());
        modelAndView.addObject("listOfProviders", allProviders);
        return modelAndView;
    }

    @GetMapping("/purchase/list")
    public ModelAndView purchasePage(HttpServletRequest request) throws IOException {

        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackPageVisit(byUsername.getId(), "purchase/list");

        ModelAndView modelAndView = new ModelAndView("list/purchaselist");

        List<Purchase> allPurchases = purchaseService.getAllPurchase();

        modelAndView.addObject("count", allPurchases.size());
        modelAndView.addObject("listOfPurchases", allPurchases);
        return modelAndView;
    }

    @GetMapping("/category/list")
    public ModelAndView categoryPage(HttpServletRequest request) throws IOException {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackPageVisit(byUsername.getId(), "category/list");

        ModelAndView modelAndView = new ModelAndView("list/categorylist");

        List<Category> allCategories = categoryService.getAllCategories();

        modelAndView.addObject("count", allCategories.size());
        modelAndView.addObject("listOfCategories", allCategories);
        return modelAndView;
    }

    @GetMapping("/repairment/list")
    public ModelAndView repairmentPage(HttpServletRequest request) throws IOException {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackPageVisit(byUsername.getId(), "repairment/list");

        ModelAndView modelAndView = new ModelAndView("list/repairmentlist");

        List<Repairment> allRepairments = repairmentService.getAllRepairments();

        modelAndView.addObject("count", allRepairments.size());
        modelAndView.addObject("listOfRepairs", allRepairments);
        return modelAndView;
    }

    @GetMapping("/revaluation/list")
    public ModelAndView revaluationPage(HttpServletRequest request) throws IOException {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackPageVisit(byUsername.getId(), "revaluation/list");

        ModelAndView modelAndView = new ModelAndView("list/revaluationlist");

        List<Revaluation> allRevals = revaluationService.getAllRevals();

        modelAndView.addObject("count", allRevals.size());
        modelAndView.addObject("listOfRevals", allRevals);
        return modelAndView;
    }

    @GetMapping("/revaluation/byEquipment")
    public ModelAndView revalByEquip(@RequestParam("idEquipRev") Long number, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackPageVisit(byUsername.getId(), "/revaluation/byEquipment");

        ModelAndView modelAndView = new ModelAndView("revaluationByEquip");

        List<Revaluation> allRevals = revaluationService.getAllRevalsByEquip(number);
        System.out.println("allRevals = " + allRevals);

        modelAndView.addObject("count", allRevals.size());
        modelAndView.addObject("listOfRevals", allRevals);
        return modelAndView;
    }

    @GetMapping("/repairment/byEquipment")
    public ModelAndView repairByEquip(@RequestParam("idEquipRep") Long number, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackPageVisit(byUsername.getId(), "/repairment/byEquipment");

        ModelAndView modelAndView = new ModelAndView("repairmentByEquip");

        List<Repairment> allRepairs = repairmentService.getAllRepairmentsByEquip(number);
        System.out.println("allRepairs = " + allRepairs);

        modelAndView.addObject("count", allRepairs.size());
        modelAndView.addObject("listOfRepairs", allRepairs);
        return modelAndView;
    }

    @PostMapping("/equip/delete/{id}")
    public void deleteNote(@PathVariable("id") Long id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackDataDelete(byUsername.getId(), "equipment with id: " + id);

        equipmentService.deleteEquipById(id);

        response.sendRedirect("/equip/list");
    }

    @GetMapping("/equip/edit/{id}")
    public ModelAndView editEquip(@PathVariable("id") Long id, HttpServletRequest request) {


        Equipment equipment = equipmentService.findEquipmentById(id).get();
        List<Category> allCategories = categoryService.getAllCategories();
        List<Workplace> allWorkplaces = workplaceService.getAllWorkplaces();


        ModelAndView modelAndView = new ModelAndView("equip-edit");
        modelAndView.addObject("equipment", equipment);
        modelAndView.addObject("categories", allCategories);
        modelAndView.addObject("workplaces", allWorkplaces);

        return modelAndView;

    }

    @PostMapping("/equip/edit/{id}/save")
    public void updateEquip(@PathVariable("id") Long id,
                            Equipment equipment,
                            HttpServletResponse response,
                            HttpServletRequest request) throws IOException {

        String username = request.getUserPrincipal().getName();
        User byUsername = userService.findByUsername(username).get();

        userActionService.trackDataChange(byUsername.getId(), "equipment name: " + equipment.getName());
        userActionService.trackDataChange(byUsername.getId(), "equipment model: " + equipment.getModel());
        userActionService.trackDataChange(byUsername.getId(), "equipment description: " + equipment.getDescription());
        userActionService.trackDataChange(byUsername.getId(), "equipment condition: " + equipment.getCondition());
        userActionService.trackDataChange(byUsername.getId(), "equipment category: " + equipment.getCategory());
        userActionService.trackDataChange(byUsername.getId(), "equipment workplace: " + equipment.getWorkplace());


        equipmentService.updateEquip(id, equipment.getName(), equipment.getModel(),
                equipment.getDescription(), equipment.getCondition(), equipment.getCategory(), equipment.getWorkplace());

        response.sendRedirect("/equip/list");

    }

}
