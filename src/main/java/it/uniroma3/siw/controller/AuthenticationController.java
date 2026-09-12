package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.service.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {

    private final CredenzialiService credServ;

    public AuthenticationController(
            CredenzialiService credServ) {

        this.credServ = credServ;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Utente());
        model.addAttribute("credenziali", new Credenziali());
        return "register";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(
            Model model,
            @Valid @ModelAttribute("user") Utente user,
            BindingResult userBindingResult,
            @Valid @ModelAttribute("credenziali") Credenziali credenziali,
            BindingResult credentialsBindingResult,
            HttpServletRequest request) throws ServletException {

        // First check validation errors
        if (userBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
            return "register";
        }

        // Then check if username is already in use
        if (credServ.existsByUsername(credenziali.getUsername())) {
            model.addAttribute("error", "Username già in uso");
            return "register";
        }

        // Username is available, so register the user
        String rawPassword = credenziali.getPassword();

        credenziali.setUser(user);
        credServ.saveCredentials(credenziali);

        if (request.getUserPrincipal() != null) {
            request.logout();
        }

        request.login(credenziali.getUsername(), rawPassword);

        return "redirect:/";
    }
}

