/*
 * Copyright (c) Simon Knott 2018.
 */

import io.reactivex.Observable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Timed;

import javax.sound.midi.SysexMessage;
import javax.sound.sampled.*;
import java.awt.*;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Transcriptor {
  interface Thunk {
    void call();
  }

  private Consumer<byte[]> read;
  private Thunk complete;

  private Observable<byte[]> input = Observable.create(emitter -> {
    this.read = emitter::onNext;
    this.complete = emitter::onComplete;
  });;

  private Thread recorder = new Thread(() -> {
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
          complete.call();
          break;
        }

        numBytesRead = line.read(targetData, 0, targetData.length);

        if (numBytesRead == -1)	break;

        read.accept(targetData);
      }
    } catch (LineUnavailableException ex) {
      ex.printStackTrace();
    }
  });

  /**
   * # Constructor
   */
  public Transcriptor() {
    input.publish();

    Observable<Integer> ints = input
      .map(toInt)
      .map(Math::abs);

    Observable<Boolean> on = ints.map(threshhold);

    Observable<Boolean> distinct = on.distinctUntilChanged(compareBoolean);

    distinct
      .timeInterval()
      .filter(isLongEnough)
      .map(toCode)
      .subscribe(System.out::print);
  }

  /**
   * # Reactive Functions
   */
  Function<byte[], Integer> toInt = bytes -> bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);

  Function<Integer, Boolean> threshhold = v -> v > 5000000;

  BiPredicate<Boolean, Boolean> compareBoolean = (a, b) -> a == b;

  Predicate<Timed<Boolean>> isLongEnough = timed -> timed.time() > 50;

  Predicate<Long> isLong = time -> time > 300;

  Function<Timed<Boolean>, Character> toCode = v -> {
    if (v.value()) {
      if (isLong.test(v.time(TimeUnit.MILLISECONDS))) { return '-'; }

      return '*';
    }

    return '#';
  };

  /**
   * # Interface
   */

  public void start() {
    recorder.start();
  }

  public void stop() {
    recorder.interrupt();
  }

  public static void main(String... args) throws InterruptedException {
    Transcriptor t = new Transcriptor();

    t.start();

    Thread.sleep(100);

    t.stop();

    Thread.sleep(100);

    int x = 1;


  }
}
