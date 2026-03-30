package tojoniaina.hei.td5.Entity;

import java.util.Objects;

public class IngredientEntity {
    private Integer identifiant;
    private String nom;
    private IngredientType categorie;
    private Double prix;

    public IngredientEntity() {
    }

    public IngredientEntity(Integer identifiant, String nom, IngredientType categorie, Double prix) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public IngredientType getCategorie() {
        return categorie;
    }

    public void setCategorie(IngredientType categorie) {
        this.categorie = categorie;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientEntity that = (IngredientEntity) o;
        return Objects.equals(identifiant, that.identifiant) && Objects.equals(nom, that.nom) && categorie == that.categorie && Objects.equals(prix, that.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant, nom, categorie, prix);
    }

    @Override
    public String toString() {
        return "IngredientEntity{" +
                "identifiant=" + identifiant +
                ", nom='" + nom + '\'' +
                ", categorie=" + categorie +
                ", prix=" + prix +
                '}';
    }
}
