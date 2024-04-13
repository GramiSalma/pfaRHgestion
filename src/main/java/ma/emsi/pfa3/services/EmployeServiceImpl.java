package ma.emsi.pfa3.services;


import ma.emsi.pfa3.entities.Employe;
import ma.emsi.pfa3.repositories.EmployeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeServiceImpl implements IEmployeService {
    private EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe saveEmploye(Employe employe) {
        employe.setSolde(20);
        return employeRepository.save(employe);
    }
    public Employe findEmployeById(int id){

        return employeRepository.findByIdEmploye(id);
    }
}
