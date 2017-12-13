import org.farng.mp3.id3.*;
import org.farng.mp3.*;

import java.io.IOException;

public class Playlist {
  private PriorityQueue<Lied> playlist = new PriorityQueue<>();
  private Lied aktuell;
  private int aktuellPrio;
  private MP3Player player;

  public boolean isEmpty() {
    return playlist.isEmpty();
  }

  /**
  *  ...
  *  ...
  *
  */
  public void hinzufuegen(String mp3file, int prio) throws IOException, TagException {
    try {
      AbstractID3v2 tags = new MP3File(mp3file).getID3v2Tag();

      playlist.enqueue(
        new Lied(
          tags.getSongTitle(),
          tags.getLeadArtist(),
          mp3file
        ),
              prio
      );
    } catch (Exception e) {
      throw e;
    }
  }
     
    /**
   * Verschiebt den ersten Titel an die letzte Stelle der Warteschlange.
   * Startet die Wiedergabe.
   */

  public void abspielen() {
    Lied old = aktuell;
    int oldPrio = aktuellPrio;

    aktuell = playlist.front();
    aktuellPrio = playlist.frontPriority();
    playlist.dequeue();

    playlist.enqueue(
      old,
      oldPrio == 0 ? 0 : oldPrio - 1
    );

    start();
  }

  /**
  * Gibt den aktuell abgespielten tTitel für die Ausgabe zurueck.
  */
  public String aktuellerTitel() {
    if (aktuell == null) return "";

    return aktuell.getTitel();
  }
  /**
  *  ...
  *  ...
  *
  * @return ...
  */
  public String anzeigen() {
    PriorityQueue<Lied> temp = new PriorityQueue<>();
    StringBuilder builder = new StringBuilder();

    while (!playlist.isEmpty()) {
      builder.append(playlist.front());
      builder.append(" (");
      builder.append(playlist.frontPriority());
      builder.append(")");
      builder.append('\n');

      temp.enqueue(playlist.front(), playlist.frontPriority());
      playlist.dequeue();
    }

    playlist = temp;
    return builder.toString();
  }
  /**
  *  ...
  *  ...
  */
  public void start() {
    if (aktuell == null) return;

    player = new MP3Player(aktuell);
    player.start();
  }
  /**
  *  ...
  *
  */
  public void stop() {
    if (player == null) return;

    player.end();
  }
}
