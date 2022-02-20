package com.shilov.shilov.controller;

import com.shilov.shilov.entity.Client;
import com.shilov.shilov.service.ClientService;
import com.shilov.shilov.service.EmployeeService;
import com.shilov.shilov.service.OperationService;
import com.shilov.shilov.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final OperationService operationService;
    private final RequestService requestService;
    private final EmployeeService employeeService;

    @Autowired
    public ClientController(ClientService clientService, OperationService operationService, RequestService requestService, EmployeeService employeeService) {
        this.clientService = clientService;
        this.operationService = operationService;
        this.requestService = requestService;
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "client/client-list";
    }

    @GetMapping("/add")
    public String addEndpoint(@ModelAttribute Client client, Model model) {
        model.addAttribute("client", client);
        model.addAttribute("operations", operationService.getAll());
        model.addAttribute("requests", requestService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        return "client/add-client";
    }

    @PostMapping("/add-item")
    public String add(@ModelAttribute Client client, BindingResult bindingResult, Model model) {
        clientService.create(client);
        return "redirect:/client";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Client client = clientService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("client", client);
        model.addAttribute("operations", operationService.getAll());
        model.addAttribute("requests", requestService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        return "client/edit-client";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Client client, BindingResult result, Model model) {
        clientService.create(client);
        return "redirect:/client";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.deleteById(id);
        return "redirect:/client";
    }
}
