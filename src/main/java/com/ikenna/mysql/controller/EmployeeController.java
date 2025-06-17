package com.ikenna.mysql.controller;

import com.ikenna.mysql.dao.EmployeeDao;
import com.ikenna.mysql.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeDao dao;
    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", dao.getAll());
        return "list";
    }
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add";
    }
    @PostMapping("/add")
    public String save(@ModelAttribute Employee employee) {
        dao.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("employee", dao.getById(id));
        return "edit";
    }
    @PostMapping("/edit")
    public String update(@ModelAttribute Employee employee) {
        dao.update(employee);
        return "redirect:/employees";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/employees";
    }
}
