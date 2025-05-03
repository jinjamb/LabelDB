import java.util.List;

public class Manager {
    private int id_manager;
    private String nom;
    private String email;
    private List<Integer> producteurs;

    public Manager(int id_manager, String nom, String email) {
        this.id_manager = id_manager;
        this.nom = nom;
        this.email = email;
    }

    public int getId_manager() { return id_manager; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public List<Integer> getProducteurs() { return producteurs; }

    public void setId_manager(int id_manager) { this.id_manager = id_manager; }
    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }
    public void setProducteurs(List<Integer> producteurs) { this.producteurs = producteurs; }
}


