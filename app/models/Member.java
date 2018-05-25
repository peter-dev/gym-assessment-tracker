package models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import utils.GymUtility;

/**
 * Subclass of Person. Stores member's address gender, height, and starting weight.
 *
 * @author Piotr Baran
 */
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

  /**
   * Return assessment sorted by Time Stamp
   *
   * @return sorted list of Assessments
   */
  public List<Assessment> getSortedAssessments() {
    ArrayList<Assessment> sortedAssessments = new ArrayList();
    sortedAssessments.addAll(assessments);
    Collections.sort(
        sortedAssessments, (arg0, arg1) -> arg1.getTimeStamp().compareTo(arg0.getTimeStamp()));
    return sortedAssessments;
  }

  /**
   * Return latest Assessment from the list of Assessments
   *
   * @return most recent Assessment, null if list is empty
   */
  public Assessment getLatestAssessment() {
    return (assessments == null || assessments.isEmpty()) ? null : getSortedAssessments().get(0);
  }

  /**
   * Uses GymUtility Class to calculate the BMI for the member based on the calculation: BMI is
   * weight divided by the square of the height
   *
   * @return calculated bmi value, 0 if no registered assessments found
   */
  public double getCurrentBmi() {
    if (assessments == null || assessments.isEmpty()) {
      return 0.0d;
    }
    DecimalFormat oneDForm = new DecimalFormat("#.#");
    return Double.valueOf(oneDForm.format(GymUtility.calculateBMI(this, getLatestAssessment())));
  }

  /**
   * Uses GymUtility Class to determine the category the BMI belongs to
   *
   * @return the category the BMI belongs to, N/A if no registered assessments found
   */
  public String getCurrentBmiCategory() {
    return (assessments == null || assessments.isEmpty())
        ? "N/A"
        : GymUtility.determineBMICategory(getCurrentBmi());
  }

  /**
   * Uses GymUtility Class to determine if member has ideal weight
   *
   * @return true if ideal, false if not ideal
   */
  public boolean isIdealBodyWeight() {
    return (assessments != null && !assessments.isEmpty())
        && GymUtility.isIdealBodyWeight(this, getLatestAssessment());
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
