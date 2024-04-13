package ma.emsi.pfa3.repositories;

import ma.emsi.pfa3.entities.ResponsableRH;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsableRHRepository extends JpaRepository<ResponsableRH,Integer> {
    ResponsableRH findByNom(String nom);
}
