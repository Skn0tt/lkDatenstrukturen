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
   * # Reactive Functions
   */
  private Thread analyzer = new Thread(() -> {
    Observable<Integer> ints = input
      .map(toInt)
      .map(Math::abs);

    Observable<Boolean> onOff = ints.map(threshhold);

    Observable<Boolean> distinct = onOff.distinctUntilChanged(compareBoolean);

    // distinct.subscribe(System.out::println);

    Observable<Timed<Boolean>> timed = distinct.timeInterval();

    timed.subscribe(v -> System.out.println(v.value() + " " + v.time()));

    Observable<String> codes = timed.map(toCode);

    /*
    Observable<Boolean> longEnough = timed.filter(isLongEnough).map(Timed::value);

    Observable<Timed<Boolean>> finalEvents = longEnough.timeInterval();

    // finalEvents.subscribe(v -> System.out.println(v.time(TimeUnit.MILLISECONDS) + " " + v.value()));

    Observable<Character> codes = finalEvents.map(toCode);
    */

    codes.subscribe(System.out::print);
  });

  private static Function<byte[], Integer> toInt = bytes -> bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);

  private static Function<Integer, Boolean> threshhold = v -> v > 120000000;

  private static BiPredicate<Boolean, Boolean> compareBoolean = (a, b) -> a == b;

  private static Predicate<Timed> isLong = v -> v.time(TimeUnit.MILLISECONDS) > 300;

  private static Predicate<Timed> isDivider = v -> v.time(TimeUnit.MILLISECONDS) > 500;

  private static Function<Timed<Boolean>, String> toCode = v -> {
    if (!v.value()) { // mic was off -> it's a sign
      return isLong.test(v) ? " -" : " *";
    } else { // mic was on -> it's a break
      return isDivider.test(v) ? " #" : "";
    }
  };

  /**
   * # Interface
   */

  public void start() {
    recorder.start();

    analyzer.start();
  }

  public void stop() {
    recorder.interrupt();
  }

  public static void main(String... args) throws InterruptedException {
    Transcriptor t = new Transcriptor();

    t.start();
  }
}
