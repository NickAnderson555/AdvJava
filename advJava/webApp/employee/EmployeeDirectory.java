package java112.employee;

import java.io.*;
import java.util.*;
import java.sql.*;

public class EmployeeDirectory {

private Properties properties;
private String driver;
private String url;
private String username;
private String password;

    public Search searchForEmployee(String searchType, String searchTerm) {

        Search searchResults = new Search(searchType, searchTerm);

        if (searchType.equals("id")) {
            this.searchById(searchResults);
        } else {
            this.searchByLastName(searchResults);
        }

        return searchResults;

    }

    private void searchById(Search searchResults) {

        String sql = "SELECT emp_id, first_name, last_name, ssn, dept, room, phone "
                    + "FROM employees WHERE emp_id = " + searchResults.getSearchTerm() + ";";

        this.searchDb(searchResults, sql);
    }

    private void searchByLastName(Search searchResults) {

        String sql = "SELECT emp_id, first_name, last_name, ssn, dept, room, phone "
                    + "FROM employees WHERE last_name = '" + searchResults.getSearchTerm() + "';";

        this.searchDb(searchResults, sql);
    }

    private void searchDb(Search searchResults, String sql) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            boolean foundRow = false;
            Employee employee = null;

            while (resultSet.next()) {
                foundRow = true;
                employee = new Employee();
                employee.setEmployeeId(resultSet.getString("emp_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setSsn(resultSet.getString("ssn"));
                employee.setDepartment(resultSet.getString("dept"));
                employee.setRoom(resultSet.getString("room"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setOutput();
                searchResults.addFoundEmployee(employee);
            }

            searchResults.setTest(foundRow);

        } catch (ClassNotFoundException classNotFound) {
            System.err.println("Cannot find database driver ");
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            System.err.println("Error in connection.ecting to database "
                    + sqlException);
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                        + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            }
        }

    }

    public String addEmployee(int id, String firstName, String lastName, String ssn,
                String department, String room, String phone) {

        Connection connection = null;
        Statement statement = null;
        String sql = "INSERT INTO employees VALUES (" + id + ", " + "'" + firstName + "', "
                + "'" + lastName + "', " + "'" + ssn + "', " + "'" + department
                + "', " + "'" + room + "', " + "'" + phone + "');";
        int rowsAffected = 0;

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sql);

            } catch (SQLException sqlException) {
                System.err.println("Error in connection.ecting to database "
                       + sqlException);
                sqlException.printStackTrace();
            } catch (Exception exception) {
                System.err.println("General Error");
                exception.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }

                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException sqlException) {
                    System.err.println("Error in connection.ecting to database "
                         + sqlException);
                    sqlException.printStackTrace();
                } catch (Exception exception) {
                    System.err.println("General Error");
                    exception.printStackTrace();
                }
            }

             if (rowsAffected == 1) {
                return "success";
             } else {
                return "failure";
             }

        }

    public EmployeeDirectory(Properties properties) {

        this.properties = properties;
        this.driver = properties.getProperty("driver");
        this.url = properties.getProperty("url");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
    }

}
