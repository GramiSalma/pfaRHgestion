package ma.emsi.pfa3.services;

import ma.emsi.pfa3.entities.Departement;

public interface IDepartementService {
    Departement saveDepartement(Departement departement); Departement findDepartementById(int id);
}