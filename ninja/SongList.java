package ninja;
import java.util.*;

public class SongList {
    public Song head; // LinkedList head

    // Add song to the LinkedList
    public void addSong(Song song) {
        if (head == null) {
            head = song;
        } else {
            Song current = head;
            while (current.nextSong != null) {
                current = current.nextSong;
            }
            current.nextSong = song;
        }
    }

    // Get two random songs from the list filtered by genre
    public String getRandomSongsByGenre(String genre) {
        List<Song> filteredSongs = new ArrayList<>();
        Song current = head;
        
        // Filter songs by genre
        while (current != null) {
            if (current.topGenre.equalsIgnoreCase(genre)) {
                filteredSongs.add(current);
            }
            current = current.nextSong;
        }

        // Check if there are songs in the genre
        if (filteredSongs.isEmpty()) {
            return "No songs available for this genre.";
        }

        // Shuffle the list to get random songs
        Collections.shuffle(filteredSongs);
        Song song1 = filteredSongs.get(0);
        Song song2 = filteredSongs.size() > 1 ? filteredSongs.get(1) : song1; // If only one song exists, repeat it
        
        return formatSong(song1) + "\n" + formatSong(song2);
    }

    // Format the song details
    private String formatSong(Song song) {
        return song.title + " by " + song.artist + " (Popularity: " + song.popularity + ")";
    }
}
