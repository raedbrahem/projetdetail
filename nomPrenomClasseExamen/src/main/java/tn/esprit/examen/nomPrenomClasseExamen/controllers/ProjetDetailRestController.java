package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Projet;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServices;



@RequiredArgsConstructor
@RequestMapping("examen")
@RestController
public class ProjetDetailRestController {
    @Autowired
    private IServices services;
    @PostMapping("/add-projet")
    public Projet add(@RequestBody Projet projet){
        return  services.add(projet);
    }

    @PostMapping("/ajouter-projet-et-projet-detail")
    public Projet addProjetAndProjetDetail(@RequestBody Projet p) {
        Projet projet = services.addProjetAndProjetDetailAndAssign(p);
        return projet;
    }
    @PostMapping("/add-projet-to-projet-detail/{projet-details-id}")
    public Projet addProjetAndAssignProjetToProjetDetail(
            @RequestBody Projet projet,
            @PathVariable("projet-details-id") Long projetDetailId) {
        return services.addProjetAndAssignProjetToProjetDetail(projet, projetDetailId);
    }
    @PutMapping("/desaffecter-projet-detail-from-projet/{projet-id}")
    public Projet DesaffecterProjetDetailFromProjet(@PathVariable("projet-id") Long projetId) {
        return services.DesaffecterProjetDetailFromProjet(projetId);
    }
}
