package easv.mrs.GUI.Model;

import easv.mrs.BE.Movie;
import easv.mrs.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MovieModel {

    private ObservableList<Movie> moviesToBeViewed;

    private MovieManager movieManager;

    public static Movie selectedMovie;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }

    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }

    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }

    public void createNewMovie(int year, String title) throws Exception {
        Movie m = movieManager.createNewMovie(year, title);
        moviesToBeViewed.add(m);
    }

    public void updateMovie(Movie movie) throws Exception {
        try {
            movieManager.updateMovie(movie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);
    }

    public void setSelectedMovie(Movie movie) {
        this.selectedMovie = movie;
    }
}
