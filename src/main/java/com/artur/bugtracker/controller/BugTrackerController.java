package com.artur.bugtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BugTrackerController {

    @GetMapping("/")
    public String date(Model model) {
        model.addAttribute("Date", getDate());
        return "signInPage";
    }

    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @RequestMapping("/navbar")
    public String navbar() {
        return "navbar";
    }

    @GetMapping("/login")
    public String signInPage() {
        return "signInPage";
    }

    @RequestMapping("/mainLayout")
    public String mainLayout() {
        return "mainLayout";
    }

    @RequestMapping("/myAccount")
    public String myAccount() {
        return "myAccount";
    }
}