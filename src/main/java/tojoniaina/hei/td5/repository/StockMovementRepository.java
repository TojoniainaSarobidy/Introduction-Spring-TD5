package tojoniaina.hei.td5.repository;

import org.springframework.stereotype.Repository;
import tojoniaina.hei.td5.Entity.CreateStockMovement;
import tojoniaina.hei.td5.Entity.StockMovementEntity;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockMovementRepository {
    private final DataSource dataSource;

    public StockMovementRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<StockMovementEntity> findByIngredient(Integer ingredientId, Instant from, Instant to) {
        List<StockMovementEntity> list = new ArrayList<>();

        String sql = """
                    SELECT id, unit, quantite, type, creationDateTime
                    FROM stockMovement
                    WHERE id_ingredient = ?
                    AND creationDateTime BETWEEN ? AND ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ingredientId);
            ps.setTimestamp(2, Timestamp.from(from));
            ps.setTimestamp(3, Timestamp.from(to));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new StockMovementEntity(
                        rs.getInt("id"),
                        rs.getString("unit"),
                        rs.getDouble("quantite"),
                        rs.getString("type"),
                        rs.getTimestamp("creationDateTime").toInstant()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<StockMovementEntity> saveAll(Integer ingredientId, List<CreateStockMovement> movements) {
        List<StockMovementEntity> result = new ArrayList<>();

        String sql = """
                    INSERT INTO stockMovement(id_ingredient, quantite, type, unit, creationDateTime)
                    VALUES (?, ?, ?::stockmovementtype, ?::stockmovementunit, ?)
                    RETURNING id, creationDateTime, unit, quantite, type
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (CreateStockMovement m : movements) {
                ps.setInt(1, ingredientId);
                ps.setDouble(2, m.getValue());
                ps.setString(3, m.getType());
                ps.setString(4, m.getUnit());
                ps.setTimestamp(5, Timestamp.from(Instant.now()));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    result.add(new StockMovementEntity(
                            rs.getInt("id"),
                            rs.getTimestamp("creationDateTime").toInstant(),
                            rs.getString("unit"),
                            rs.getDouble("quantite"),
                            rs.getString("type")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
