package ma.emsi.pfa3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  ResponsableRH  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  idRH;
    private  String email;
    private String nom;
    private String photo;
    private  String prenom;
    @OneToMany(mappedBy = "rh")
    private Collection<Absence> absences;
    @OneToMany(mappedBy = "rh")
    private Collection<Conge> conges;
    @ManyToOne

    private Departement departement;

}
