package tn.esprit.examen.nomPrenomClasseExamen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Projet;
import tn.esprit.examen.nomPrenomClasseExamen.entities.ProjetDetail;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.ProjetDetailRepository;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.ProjetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetDetailServiceImpl implements IServiceProjetDetail {

    @Autowired
    private ProjetDetailRepository projetDetailRepository;

    @Autowired
    private ProjetRepository projetRepository; // ✅ Inject ProjetRepository

    @Override
    public List<ProjetDetail> getProjetDetails() {
        return projetDetailRepository.findAll();
    }

    @Override
    public ProjetDetail retrieve(Long id) {
        return projetDetailRepository.findById(id).orElse(null);
    }

    @Override
    public ProjetDetail add(ProjetDetail projetDetail) {
        return projetDetailRepository.save(projetDetail);
    }

    @Override
    public void remove(Long id) {
        projetDetailRepository.deleteById(id);
    }

    @Override
    public ProjetDetail modify(ProjetDetail projetDetail) {
        return projetDetailRepository.save(projetDetail);
    }

    // ✅ Implement assignProjetDetailToProjet method
    @Override
    public void assignProjetDetailToProjet(Long projetId, Long projetDetailId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet not found"));
        ProjetDetail projetDetail = projetDetailRepository.findById(projetDetailId)
                .orElseThrow(() -> new RuntimeException("ProjetDetail not found"));

        // Set the relation (parent-child)
        projet.setProjetDetail(projetDetail);
        projetRepository.save(projet); // Save changes
    }
}
