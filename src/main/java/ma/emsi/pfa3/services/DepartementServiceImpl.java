package ma.emsi.pfa3.services;

import ma.emsi.pfa3.entities.Departement;
import ma.emsi.pfa3.repositories.DepartementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartementServiceImpl implements IDepartementService{
    private DepartementRepository departementRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }
    @Override
    public Departement findDepartementById(int id) {

        return departementRepository.findByIdDepartement(id);
    }
}
