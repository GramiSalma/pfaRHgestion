package ma.emsi.pfa3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employe{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idEmploye;
    private float solde;
    private String password;
    @OneToMany(mappedBy = "employe")
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Collection<Absence> absences;
    @OneToMany(mappedBy = "employe")
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Collection<Conge> conges;
    @ManyToOne
    private Departement departement;
    private int cin;
    private LocalDate dateNaissance;
    private LocalDate dateRecrutement;
    private  String email;
    private String nom;
    private String photo;
    private  String prenom;
    private  double salaire;
    private String poste;
    private String sexe;
    private  String situationFamiliale;
    private  long telephone;
    public void afficherSoldeEmploye() {

        // Calculer le solde restant en jours et heures significatives
        int jours = (int) this.solde;
        int heures = (int) ((this.solde - jours) * 8);
        if (heures==0){
            System.out.println(jours + " j " );
        }else{
            System.out.println(jours + " j " + heures + " h");
        }


    }



}
