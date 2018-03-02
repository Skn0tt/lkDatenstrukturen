/*
 * Copyright (c) Simon Knott 2018.
 */

public interface ComparableContent<T> {
  boolean isLess(T than);
  boolean isEqual(T than);
  boolean isGreater(T than);
}
