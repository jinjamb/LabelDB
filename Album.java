import java.util.List;

public class Album {
    private int id_album;
    private String titre;
    private String date_sortie;
    private Integer artiste_id;
    private List<Integer> musiques;

    public Album(int id_album, String titre, String date_sortie) {
        this.id_album = id_album;
        this.titre = titre;
        this.date_sortie = date_sortie;
    }

    public int getId_album() { 
        return id_album; 
    }

    public String getTitre() { 
        return titre; 
    }

    public String getDate_sortie() { 
        return date_sortie; 
    }

    public Integer getArtiste_id() { 
        return artiste_id; 
    }

    
    public List<Integer> getMusiques() { 
        return musiques; 
    }

    public void setArtiste_id(Integer artiste_id) { 
        this.artiste_id = artiste_id; 
    }

    public void setMusiques(List<Integer> musiques) { 
        this.musiques = musiques; 
    }
}
