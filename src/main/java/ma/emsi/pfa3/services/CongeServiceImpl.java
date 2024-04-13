package ma.emsi.pfa3.services;

import ma.emsi.pfa3.entities.*;
import ma.emsi.pfa3.repositories.CongeRepository;
import ma.emsi.pfa3.repositories.EmployeRepository;
import ma.emsi.pfa3.repositories.ResponsableRHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class CongeServiceImpl implements ICongeService{
    private CongeRepository congeRepository;
    private EmployeRepository employeRepository;
    private ResponsableRHRepository responsableRHRepository;


    public CongeServiceImpl(CongeRepository congeRepository, EmployeRepository employeRepository, ResponsableRHRepository responsableRHRepository) {
        this.congeRepository = congeRepository;
        this.employeRepository = employeRepository;
        this.responsableRHRepository = responsableRHRepository;
    }

    public float ajusterSoldeConge(Employe employe, Conge conge) {

            // Convertir le solde de congé de l'employé en heures
            float soldeHeures = employe.getSolde() * 8; // 1 jour = 8 heures

            // Calculer la durée du congé en jours
            long dureeCongeJours = conge.DureeConge();
            // Soustraire 8 heures pour chaque jour dans la durée du congé
            soldeHeures -= dureeCongeJours * 8;

            // Reconvertir les heures restantes en jours
            float soldeJours = soldeHeures / 8;

            // Mettre à jour le solde de congé de l'employé
            return soldeJours;
        }
    @Override
    public Conge addConge(Conge conge) {
        if (conge.getDateDebutConge().isAfter(conge.getDateFinConge())) {
            // Gérer l'erreur ici (par exemple, lancer une exception)

            throw new IllegalArgumentException("La date de début du congé doit être antérieure à la date de fin.");
        }

        // Vérifier si la date de début est une date future
        if (!conge.getDateDebutConge().isAfter(LocalDate.now())) {
            // Gérer l'erreur ici (par exemple, lancer une exception)
            throw new IllegalArgumentException("La date de début du congé doit être une date future.");
        }

        // Calculer la durée du congé
        long dureeConge = ChronoUnit.DAYS.between(conge.getDateDebutConge(), conge.getDateFinConge());

        // Vérifier si la durée du congé est valide par rapport au type de congé et au solde de l'employé
        if (dureeConge > conge.getEmploye().getSolde() || dureeConge < 0) {
            // Gérer l'erreur ici (par exemple, lancer une exception)
            throw new IllegalArgumentException("La durée du congé n'est pas valide.");
        }

        Employe demandeurConge =conge.getEmploye();
        ajusterSoldeConge(demandeurConge,conge);
        conge.setDateDemandeConge(LocalDate.now());
        conge.DureeConge();
        if(conge.getStatutConge()== StatusCNG.Approved){
            float solde=ajusterSoldeConge(demandeurConge,conge);
            demandeurConge.setSolde(solde);
            employeRepository.save(demandeurConge);

        }

        ResponsableRH defaultRh=responsableRHRepository.findByNom("Default");
        conge.setRh(defaultRh);

        congeRepository.save(conge);

        return conge;
    }

    @Override
    public Conge updateConge(Conge conge, int id) {

        return congeRepository.findById(id).map(st-> {
            st.setDateDebutConge(conge.getDateDebutConge());
            st.setDateFinConge(conge.getDateFinConge());
            return congeRepository.save(st);
        }).orElseThrow(()->new IllegalArgumentException("ce conge n'existe pas"));
    }

    @Override
    public Conge getCongeById(int id) {
        return congeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("pas de conge avec cet id"));
    }

    @Override
    public List<Conge> getConges() {
        return congeRepository.findAll();
    }

    @Override
    public void deleteConge(int id) {
        if(!congeRepository.existsById(id)){
            throw new IllegalArgumentException("not conge found by delete ");
        }
        congeRepository.deleteById(id);
    }



}