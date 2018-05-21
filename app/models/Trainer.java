package models;

import javax.persistence.Entity;

/**
 * Subclass of Person. No additional fields.
 *
 * @author Piotr Baran
 */
@Entity
public class Trainer extends Person {

  public Trainer(String name, String email, String password) {
    super(name, email, password);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
