/*
 * Copyright (c) Simon Knott 2018.
 */

import io.reactivex.schedulers.Timed;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Transcriptor {

  /*
   * # Attributes
   */

  /**
   * listeners that are called on new code
   */
  private List<Consumer<String>> listeners = new ArrayList<>();

  /**
   * input subject
   * emits all samples from microphone
   */
  private Subject<Integer> input = PublishSubject.create();

  /*
   * # Interface
   */

  /**
   * starts the transcribing
   */
  public void start() {
    recorder.start();
    analyzer.start();
  }

  /**
   * stops the recorder
   * analyzer won't emit since no further items are emitted
   */
  public void stop() {
    recorder.interrupt();
  }

  /**
   * add a new listener
   * @param listener to add
   */
  public void registerListener(Consumer<String> listener) {
    this.listeners.add(listener);
  }

  /**
   * remove all listeners
   */
  public void unregisterListeners() {
    this.listeners.clear();
  }

  /**
   * # Recorder
   * Pipes Microphone input to `input` Subject
   */
  private Thread recorder = new Thread(() -> {
    // Make Observable "Hot"
    input.publish();

    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    TargetDataLine line;

    try {
      AudioFormat format = new AudioFormat(
        44100f,
        16,
        2,
        true,
        true
      );

      DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

      // checks if system supports the data line
      if (!AudioSystem.isLineSupported(info)) {
        System.out.println("Line not supported");
        System.exit(0);
      }
      line = (TargetDataLine) AudioSystem.getLine(info);
      line.open(format);
      line.start();   // start capturing

      int numBytesRead;
      byte[] targetData = new byte[line.getBufferSize() / 5];

      // start recording
      while (true) {
        if (Thread.interrupted()) {
          input.onComplete();
          break;
        }

        numBytesRead = line.read(targetData, 0, targetData.length);

        if (numBytesRead == -1)	break;

        input.onNext(Math.abs(toInt(targetData)));
      }
    } catch (LineUnavailableException ex) {
      ex.printStackTrace();
    }
  });

  /**
   * # Analyzer
   * Analyzes input stream and publishes morse codes to subscribers
   */
  private Thread analyzer = new Thread(() ->
    input
      .map(amplitude -> threshold(amplitude))
      .distinctUntilChanged()
      .debounce(20, TimeUnit.MILLISECONDS)
      .timeInterval()
      .map(event -> toCode(event))
      .subscribe(c -> listeners.forEach(l -> l.accept(c)))
  );

  /**
   * Checks if an event is loud enough
   * @param amplitude to check
   * @return loud enough
   */
  private static boolean threshold(int amplitude) {
    return amplitude > 120000000;
  }

  /**
   * Checks if an event is a long beep
   * @param beep to check
   * @return long or not
   */
  private static boolean isLong(Timed beep) {
    return beep.time(TimeUnit.MILLISECONDS) > 200;
  }

  /**
   * Checks if an event is a divider
   * @param beep to check
   * @return divider or not
   */
  private static boolean isDivider(Timed beep) {
    return beep.time(TimeUnit.MILLISECONDS) > 750;
  }

  /**
   * Maps an event to a morse code
   * @param event to convert
   * @return morse code
   */
  private static String toCode(Timed<Boolean> event) {
    if (!event.value()) {
      return isLong(event) ? "-" : ".";
    } else {
      return isDivider(event) ? " # " : "";
    }
  }

  /*
   * # Helpers
   */

  /**
   * Converts a bytearray to it's integer value.
   * @param bytes to convert
   * @return integer value
   */
  private static int toInt (byte[] bytes) {
    return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
  }

  /*
   * # Main Method
   * For Testing purposes
   */
  public static void main(String... args) throws InterruptedException {
    Transcriptor t = new Transcriptor();

    t.registerListener(System.out::print);

    t.start();
  }
}
