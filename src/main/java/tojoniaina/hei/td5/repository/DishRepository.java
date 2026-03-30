package tojoniaina.hei.td5.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import tojoniaina.hei.td5.Entity.*;

@Repository
public class DishRepository {

    private final DataSource dataSource;

    public DishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<DishEntity> findAll() {
        List<DishEntity> dishes = new ArrayList<>();

        String sql = "SELECT id, name, price FROM dish";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DishEntity dish = new DishEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        getIngredientsByDishId(rs.getInt("id"), connection)
                );
                dishes.add(dish);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dishes;
    }

    public Optional<DishEntity> findById(Integer id) {
        String sql = "SELECT id, name, price FROM dish WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DishEntity dish = new DishEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        getIngredientsByDishId(id, conn)
                );
                return Optional.of(dish);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    private List<IngredientEntity> getIngredientsByDishId(Integer dishId, Connection conn) throws SQLException {
        List<IngredientEntity> ingredients = new ArrayList<>();

        String sql = """
                    SELECT i.id, i.name, i.price FROM ingredient i
                    JOIN dish_ingredient di ON i.id = di.ingredient_id
                    WHERE di.dish_id = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dishId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                IngredientEntity ing = new IngredientEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        IngredientType.valueOf(rs.getString("categorie")),
                        rs.getDouble("price")
                );
                ingredients.add(ing);
            }
        }

        return ingredients;
    }

    public void updateIngredients(Integer dishId, List<Integer> ingredientIds) {

        String deleteSql = "DELETE FROM dish_ingredient WHERE dish_id = ?";
        String insertSql = "INSERT INTO dish_ingredient(dish_id, ingredient_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection()) {

            try (PreparedStatement ps = conn.prepareStatement(deleteSql)) {
                ps.setInt(1, dishId);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                for (Integer ingId : ingredientIds) {
                    ps.setInt(1, dishId);
                    ps.setInt(2, ingId);
                    ps.addBatch();
                }
                ps.executeBatch();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}