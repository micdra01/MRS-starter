package easv.mrs.GUI.Model;

import easv.mrs.BE.Movie;
import easv.mrs.BLL.MovieManager;
import easv.mrs.GUI.Controller.MovieViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MovieModel {

    private ObservableList<Movie> moviesToBeViewed;

    private MovieManager movieManager;

    public static Movie selectedMovie;

    public void setSelectedMovie(Movie selectedMovie) {
        MovieModel.selectedMovie = selectedMovie;
    }

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

    public void createNewMovie(int year, String title) {
        try {
            movieManager.createNewMovie(year, title);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
