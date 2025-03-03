package tn.esprit.examen.nomPrenomClasseExamen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Projet;
import tn.esprit.examen.nomPrenomClasseExamen.entities.ProjetDetail;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.ProjetDetailRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.ProjetRepository;

import java.util.List;

@Service
public class ProjetServiceImpl implements  IServices{
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ProjetDetailRepository projetDetailRepository;
    @Override
    public List<Projet> retrieveall() {
        return projetRepository.findAll();
    }

    @Override
    public Projet retrieve(Long projetid) {
        return projetRepository.findById(projetid).get();
    }

    @Override
    public Projet add(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public void remove(Long projetid) {
        projetRepository.deleteById(projetid);
    }

    @Override
    public Projet modify(Projet foyer) {
        return projetRepository.save(foyer);
    }
    @Override
    public Projet addProjetAndProjetDetailAndAssign(Projet projet) {
        if (projet.getProjetDetail() != null) {
            projet.getProjetDetail().setProjet(projet); // Assign the Projet to ProjetDetail
        }
        return projetRepository.save(projet); // Save Projet (which will save ProjetDetail as well)
    }

    @Override
    public Projet addProjetAndAssignProjetToProjetDetail(Projet projet, Long projetDetailId) {
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId).get();
// on set le fils dans le parent :
        projet.setProjetDetail(projetDetail);
        return projetRepository.save(projet);
    }

    @Override
    public Projet DesaffecterProjetDetailFromProjet(Long projetId) {
        Projet projet = projetRepository.findById(projetId).get();
        projet.setProjetDetail(null);
        return projetRepository.save(projet);
    }




}
