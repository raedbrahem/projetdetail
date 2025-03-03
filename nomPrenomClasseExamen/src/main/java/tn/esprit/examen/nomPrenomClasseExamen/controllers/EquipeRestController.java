package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServiceEquipe;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeRestController {

    @Autowired
    private IServiceEquipe equipeService;

    // Retrieve all equipes
    @GetMapping
    public List<Equipe> getAllEquipes() {
        return equipeService.getProjetDetails();
    }

    // Retrieve a single equipe by ID
    @GetMapping("/{id}")
    public Equipe getEquipeById(@PathVariable Long id) {
        return equipeService.retrieve(id);
    }

    // Add a new equipe
    @PostMapping("/add-equipe")
    public Equipe addEquipe(@RequestBody Equipe equipe) {
        return equipeService.add(equipe);
    }

    // Modify an existing equipe
    @PutMapping
    public Equipe updateEquipe(@RequestBody Equipe equipe) {
        return equipeService.modify(equipe);
    }

    // Delete an equipe by ID
    @DeleteMapping("/{id}")
    public void deleteEquipe(@PathVariable Long id) {
        Equipe equipe = equipeService.retrieve(id);
        if (equipe != null) {
            equipeService.remove(equipe);
        }
    }

    // Assign a projet to an equipe

    @PutMapping("/assign-projet/{projetId}/{equipeId}")
    public void assignProjetToEquipe(@PathVariable Long projetId, @PathVariable Long equipeId) {
        equipeService.assignProjetToEquipe(projetId, equipeId);
    }
    @PutMapping("/desaffecter-projet-from-equipe/{projetId}/{equipeId}")
    public void desaffecterProjetFromEquipe(@PathVariable Long projetId, @PathVariable Long equipeId) {
        equipeService.desaffecterProjetFromEquipe(projetId, equipeId);
    }


    @PutMapping("/assign-multiple-projets/{equipeId}")
    public void assignMultipleProjetsToEquipe(
            @PathVariable Long equipeId,
            @RequestBody List<Long> projetIds) {
        equipeService.assignMultipleProjetsToEquipe(projetIds, equipeId);
    }

}
