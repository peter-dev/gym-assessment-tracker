package models;

import javax.persistence.Entity;

/** @author Piotr Baran */
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
