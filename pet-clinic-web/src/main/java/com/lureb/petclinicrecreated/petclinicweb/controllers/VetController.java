package com.lureb.petclinicrecreated.petclinicweb.controllers;

import com.lureb.petclinicrecreated.petclinicdata.model.Vet;
import com.lureb.petclinicrecreated.petclinicdata.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets", "/vets/index", "/vets/vetList.html", "/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/vetList";
    }

    @GetMapping({"/api/vets"})
    public ResponseEntity<Set<Vet>> listVetsJson() {
        return ResponseEntity.ok(vetService.findAll());
    }
}
