package ma.emsi.pfa3.services;

import ma.emsi.pfa3.entities.Employe;

public interface IEmployeService {
    Employe saveEmploye(Employe employe);
    Employe findEmployeById(int id);
}
