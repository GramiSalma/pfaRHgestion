package ma.emsi.pfa3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idAbsence;
    @Temporal(TemporalType.DATE)
    private LocalDate dateAbsence;
    @Temporal(TemporalType.TIME)
    private LocalTime heureDebutAbsence;
    @Temporal(TemporalType.TIME)
    private LocalTime heureFinAbsence;
    @Enumerated(EnumType.STRING)
    private StatusCNG StatutAbsence=StatusCNG.Pending;
    private String justificationAbsence;
    @Temporal(TemporalType.DATE)
    private  LocalDate dateDemandeAbsence;
    private long dureeAbsence;
    @ManyToOne
    private Employe employe;

    @ManyToOne
    private ResponsableRH rh;
    public long DureeAbsence(){
        if(this.heureDebutAbsence!=null && this.heureFinAbsence!=null){
        this.dureeAbsence = ChronoUnit.HOURS.between(this.heureDebutAbsence, this.heureFinAbsence);
        return this.dureeAbsence;}
        else{
            return 8;
        }
    }



}
