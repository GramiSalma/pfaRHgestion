package ma.emsi.pfa3.services;


import ma.emsi.pfa3.entities.ResponsableRH;
import ma.emsi.pfa3.repositories.ResponsableRHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResponsableRHServiceImpl implements IResponsableRHService {
    private ResponsableRHRepository responsableRHRepository;

    public ResponsableRHServiceImpl(ResponsableRHRepository responsableRHRepository) {
        this.responsableRHRepository = responsableRHRepository;
    }


    @Override
    public ResponsableRH saveResponsableRH(ResponsableRH responsableRH) {

        return responsableRHRepository.save(responsableRH);
    }





}
