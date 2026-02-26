package com.academy.four.dao.player;

import com.academy.four.dao.Dao;
import com.academy.four.dao.connection.ConnectionPool;
import com.academy.four.util.ColorUtil;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerInfoDao implements Dao<PlayerInfo> {
    @Override
    public boolean save(PlayerInfo playerInfo) {
        String sqlStatement = """
                INSERT INTO player_info
                (first_name, last_name, sport, years, vegetarian, color)
                VALUES (?, ?, ?, ? , ? , ?)
                """;
        ConnectionPool connectionPool = ConnectionPool.instance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setString(1, playerInfo.getFirstName());
            preparedStatement.setString(2, playerInfo.getLastName());
            preparedStatement.setString(3, playerInfo.getSport());
            preparedStatement.setInt(4, playerInfo.getYears());
            preparedStatement.setBoolean(5, playerInfo.isVegetarian());
            String hexColor = ColorUtil.colorToString(playerInfo.getColor());
            preparedStatement.setString(6, hexColor);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

    //ZADAĆA
    //try-with-resource -> 1. connection.close() 2. connectionPool.releaseConnection(connection)
    @Override
    public boolean update(PlayerInfo playerInfo) {
        String sqlUpdate = """
                UPDATE player_info
                SET first_name = ?,
                    last_name = ?,
                    sport = ?,
                    years = ?,
                    vegetarian = ?,
                    color = ?
                WHERE id = ?
                """;
        ConnectionPool connectionPool = ConnectionPool.instance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setString(1, playerInfo.getFirstName());
            preparedStatement.setString(2, playerInfo.getLastName());
            preparedStatement.setString(3, playerInfo.getSport());
            preparedStatement.setInt(4, playerInfo.getYears());
            preparedStatement.setBoolean(5, playerInfo.isVegetarian());
            String hexColor = ColorUtil.colorToString(playerInfo.getColor());
            preparedStatement.setString(6, hexColor);
            preparedStatement.setInt(7, playerInfo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

    @Override
    public boolean delete(PlayerInfo playerInfo) {
        String sqlDelete = """
                DELETE FROM player_info
                WHERE id = ?
                """;
        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlDelete)) {
            ps.setInt(1, playerInfo.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while deleting record: " + e.getMessage());
        } finally {
            pool.releaseConnection(connection);
        }
        return true;
    }

    @Override
    public PlayerInfo findById(Integer id) {
        return null;
    }

    @Override
    public List<PlayerInfo> findAll() {
        List<PlayerInfo> players = new ArrayList<>();
        String sqlStatement = """
                SELECT * FROM player_info;
                """;
        ConnectionPool connectionPool = ConnectionPool.instance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PlayerInfo playerInfo = new PlayerInfo();
                playerInfo.setId(resultSet.getInt("id"));
                playerInfo.setFirstName(resultSet.getString("first_name"));
                playerInfo.setLastName(resultSet.getString("last_name"));
                playerInfo.setSport(resultSet.getString("sport"));
                playerInfo.setYears(resultSet.getInt("years"));
                playerInfo.setVegetarian(resultSet.getBoolean("vegetarian"));
                Color color = ColorUtil.stringToColor(resultSet.getString("color"));
                playerInfo.setColor(color);

                players.add(playerInfo);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return players;
    }

    //META PODATKE
    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        String sqlStatement = """
                SELECT * FROM player_info;
                """;
        ConnectionPool connectionPool = ConnectionPool.instance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                columnNames.add(columnName);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return columnNames;
    }
}
