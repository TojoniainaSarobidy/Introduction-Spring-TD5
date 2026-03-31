package tojoniaina.hei.td5.Entity;

import java.time.Instant;
import java.util.Objects;

public class StockMovementEntity {
    private Integer identifiant;
    private Integer ingredientId;
    private Double quantite;
    private StockType type;
    private StockUnit unit;
    private Instant creationDateTime;

    public StockMovementEntity(Integer identifiant, Integer ingredientId, Double quantite,
                               StockType type, StockUnit unit, Instant creationDateTime) {
        this.identifiant = identifiant;
        this.ingredientId = ingredientId;
        this.quantite = quantite;
        this.type = type;
        this.unit = unit;
        this.creationDateTime = creationDateTime;
    }

    public StockMovementEntity(Integer identifiant,
                               String unit,
                               Double quantite,
                               String type,
                               Instant creationDateTime) {

        this.identifiant = identifiant;
        this.unit = StockUnit.valueOf(unit);
        this.quantite = quantite;
        this.type = StockType.valueOf(type);
        this.creationDateTime = creationDateTime;
    }

    public StockMovementEntity(Integer identifiant,
                               Instant creationDateTime,
                               String unit,
                               Double quantite,
                               String type) {

        this.identifiant = identifiant;
        this.creationDateTime = creationDateTime;
        this.unit = StockUnit.valueOf(unit);
        this.quantite = quantite;
        this.type = StockType.valueOf(type);
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public Double getQuantite() {
        return quantite;
    }

    public StockType getType() {
        return type;
    }

    public StockUnit getUnit() {
        return unit;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public void setType(StockType type) {
        this.type = type;
    }

    public void setUnit(StockUnit unit) {
        this.unit = unit;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockEntity)) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(identifiant, that.getIdentifiant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant);
    }
}
