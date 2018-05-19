package models;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import play.db.jpa.Model;

/**
 * @author Piotr Baran
 */
@MappedSuperclass
public class Person extends Model {
  String name;
  String email;
  String password;

  public Person() {}

  public Person(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public boolean checkPassword(String password) {
    return (this.password.equals(password));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "\nName:             "
        + name
        + "\nEmail:            "
        + email
        + "\nAddress:          "
        + password;
  }
}
