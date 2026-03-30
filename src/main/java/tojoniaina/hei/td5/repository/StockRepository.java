package tojoniaina.hei.td5.repository;

import org.springframework.stereotype.Repository;
import tojoniaina.hei.td5.Entity.StockEntity;
import tojoniaina.hei.td5.Entity.StockType;
import tojoniaina.hei.td5.Entity.StockUnit;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockRepository {
    private final DataSource dataSource;

    public StockRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<StockEntity> findByIngredientIdAndDate(Integer id, Instant at) {
        List<StockEntity> stocks = new ArrayList<>();
        String sql = """
                SELECT id, quantite, type, unit, creationdatetime
                                FROM stockmovement
                                WHERE id_ingredient = ?
                                AND creationdatetime <= ?
                """;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setTimestamp(2, Timestamp.from(at));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StockEntity stockEntity = new StockEntity(
                        resultSet.getInt("id"),
                        resultSet.getDouble("quantite"),
                        StockType.valueOf(resultSet.getString("type")),
                        StockUnit.valueOf(resultSet.getString("unit")),
                        resultSet.getTimestamp("creationdatetime").toInstant()
                );
                stocks.add(stockEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return stocks;
    }
}
