package ma.enset.digitalbankingbackend.exceptions;

public class BalanceNotSufficientException extends Exception {
  public BalanceNotSufficientException(String message) {
    super(message);
  }
}
