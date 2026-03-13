package ninja;
//Class to store song information
public class Song {
 public String title;
 public String artist;
 public String popularity;
 public String year;
 public String topGenre;
 public Song nextSong; // For LinkedList

 // Constructor
 public Song(String title, String artist, String popularity, String year, String topGenre) {
     this.title = title;
     this.artist = artist;
     this.popularity = popularity;
     this.year = year;
     this.topGenre = topGenre;
     this.nextSong = null; // Initialize nextSong as null for LinkedList
 }
}
