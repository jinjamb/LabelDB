public class Artiste {

    private int id_artiste;
    private String nom;
    private String genre_musical;
    private String date_naissance;
    private Integer groupe;
    private Integer manager_id;

    public Artiste(int id_artiste, String nom, String genre_musical, String date_naissance) {
        this.id_artiste = id_artiste;
        this.nom = nom;
        this.genre_musical = genre_musical;
        this.date_naissance = date_naissance;
    }

    public int getId_artiste() { 
        return id_artiste; 
    }

    public String getNom() { 
        return nom; 
    }

    public String getGenre_musical() { 
        return genre_musical;
    }
    public String getDate_naissance() { 
        return date_naissance;
    }

    public Integer getGroupe() { 
        return groupe; 
    }
    public Integer getManager_id() { 
        return manager_id; 
    }

    public void setGroupe(Integer groupe) { 
        this.groupe = groupe; 
    }

    public void setManager_id(Integer manager_id) { 
        this.manager_id = manager_id; 
    }
    
}
