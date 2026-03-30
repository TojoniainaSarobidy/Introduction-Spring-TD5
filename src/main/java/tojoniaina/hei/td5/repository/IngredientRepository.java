package tojoniaina.hei.td5.repository;

import org.springframework.stereotype.Repository;
import tojoniaina.hei.td5.Entity.IngredientEntity;
import tojoniaina.hei.td5.Entity.IngredientType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepository {
    private final DataSource dataSource;

    public IngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<IngredientEntity> findAll() {
        List<IngredientEntity> ingredients = new ArrayList<>();
        String sql = "SELECT id, nom, categorie, prix FROM ingredient";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IngredientEntity ingredientEntity = new IngredientEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        IngredientType.valueOf(resultSet.getString("categorie")),
                        resultSet.getDouble("prix")
                );
                ingredients.add(ingredientEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return ingredients;
    }

    public Optional<IngredientEntity> findById(Integer id) {
        String sql = "SELECT id, nom, categorie, prix FROM ingredient WHERE id = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IngredientEntity ingredient = new IngredientEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        IngredientType.valueOf(resultSet.getString("categorie")),
                        resultSet.getDouble("prix")
                );
                return Optional.of(ingredient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return Optional.empty();
    }
}
