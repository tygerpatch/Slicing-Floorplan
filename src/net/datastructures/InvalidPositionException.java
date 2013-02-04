package net.datastructures;

/**
 * Thrown when a position is determined to be invalid.
 * 
 * @author Roberto Tamassia, Michael Goodrich
 */
// A run-time exception for invalid positions
public class InvalidPositionException extends RuntimeException {
  public InvalidPositionException(String err) {
    super(err);
  }

  public InvalidPositionException() {
    /* default constructor */
  }
}

