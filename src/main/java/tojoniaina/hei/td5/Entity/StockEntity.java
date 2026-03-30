package tojoniaina.hei.td5.Entity;

import java.time.Instant;
import java.util.Objects;

public class StockEntity {
    private Integer identifiant;
    private IngredientEntity ingredient;
    private Double quantite;
    private StockType type;
    private StockUnit unit;
    private Instant creationDateTime;

    public StockEntity(Integer identifiant, Double quantite, StockType type, StockUnit stockUnit, Instant creationDateTime) {
        this.identifiant = identifiant;
        this.quantite = quantite;
        this.type = type;
        this.unit = stockUnit;
        this.creationDateTime = creationDateTime;
    }

    public StockEntity(Integer identifiant, IngredientEntity ingredient, Double quantite, StockType type, StockUnit unit, Instant creationDateTime) {
        this.identifiant = identifiant;
        this.ingredient = ingredient;
        this.quantite = quantite;
        this.type = type;
        this.unit = unit;
        this.creationDateTime = creationDateTime;
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientEntity ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public StockType getType() {
        return type;
    }

    public void setType(StockType type) {
        this.type = type;
    }

    public StockUnit getUnit() {
        return unit;
    }

    public void setUnit(StockUnit unit) {
        this.unit = unit;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(identifiant, that.identifiant) && Objects.equals(ingredient, that.ingredient) && Objects.equals(quantite, that.quantite) && type == that.type && unit == that.unit && Objects.equals(creationDateTime, that.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant, ingredient, quantite, type, unit, creationDateTime);
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "identifiant=" + identifiant +
                ", ingredient=" + ingredient +
                ", quantite=" + quantite +
                ", type=" + type +
                ", unit=" + unit +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
