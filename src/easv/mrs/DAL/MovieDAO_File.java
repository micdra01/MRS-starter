package easv.mrs.DAL;

import easv.mrs.BE.Movie;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public class MovieDAO_File implements IMovieDataAccess {

    private static final String MOVIES_FILE = "data/movie_titles.txt";
    private Path pathToFile = Path.of(MOVIES_FILE);

    public List<Movie> getAllMovies() throws Exception {
        //Read all lines from file:
        List<String> lines = Files.readAllLines(pathToFile);
        List<Movie> allMovies = new ArrayList<>();

        // Parse each line
        for (String line : lines) {
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

        //Sort by id, lowest to highest
        allMovies.sort(Comparator.comparingInt(Movie::getId));

        /**
         * Method to check where there are holes in the IDs.

         int count = 1;
         for (Movie movie: allMovies) {
         if (movie.getId() != allMovies.indexOf(movie)+count) {
         System.out.println(allMovies.indexOf(movie)+count);
         count++;
         }
         }
         * Result: 4388, 4794, 7241, 10782, 15918, 16678, 17667
         */
        return allMovies;
    }

    private int getNextID() throws Exception {
        List<Movie> movies = getAllMovies();

        return movies.get(getAllMovies().size() - 1).getId() + 1; //Get the ID of last index and add 1 for the next ID.
    }

    @Override
    public Movie createMovie(String title, int year) throws Exception {
        int nextID = getNextID();

        String newLine = nextID + "," + year + "," + title;

        //Append new line using Java NIO
        Files.write(pathToFile, ("\n" + newLine).getBytes(), APPEND);

        Movie newMovie = new Movie(nextID, year, title);
        return newMovie;
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