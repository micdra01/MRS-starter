package easv.mrs.DAL.db;

import easv.mrs.BE.Movie;
import easv.mrs.DAL.IMovieDataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess {

    private MyDatabaseConnector databaseConnector;

    public MovieDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    public List<Movie> getAllMovies() throws Exception {
        try (Connection connection = databaseConnector.getConnection();
             Statement statement = connection.createStatement();) {
            List<Movie> allMovies = new ArrayList<>();

            String sql = "SELECT * FROM Movie;";
            ResultSet resultSet = statement.executeQuery(sql);

            //Loop through rows from the database result set
            while (resultSet.next()) {
                //Map DB row to Movie object
                int id = resultSet.getInt("Id");
                String title = resultSet.getString("Title");
                int year = resultSet.getInt("Year");

                Movie movie = new Movie(id, year, title);
                allMovies.add(movie);
            }
            return allMovies;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not get movies from database", e);
        }
    }

    public Movie createMovie(String title, int year) throws Exception {
        String sql = "INSERT INTO Movie (Title,Year) VALUES (?,?);"; //Match to database column name
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Bind parameters
            statement.setString(1, title); //Match to java variable name. Indexes from available columns that can be set.
            statement.setInt(2, year);

            //Run the specified SQL statement
            statement.executeUpdate();

            //Get the generated ID from the database
            ResultSet resultSet = statement.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            //Create and return movie object and send up the layers to the GUI
            Movie movie = new Movie(id, year, title);
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not create movie", e);
        }
    }

    public void updateMovie(Movie movie) throws Exception {
        String sql = "UPDATE Movie SET Title=?, Year=? WHERE Id=?;"; //Match to database column name

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            //Bind parameters
            statement.setString(1, movie.getTitle());
            statement.setInt(2, movie.getYear());
            statement.setInt(3, movie.getId());

            //Run the specified SQL statement
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not edit movie", e);
        }
    }

    public void deleteMovie(Movie movie) throws Exception {
        String sql = "DELETE FROM Movie WHERE Id=?;";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            //Bind parameters
            statement.setInt(1, movie.getId());

            //Run the specified SQL statement
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not delete movie", e);
        }
    }

    public List<Movie> searchMovies(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }

}
