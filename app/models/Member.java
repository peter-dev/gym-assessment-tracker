package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/** @author Piotr Baran */
@Entity
public class Member extends Person {

  private String address;
  private String gender;
  private float height;
  private float startWeight;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Assessment> assessments;

  public Member(
      String name,
      String email,
      String password,
      String address,
      String gender,
      float height,
      float startWeight) {
    super(name, email, password);
    this.address = address;
    this.gender = gender;
    this.height = height;
    this.startWeight = startWeight;
    this.assessments = new ArrayList<>();
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  @Override
  public String toString() {
    return "\nName:             "
        + name
        + "\nEmail:            "
        + email
        + "\nAddress:          "
        + address
        + "\nGender:           "
        + gender
        + "\nHeight:           "
        + height
        + " m"
        + "\nStart Weight:     "
        + startWeight;
  }
}
