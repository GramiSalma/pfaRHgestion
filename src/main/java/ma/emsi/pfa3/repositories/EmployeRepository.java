package ma.emsi.pfa3.repositories;

import ma.emsi.pfa3.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe,Integer> {


    Employe findByIdEmploye(int id);
    Employe findByEmail(String email);

}
