import java.io.*;

import javazoom.jl.player.Player;


public class MP3Player extends Thread {
  private FileInputStream fis;
  private Player player;
  private Lied lied;

  public MP3Player(Lied lied) {
    this.lied = lied;
  }

  public void run() {
    try {
      fis = new FileInputStream(lied.getMp3Name());
      player = new Player(fis);
      player.play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void end() {
    try {
      if (player != null) {
        player.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
