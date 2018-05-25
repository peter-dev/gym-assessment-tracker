package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * It stores weight, chest, thigh, upper arm, waist, hips, comment and time stamp that uniquely
 * identifies the assessment in the system.
 *
 * @author Piotr Baran
 */
@Entity
public class Assessment extends Model {

  private float weight;
  private float chest;
  private float thigh;
  private float upperArm;
  private float waist;
  private float hips;
  private int trend;
  private String comment;
  private Date timeStamp;

  public Assessment(
      float weight, float chest, float thigh, float upperArm, float waist, float hips) {
    this.weight = weight;
    this.chest = chest;
    this.thigh = thigh;
    this.upperArm = upperArm;
    this.waist = waist;
    this.hips = hips;
    this.trend = 0;
    this.timeStamp = new Date();
  }

  /**
   * Returns Assessment's time stamp as String formatted in the following way: dd-MMM-yyyy hh:mm:ss
   *
   * @return time stamp as String
   */
  public String getFormattedDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
    return sdf.format(timeStamp);
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getChest() {
    return chest;
  }

  public void setChest(float chest) {
    this.chest = chest;
  }

  public float getThigh() {
    return thigh;
  }

  public void setThigh(float thigh) {
    this.thigh = thigh;
  }

  public float getUpperArm() {
    return upperArm;
  }

  public void setUpperArm(float upperArm) {
    this.upperArm = upperArm;
  }

  public float getWaist() {
    return waist;
  }

  public void setWaist(float waist) {
    this.waist = waist;
  }

  public float getHips() {
    return hips;
  }

  public void setHips(float hips) {
    this.hips = hips;
  }

  public int getTrend() {
    return trend;
  }

  public void setTrend(int trend) {
    this.trend = trend;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  @Override
  public String toString() {
    return "\nDate:             "
        + getFormattedDate()
        + "\nWeight:           "
        + weight
        + "\nChest:            "
        + chest
        + "\nThigh:            "
        + thigh
        + "\nUpper Arm:        "
        + upperArm
        + "\nWaist:            "
        + waist
        + "\nHips:            "
        + hips
        + "\nComment:          "
        + comment;
  }
}
