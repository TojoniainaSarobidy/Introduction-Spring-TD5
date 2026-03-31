package tojoniaina.hei.td5.Entity;

public class CreateStockMovement {
    private String unit;
    private Double value;
    private String type;

    public CreateStockMovement() {
    }

    public CreateStockMovement(String unit, Double value, String type) {
        this.unit = unit;
        this.value = value;
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}