package com.shilov.shilov.controller;

import com.shilov.shilov.entity.Deal;
import com.shilov.shilov.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/deal")
public class DealController {
    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("deals", dealService.getAll());
        return "deal/deal-list";
    }

    @GetMapping("/add")
    public String addEndpoint(@ModelAttribute Deal deal, Model model) {
        model.addAttribute("deal", deal);
        return "deal/add-deal";
    }

    @PostMapping("/add-item")
    public String add(@ModelAttribute Deal deal, BindingResult bindingResult, Model model) {
        dealService.create(deal);
        return "redirect:/deal";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Deal deal = dealService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("deal", deal);

        return "deal/edit-deal";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Deal deal, BindingResult result, Model model) {
        dealService.create(deal);
        return "redirect:/deal";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        dealService.deleteById(id);
        return "redirect:/deal";
    }
}
