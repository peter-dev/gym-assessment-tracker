package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

/** @author Piotr Baran */
@Entity
public class Member extends Model {

  private String name;
  private String email;
  private String password;
  private String gender;
  private float height;
  private float startWeight;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Assessment> assessments;

  public Member(
      String name, String email, String password, String gender, float height, float startWeight) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.height = height;
    this.startWeight = startWeight;
    this.assessments = new ArrayList<>();
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public float getStartWeight() {
    return startWeight;
  }

  public void setStartWeight(float startWeight) {
    this.startWeight = startWeight;
  }

  public List<Assessment> getAssessments() {
    return assessments;
  }

  public void setAssessments(List<Assessment> assessments) {
    this.assessments = assessments;
  }
}
