package ma.emsi.pfa3.entities;

import lombok.Data;

import java.util.Date;

@Data

public  class  Utilisateur {
    private int cin;
    private Date dateNaissance;
    private  Date dateRecrutement;
    private  String email;
    private String nom;
    private String photo;
    private  String prenom;
    private  double salaire;
    private String sexe;
    private  String situationFamiliale;
    private  long telephone;



}
