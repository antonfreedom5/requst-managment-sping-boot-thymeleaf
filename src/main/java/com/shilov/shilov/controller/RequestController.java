package com.shilov.shilov.controller;

import com.shilov.shilov.entity.Request;
import com.shilov.shilov.service.DealService;
import com.shilov.shilov.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;
    private final DealService dealService;

    @Autowired
    public RequestController(RequestService requestService, DealService dealService) {
        this.requestService = requestService;
        this.dealService = dealService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("requests", requestService.getAll());
        return "request/request-list";
    }

    @GetMapping("/add")
    public String addEndpoint(@ModelAttribute Request request, Model model) {
        model.addAttribute("request", request);
        model.addAttribute("deals", dealService.getAll());
        return "request/add-request";
    }

    @PostMapping("/add-item")
    public String add(@ModelAttribute Request request, BindingResult bindingResult, Model model) {
        System.out.println(request.getDeal());
        requestService.create(request);
        return "redirect:/request";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Request request = requestService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("request", request);
        model.addAttribute("deals", dealService.getAll());
        return "request/edit-request";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Request request, BindingResult result, Model model) {
        requestService.create(request);
        return "redirect:/request";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        requestService.deleteById(id);
        return "redirect:/request";
    }
}
