package entities;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

public class Module {
    private String matricule;
    private String nom;
    private int coefficient;
    private int volume_horaire;
    private TypeModule type;
    private UniteEnseignement ue;

    public Module() {
    }

    public Module(String matricule, String nom, int coefficient, int volume_horaire, TypeModule type, UniteEnseignement ue) {
        this.matricule = matricule;
        this.nom = nom;
        this.coefficient = coefficient;
        this.volume_horaire = volume_horaire;
        this.type = type;
        this.ue = ue;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getVolume_horaire() {
        return volume_horaire;
    }

    public void setVolume_horaire(int volume_horaire) {
        this.volume_horaire = volume_horaire;
    }

    public TypeModule getType() {
        return type;
    }

    public void setType(TypeModule type) {
        this.type = type;
    }

    public UniteEnseignement getUe() {
        return ue;
    }

    public void setUe(UniteEnseignement ue) {
        this.ue = ue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module module)) return false;
        return Objects.equals(matricule, module.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricule);
    }
}
