package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;
import tn.esprit.examen.nomPrenomClasseExamen.entities.ProjetDetail;

import java.util.List;

public interface IServiceEquipe {
    List<Equipe> getProjetDetails();
    Equipe retrieve(Long id);
    Equipe add(Equipe equipe);
    void remove(Equipe Id);
    Equipe modify(Equipe equipe);
    public void assignProjetToEquipe(Long projetId, Long equipeId);
    public void desaffecterProjetFromEquipe(Long projetId, Long equipeId);
    public void assignMultipleProjetsToEquipe(List<Long> projetIds, Long equipeId);
    }
