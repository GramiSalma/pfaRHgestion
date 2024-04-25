package ma.emsi.pfa3;

import ma.emsi.pfa3.entities.*;
import ma.emsi.pfa3.repositories.EmployeRepository;
import ma.emsi.pfa3.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;
@SpringBootApplication
public class Pfa3Application {
	public static void main(String[] args) {
		SpringApplication.run(Pfa3Application.class, args);
	}
	@Bean
	CommandLineRunner start(IResponsableRHService responsableRHService,
							IEmployeService employeService,
							IDepartementService departementService,
							ICongeService congeService,
	IAbsenceService absenceService){
		return args->{
			Stream.of(
							"Recherche & Développement",
							"Ventes & Marketing",
							"Administratif",
							"Operations",
							"Assurance Qualité",
							"Comptes & Finances",
							"Ressources Humaines",
							"Formation & Développement"
					)
					.forEach(designation->{
						Departement departement = new Departement();
						departement.setDesignationDepartement(designation);
						departementService.saveDepartement(departement);

					});
			Departement departementsearch1= departementService.findDepartementById(4);
			Departement departementsearch2=departementService.findDepartementById(1);
			Departement departementsearch3=departementService.findDepartementById(3);
			Departement departementsearch4=departementService.findDepartementById(2);
			Departement departementsearch5=departementService.findDepartementById(5);
			Departement   departementsearch7=departementService.findDepartementById(7);

			System.out.println(departementsearch7.getIdDepartement());
			Stream.of(departementsearch1,departementsearch2,departementsearch4,departementsearch4,departementsearch5

					)
					.forEach(departement->{
						Employe employe = new Employe();
						employe.setDepartement(departement);
						employe.setNom("Aziz");
						employe.setPrenom("Aziz");
						employeService.saveEmploye(employe);

					});
			Stream.of(
							"Default","Amina","Salma","Kaoutar"

					)
					.forEach(nom->{
						ResponsableRH rh = new ResponsableRH();
						rh.setDepartement(departementsearch7);
						rh.setNom(nom);

						responsableRHService.saveResponsableRH(rh);

					});

			Conge conge1=new Conge();
			conge1.setEmploye(employeService.findEmployeById(3));
			conge1.setDateDebutConge(LocalDate.of(2024, 5, 23));
			conge1.setDateFinConge(LocalDate.of(2024, 5, 30
			));
			conge1.setTypeConge(TypeConge.Vacances);
			conge1.setStatutConge(StatusCNG.Approved);
			congeService.addConge(3,conge1);
			employeService.findEmployeById(3).afficherSoldeEmploye();

			Absence absence = new Absence();
			absence.setEmploye(employeService.findEmployeById(4));
			absence.setDateAbsence(LocalDate.of(2024, 5,26)); // Date de l'absence
			absence.setHeureDebutAbsence(LocalTime.of(9, 0)); // Heure de début de l'absence
			absence.setStatutAbsence(StatusCNG.Approved);
			absence.setHeureFinAbsence(LocalTime.of(14, 0)); // Heure de fin de l'absence
			absenceService.addAbsence(4,absence);
			employeService.findEmployeById(4).afficherSoldeEmploye();


		Employe	employe=employeService.findEmployeById(4);
		employe.setEmail("sara@gmail.com");
		employe.setPassword("1234");
		employeService.saveEmploye(employe);

		};
	}
}
