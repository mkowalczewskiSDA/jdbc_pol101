package sda.jdbc.dao;

import sda.jdbc.przyklad4.Country;
import sda.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements  CountryDao{

    private final String selectAll = "select * from Country;";
    private final String selectById = "select * from Country where CO_ID = ?;";
    public static final int CO_ID_PARAMETER_INDEX = 1;

    @Override
    public List<Country> getAll() throws SQLException {
        ResultSet resultSet = getConnection().createStatement().executeQuery(selectAll);

        List<Country> countries = new ArrayList<>();

        while (resultSet.next()) {
            countries.add(mapResultSetToCountry(resultSet));
        }
        return countries;
    }

    @Override
    public Country findById(int id) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(selectById);

        preparedStatement.setInt(CO_ID_PARAMETER_INDEX, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return mapResultSetToCountry(resultSet);
        }
        return null;
    }

    @Override
    public void addNewCountry(Country country) {

    }

    @Override
    public void deleteById(int id) {

    }

    private Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }

    private Country mapResultSetToCountry(ResultSet resultSet) throws SQLException {
        return new Country(
                resultSet.getInt("CO_ID"),
                resultSet.getString("CO_NAME"),
                resultSet.getString("CO_ALIAS")
        );
    }
}
