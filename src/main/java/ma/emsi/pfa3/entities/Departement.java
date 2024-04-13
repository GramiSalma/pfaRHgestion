package ma.emsi.pfa3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private int idDepartement;
    private String designationDepartement;
    @OneToMany(mappedBy = "departement")
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Collection<ResponsableRH> rhs;
    @OneToMany(mappedBy = "departement")
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Collection<Employe> employes;
}
