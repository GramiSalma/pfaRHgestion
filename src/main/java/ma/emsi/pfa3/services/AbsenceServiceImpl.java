package ma.emsi.pfa3.services;

import ma.emsi.pfa3.entities.Absence;
import ma.emsi.pfa3.entities.Employe;
import ma.emsi.pfa3.entities.ResponsableRH;
import ma.emsi.pfa3.entities.StatusCNG;
import ma.emsi.pfa3.repositories.AbsenceRepository;
import ma.emsi.pfa3.repositories.EmployeRepository;
import ma.emsi.pfa3.repositories.ResponsableRHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class AbsenceServiceImpl implements IAbsenceService{
    private AbsenceRepository absenceRepository;
    private EmployeRepository employeRepository;

    private ResponsableRHRepository responsableRHRepository;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository, EmployeRepository employeRepository, ResponsableRHRepository responsableRHRepository) {
        this.absenceRepository = absenceRepository;
        this.employeRepository = employeRepository;
        this.responsableRHRepository = responsableRHRepository;
    }


    @Override
    public Absence addAbsence(int idEmploye,Absence absence) {

       if (absence.getDateAbsence().isBefore(LocalDate.now())) {
           throw new IllegalArgumentException("Date d'absence invalide");
       }
        if ((absence.getHeureDebutAbsence() != null && absence.getHeureFinAbsence() != null) &&
                ((absence.getHeureDebutAbsence().isAfter(absence.getHeureFinAbsence())) ||
                        (absence.getHeureDebutAbsence().isBefore(LocalTime.of(9, 0)) &&
                                absence.getHeureFinAbsence().isAfter(LocalTime.of(17, 0))))) {
            throw new IllegalArgumentException("Heures incohérentes.");
        }
         if(absence.getStatutAbsence()== StatusCNG.Approved){
             ajusterSoldeEtAfficherMessage(absence.getEmploye(), absence);
         }

        ResponsableRH defaultRh=responsableRHRepository.findByNom("Default");
        absence.setRh(defaultRh);
        Employe demandeurAbsence = employeRepository.findById(idEmploye)
                .orElseThrow(() -> new IllegalArgumentException("Employé non trouvé"));
        absence.setEmploye(demandeurAbsence);
        absence.setDateDemandeAbsence(LocalDate.now());
        // Enregistrer l'absence dans le repository
        return absenceRepository.save(absence);}


    @Override
    public Absence updateAbsence(Absence absence, int id) {
        return absenceRepository.findById(id).map(st-> {
            st.setDateAbsence(absence.getDateAbsence());
            return absenceRepository.save(st);
        }).orElseThrow(()->new IllegalArgumentException("Absence n'existe pas"));

    }

    @Override
    public Absence getAbsenceById(int id) {
        return absenceRepository.findById(id).orElseThrow(()->new IllegalArgumentException("pas d'absence avec cet id"));
    }

    @Override
    public List<Absence> getAbsences() {

        return absenceRepository.findAll();
    }
    public void ajusterSoldeEtAfficherMessage(Employe employe, Absence absence) {
        // Convertir le solde de congé de l'employé en heures
        float soldeHeures = employe.getSolde() * 8; // 1 jour = 8 heures

        // Vérifier si la date d'absence est fournie et que les heures de début et de fin sont nulles
        if (absence.getDateAbsence() != null && absence.getHeureDebutAbsence() == null && absence.getHeureFinAbsence() == null) {
            // Soustraire 8 heures du solde pour une journée entière
            soldeHeures -= 8;
            employe.setSolde(soldeHeures / 8);
            employeRepository.save(employe);
            // Mettre à jour le solde de l'employé en jours
            // Afficher un message de succès
            System.out.println("Solde de congé mis à jour avec succès pour la journée selectionnee entière.");
        }
        // Vérifier si la date d'absence est fournie et que les heures de début et de fin sont tous les deux fournis
        else if (absence.getDateAbsence() != null && absence.getHeureDebutAbsence() != null && absence.getHeureFinAbsence() != null) {
            // Calculer la durée de l'absence en heures
            long dureeAbsenceHeures = ChronoUnit.HOURS.between(absence.getHeureDebutAbsence(), absence.getHeureFinAbsence());
            // Soustraire la durée de l'absence du solde
            soldeHeures -= dureeAbsenceHeures;
            employe.setSolde(soldeHeures / 8);
            employeRepository.save(employe);
            // Mettre à jour le solde de l'employé en jours
            // Afficher un message de succès
            System.out.println("Solde de congé mis à jour avec succès pour la durée spécifiée.");
        }
        // Si aucun des cas ci-dessus n'est vrai, afficher un message d'erreur
        else {
            System.out.println("Erreur : Les informations d'absence ne sont pas complètes.");
        }
    }

    @Override
    public void deleteAbsence(int id) {
        if(!absenceRepository.existsById(id)){
            throw new IllegalArgumentException("not absence found by delete ");
        }
        absenceRepository.deleteById(id);
    }
}
