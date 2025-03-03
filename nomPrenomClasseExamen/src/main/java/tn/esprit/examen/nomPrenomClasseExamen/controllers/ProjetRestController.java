package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.ProjetDetail;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServiceProjetDetail;

@RequiredArgsConstructor
@RequestMapping("examen")
@RestController
public class ProjetRestController {

    private final IServiceProjetDetail serviceProjetDetail; // ✅ Use constructor injection

    @PostMapping("/add-projetdetail")
    public ProjetDetail add(@RequestBody ProjetDetail projetDetail) {
        return serviceProjetDetail.add(projetDetail);
    }

    // ✅ Implement the endpoint to assign ProjetDetail to Projet
    @PutMapping("/affecter-projet-a-projet-details/{projet-id}/{projet-details-id}")
    public void affecterProjetAProjetDetail(
            @PathVariable("projet-id") Long projetId,
            @PathVariable("projet-details-id") Long projetDetailsId) {
        serviceProjetDetail.assignProjetDetailToProjet(projetId, projetDetailsId);
    }


}
