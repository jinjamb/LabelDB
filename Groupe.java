public class Groupe {
    private int id_groupe;
    private String nom;
    private String genre_musical;

    public Groupe(int id_groupe, String nom, String genre_musical) {
        this.id_groupe = id_groupe;
        this.nom = nom;
        this.genre_musical = genre_musical;
    }

    public int getId_groupe(){ 
        return id_groupe;
    }
    public String getNom() { 
        return nom; 
    }

    public String getGenre_musical() { 

        return genre_musical; 
    }

    public void setId_groupe(int id_groupe) { 
        this.id_groupe = id_groupe; 
    }
    public void setNom(String nom) { 
        this.nom = nom; 
    }
    public void setGenre_musical(String genre_musical) { 
        this.genre_musical = genre_musical; 
    }
}
