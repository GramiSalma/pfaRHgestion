package ma.emsi.pfa3.services;

import ma.emsi.pfa3.entities.Conge;

import java.util.List;

public interface ICongeService {
    Conge addConge(Conge conge);
    Conge updateConge(Conge conge,int id);
    Conge getCongeById(int id);
   List<Conge> getConges();
   void deleteConge(int id );

}
