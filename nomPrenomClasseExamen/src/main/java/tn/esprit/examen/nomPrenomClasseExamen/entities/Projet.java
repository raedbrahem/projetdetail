package tn.esprit.examen.nomPrenomClasseExamen.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sujet;

    @OneToOne(cascade = CascadeType.ALL)
    private ProjetDetail projetDetail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public ProjetDetail getProjetDetail() {
        return projetDetail;
    }

    public void setProjetDetail(ProjetDetail projetDetail) {
        this.projetDetail = projetDetail;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    @ManyToMany
    @JoinTable(
            name = "projet_equipes",  // Name of the join table
            joinColumns = @JoinColumn(name = "projet_id"),  // Foreign key to Projet
            inverseJoinColumns = @JoinColumn(name = "equipe_id")  // Foreign key to Equipe
    )
    private List<Equipe> equipes;

}
