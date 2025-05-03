public class Musique {
    private int id_musique;
    private String titre;
    private int duree;

    public Musique(int id_musique, String titre, int duree) {
        this.id_musique = id_musique;
        this.titre = titre;
        this.duree = duree;
    }

    public int getId_musique() { return id_musique; }
    public String getTitre() { return titre; }
    public int getDuree() { return duree; }
    public void setId_musique(int id_musique) { this.id_musique = id_musique; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setDuree(int duree) { this.duree = duree; }
}
