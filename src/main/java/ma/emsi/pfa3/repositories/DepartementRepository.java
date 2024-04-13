package ma.emsi.pfa3.repositories;

import ma.emsi.pfa3.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement,Integer> {
    Departement findByIdDepartement(int id);

}
