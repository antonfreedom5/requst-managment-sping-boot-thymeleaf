package com.shilov.shilov.controller;

import com.shilov.shilov.entity.Operation;
import com.shilov.shilov.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("operations", operationService.getAll());
        return "operation/operation-list";
    }

    @GetMapping("/add")
    public String addEndpoint(@ModelAttribute Operation operation, Model model) {
        model.addAttribute("operation", operation);
        return "operation/add-operation";
    }

    @PostMapping("/add-item")
    public String add(@ModelAttribute Operation operation, BindingResult bindingResult, Model model) {
        operationService.create(operation);
        return "redirect:/operation";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Operation operation = operationService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("operation", operation);

        return "operation/edit-operation";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Operation operation, BindingResult result, Model model) {
        operationService.create(operation);
        return "redirect:/operation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        operationService.deleteById(id);
        return "redirect:/operation";
    }
}
