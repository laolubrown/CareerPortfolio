package ninja;
import java.io.*;
import java.util.*;

public class Tester {

    public static void main(String[] args) throws IOException {
        SongList songList = new SongList();

        // Read songs from the Spotify CSV (Spotify-2000_b.csv)
        List<Song> songs = readSongsFromFile("C:\\Users\\Laolu\\241\\lab7\\src\\poo\\Spotify-2000_b.csv"); // Spotify CSV file with song data
        for (Song song : songs) {
            songList.addSong(song);
        }

        // Read student favorite genres from Class Music CSV (class_music.csv)
        List<String[]> students = readStudentGenresFromFile("C:\\Users\\Laolu\\241\\lab7\\src\\poo\\class_music.csv"); // Class Music CSV file

        // Loop through each student and provide recommendations based on their favorite genre
        for (String[] student : students) {
            String name = student[0];  // Assuming the first column is student name
            String favoriteGenre = student[1];  // Assuming the second column is the student's favorite genre

            // Get recommendations for each student
            String recommendations = songList.getRandomSongsByGenre(favoriteGenre);
            System.out.println("\nRecommended songs for " + name + " who likes " + favoriteGenre + ":");
            System.out.println(recommendations);

            // Write recommendations to a file
            writeToFile(name + "_recommendations.txt", recommendations);  // Each student has their own output file
        }
    }

    // Method to read songs from the Spotify CSV file
    private static List<Song> readSongsFromFile(String fileName) throws IOException {
        List<Song> songs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 15) {
                // Add relevant data for Song class
                songs.add(new Song(data[1], data[2], data[14], data[4], data[3])); // Title, Artist, Popularity, Year, Genre
            }
        }
        reader.close();
        return songs;
    }

    // Method to read student favorite genres from the Class Music CSV file
    private static List<String[]> readStudentGenresFromFile(String fileName) throws IOException {
        List<String[]> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 2) {  // Assuming at least two columns: student name and favorite genre
                students.add(new String[]{data[0], data[1]}); // Add student name and favorite genre
            }
        }
        reader.close();
        return students;
    }

    // Method to write recommendations to a file
    private static void writeToFile(String fileName, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
        System.out.println("\nRecommendations written to " + fileName);
    }
}
