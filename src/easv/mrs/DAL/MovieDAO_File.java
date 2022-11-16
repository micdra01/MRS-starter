package easv.mrs.DAL;

import easv.mrs.BE.Movie;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_File implements IMovieDataAccess {

    private static final String MOVIES_FILE = "data/movie_titles.txt";
    private Path pathToFile = Path.of(MOVIES_FILE);

    public List<Movie> getAllMovies() throws IOException {
        //Read all lines from file:
        List<String> lines = Files.readAllLines(pathToFile);
        List<Movie> allMovies = new ArrayList<>();

        // Parse each line
        for (String line: lines) {
            String[] seperatedLine = line.split(",");

            // Map each seperated line to Movie object
            int id = Integer.parseInt(seperatedLine[0]);
            int year = Integer.parseInt(seperatedLine[1]);
            String title = seperatedLine[2];

            //Create Movie object
            Movie mov = new Movie(id, year, title);

            // Add each Movie object to the array of movies
            allMovies.add(mov);
        }
        return allMovies;
    }

    @Override
    public Movie createMovie(String title, int year) throws Exception {
        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {

    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {

    }






    /*
    public List<Movie> getAllMovies() {
        List<Movie> allMovieList = new ArrayList<>();

        File moviesFile = new File(MOVIES_FILE);


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(moviesFile))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = bufferedReader.readLine();
                hasLines = (line != null);
                if (hasLines && !line.isBlank())
                {
                    String[] separatedLine = line.split(",");

                    int id = Integer.parseInt(separatedLine[0]);
                    int year = Integer.parseInt(separatedLine[1]);
                    String title = separatedLine[2];
                    if(separatedLine.length > 3)
                    {
                        for(int i = 3; i < separatedLine.length; i++)
                        {
                            title += "," + separatedLine[i];
                        }
                    }
                    Movie movie = new Movie(id, title, year);
                    allMovieList.add(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMovieList;
    }
    */


}