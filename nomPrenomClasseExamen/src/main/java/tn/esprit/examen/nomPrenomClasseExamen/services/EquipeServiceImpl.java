package tn.esprit.examen.nomPrenomClasseExamen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Projet;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.EquipeRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.ProjetRepository;

import java.util.List;

@Service
public class EquipeServiceImpl implements IServiceEquipe {

    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private ProjetRepository projetRepository;

    @Override
    public List<Equipe> getProjetDetails() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe retrieve(Long id) {
        return equipeRepository.findById(id).orElse(null);
    }

    @Override
    public Equipe add(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public void remove(Equipe Id) {
        equipeRepository.delete(Id);
    }
    @Override
    public Equipe modify(Equipe equipe) {
        return equipeRepository.save(equipe);
    }
    @Override
    public void assignProjetToEquipe(Long projetId, Long equipeId) {
        Projet projet = projetRepository.findById(projetId).orElse(null);
        Equipe equipe = equipeRepository.findById(equipeId).orElse(null);

        if (projet != null && equipe != null) {
            // Update both sides of the relationship
            equipe.getProjets().add(projet);
            projet.getEquipes().add(equipe); // Ensure projet knows its equipes

            equipeRepository.save(equipe);
            projetRepository.save(projet);  // Save projet to persist the change
        }
    }
    @Override
    public void desaffecterProjetFromEquipe(Long projetId, Long equipeId) {
        Projet projet = projetRepository.findById(projetId).orElse(null);
        Equipe equipe = equipeRepository.findById(equipeId).orElse(null);

        if (projet != null && equipe != null) {
            equipe.getProjets().remove(projet);
            projet.getEquipes().remove(equipe);

            equipeRepository.save(equipe);
            projetRepository.save(projet);
        }
    }


    @Override
    public void assignMultipleProjetsToEquipe(List<Long> projetIds, Long equipeId) {
        Equipe equipe = equipeRepository.findById(equipeId).orElse(null);

        if (equipe != null) {
            List<Projet> projets = projetRepository.findAllById(projetIds);

            for (Projet projet : projets) {
                equipe.getProjets().add(projet);
                projet.getEquipes().add(equipe); // Ensure projet knows its equipes
            }

            equipeRepository.save(equipe);
            projetRepository.saveAll(projets);  // Save all projets to persist the changes
        }
    }


}
