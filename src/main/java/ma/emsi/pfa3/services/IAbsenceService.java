package ma.emsi.pfa3.services;

import ma.emsi.pfa3.entities.Absence;

import java.util.List;

public interface IAbsenceService {
    Absence addAbsence(int IdEmploye,Absence absence);
    Absence updateAbsence(Absence absence, int id);
    Absence getAbsenceById(int id);
    List<Absence> getAbsences();
    void deleteAbsence(int id);
}

