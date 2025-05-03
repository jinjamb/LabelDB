public class Producteur {
    private int id_producteur;
    private String nom;
    private String societe;

    public Producteur(int id_producteur, String nom, String societe) {
        this.id_producteur = id_producteur;
        this.nom = nom;
        this.societe = societe;
    }

    public int getId_producteur() { 
        return id_producteur; 
    }

    public String getNom() { 
        return nom; 
    }



    public String getSociete() { 
        return societe; 
    
    
    }

    public void setId_producteur(int id_producteur) { 
        this.id_producteur = id_producteur; 
    }
    public void setNom(String nom) { 
        this.nom = nom; 
    }
    public void setSociete(String societe) { 
        this.societe = societe; }
}

