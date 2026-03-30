package tojoniaina.hei.td5.Entity;

import java.util.List;
import java.util.Objects;

public class DishEntity {
    private Integer identifiant;
    private String nom;
    private Double price;
    private List<IngredientEntity> ingredients;

    public DishEntity(Integer identifiant, String nom, Double price, List<IngredientEntity> ingredients) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.price = price;
        this.ingredients = ingredients;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishEntity that = (DishEntity) o;
        return Objects.equals(identifiant, that.identifiant) && Objects.equals(nom, that.nom) && Objects.equals(price, that.price) && Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant, nom, price, ingredients);
    }

    @Override
    public String toString() {
        return "DishEntity{" +
                "identifiant=" + identifiant +
                ", nom='" + nom + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}
