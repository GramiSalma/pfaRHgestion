package ma.emsi.pfa3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conge {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idConge;
    @Temporal(TemporalType.DATE)
    private LocalDate dateDebutConge;
    @Temporal(TemporalType.DATE)
    private  LocalDate dateFinConge;
    @Temporal(TemporalType.DATE)
    private LocalDate dateDemandeConge;
    @Enumerated(EnumType.STRING)
    private StatusCNG statutConge=StatusCNG.Pending;
    @Enumerated(EnumType.STRING)
    private  TypeConge typeConge;
    private long duree;
    @ManyToOne

    private Employe employe;
    @ManyToOne
    private ResponsableRH rh;

    public long DureeConge(){
        this.duree = ChronoUnit.DAYS.between(dateDebutConge, dateFinConge);
        return this.duree;
    }






}
