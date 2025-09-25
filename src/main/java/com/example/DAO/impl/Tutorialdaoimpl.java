package com.example.DAO.impl;

import com.example.DAO.TutorialDao;
import com.example.entity.Tutorial;
import com.example.exception.DBOperationException;
import com.example.exception.TutorialNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Tutorialdaoimpl implements TutorialDao {

    private Tutorial extractTutorial(ResultSet rs) throws SQLException {
        return new Tutorial(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("url")
        );
    }

    @Override
    public void addTutorial(Tutorial tutorial) throws DBOperationException {
        String sql = "INSERT INTO tutorial (id, title, author, url) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection("jdbc:oracle:thin:@//localhost:1521/ORCL", "c##scott", "tiger");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tutorial.getId());
            ps.setString(2, tutorial.getTitle());
            ps.setString(3, tutorial.getAuthor());
            ps.setString(4, tutorial.getUrl());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DBOperationException("Insert failed, no rows affected");
            }

        } catch (SQLException e) {
            throw new DBOperationException("Error inserting tutorial: " + e.getMessage());
        }
    }

    @Override
    public Tutorial getTutorialById(int id) throws DBOperationException, TutorialNotFoundException {
        String sql = "SELECT id, title, author, url FROM tutorial WHERE id = ?";
        try (Connection con = getConnection("jdbc:oracle:thin:@//localhost:1521/ORCL", "c##scott", "tiger");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractTutorial(rs);
                } else {
                    throw new TutorialNotFoundException("No Tutorial with id " + id);
                }
            }

        } catch (SQLException e) {
            throw new DBOperationException("Error fetching tutorial: " + e.getMessage());
        }
    }

    @Override
    public List<Tutorial> getAllTutorials() throws DBOperationException {
        List<Tutorial> tutorials = new ArrayList<>();
        String sql = "SELECT id, title, author, url FROM tutorial";
        try (Connection con = getConnection("jdbc:oracle:thin:@//localhost:1521/ORCL", "c##scott", "tiger");
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tutorials.add(extractTutorial(rs));
            }

        } catch (SQLException e) {
            throw new DBOperationException("Error fetching all tutorials: " + e.getMessage());
        }
        return tutorials;
    }

    @Override
    public void updateTutorial(Tutorial tutorial) throws DBOperationException, TutorialNotFoundException {
        String sql = "UPDATE tutorial SET title = ?, author = ?, url = ? WHERE id = ?";
        try (Connection con = getConnection("jdbc:oracle:thin:@//localhost:1521/ORCL", "c##scott", "tiger");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tutorial.getTitle());
            ps.setString(2, tutorial.getAuthor());
            ps.setString(3, tutorial.getUrl());
            ps.setInt(4, tutorial.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new TutorialNotFoundException("Tutorial with ID " + tutorial.getId() + " not found");
            }

        } catch (SQLException e) {
            throw new DBOperationException("Error updating tutorial: " + e.getMessage());
        }
    }

    @Override
    public void deleteTutorial(int id) throws DBOperationException, TutorialNotFoundException {
        String sql = "DELETE FROM tutorial WHERE id = ?";
        try (Connection con = getConnection("jdbc:oracle:thin:@//localhost:1521/ORCL", "c##scott", "tiger");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new TutorialNotFoundException("Tutorial with ID " + id + " not found");
            }

        } catch (SQLException e) {
            throw new DBOperationException("Error deleting tutorial: " + e.getMessage());
        }
    }
}
