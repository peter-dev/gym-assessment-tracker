package utils;

import java.util.List;
import models.Assessment;
import models.Member;

/**
 * A utility class (or helper class) that has only static methods and encapsulates no state (fields)
 */
public class GymUtility {

  /** A list of constants that can be used to determine BMI category */
  public enum BmiLevel {
    L1(16d, "SEVERELY UNDERWEIGHT"),
    L2(18.5d, "UNDERWEIGHT"),
    L3(25d, "NORMAL"),
    L4(30d, "OVERWEIGHT"),
    L5(35d, "MODERATELY OBESE"),
    L6(35d, "SEVERELY OBESE");

    private final double bmiRange;
    private final String bmiDescription;

    BmiLevel(double bmiRange, String bmiDescription) {
      this.bmiRange = bmiRange;
      this.bmiDescription = bmiDescription;
    }

    double getBmiRange() {
      return bmiRange;
    }

    String getBmiDescription() {
      return bmiDescription;
    }
  }

  /**
   * Return the BMI for the member based on the calculation: BMI is weight divided by the square of
   * the height.
   *
   * @param member a member representation that stores the height of the gym member
   * @param assessment assessment that contains the latest weight
   * @return calculated bmi value
   */
  public static double calculateBMI(Member member, Assessment assessment) {
    float weight = assessment.getWeight();
    float height = member.getHeight() / 100;
    return (double) weight / Math.pow(height, 2);
  }

  /**
   * Return the category the BMI belongs to, based on the predefined values in BmiLevel Class
   *
   * @param bmiValue calculated BMI value
   * @return the category the BMI belongs to
   */
  public static String determineBMICategory(double bmiValue) {

    if (bmiValue < BmiLevel.valueOf("L1").getBmiRange()) {
      return BmiLevel.valueOf("L1").getBmiDescription();
    } else if (bmiValue < BmiLevel.valueOf("L2").getBmiRange()) {
      return BmiLevel.valueOf("L2").getBmiDescription();
    } else if (bmiValue < BmiLevel.valueOf("L3").getBmiRange()) {
      return BmiLevel.valueOf("L3").getBmiDescription();
    } else if (bmiValue < BmiLevel.valueOf("L4").getBmiRange()) {
      return BmiLevel.valueOf("L4").getBmiDescription();
    } else if (bmiValue < BmiLevel.valueOf("L5").getBmiRange()) {
      return BmiLevel.valueOf("L5").getBmiDescription();
    } else {
      return BmiLevel.valueOf("L6").getBmiDescription();
    }
  }

  /**
   * Returns a boolean to indicate if the member has an ideal body weight.
   *
   * @param member a representation of a gym member
   * @param assessment latest member's assessment with that stores required weight information
   * @return
   */
  public static boolean isIdealBodyWeight(Member member, Assessment assessment) {

    // member weight and height
    float weightInKg = assessment.getWeight();
    float heightInInch = metreToInchConversion(member.getHeight() / 100);
    String gender = member.getGender();

    // formula values
    float baseHeightInInch = 60f; // 5 feet x 12 = 60 inches
    float baseWeightMale = 50f;
    float baseWeightFemale = 45.5f;
    float incrementalWeight = 2.3f;

    // if member is 5 feet or less return 50kg for male and 45.5kg for female
    if (heightInInch <= baseHeightInInch) {
      return (gender.equals("M"))
          ? (weightInKg == baseWeightMale)
          : (weightInKg == baseWeightFemale);
    }

    // assign correct base weight
    float baseWeight = (gender.equals("M")) ? baseWeightMale : baseWeightFemale;

    // add 2.3kg for each inch over 5 feet (60 inches)
    float calculatedIdeal = baseWeight + (incrementalWeight * (heightInInch - baseHeightInInch));

    return (weightInKg > calculatedIdeal - 0.2f && weightInKg < calculatedIdeal + 0.2f);
  }

  /**
   * Returns an integer to determine if trend is up (1), down (-1), or no change (0)
   *
   * @param member a representation of a gym member
   * @param newEntry new assessment being added to the system
   * @return
   */
  public static int determineTrend(Member member, Assessment newEntry) {
    List<Assessment> sortedAssessments = member.getSortedAssessments();

    if (sortedAssessments.isEmpty()) {
      // no previous assessments available, compare to the starting weight
      if (newEntry.getWeight() > member.getStartWeight()) return 1;
      if (newEntry.getWeight() < member.getStartWeight()) return -1;
      // default, no change
      return 0;
    } else {
      // at least one previous assessment available, compare with the last entry
      if (newEntry.getWeight() > sortedAssessments.get(0).getWeight()) return 1;
      if (newEntry.getWeight() < sortedAssessments.get(0).getWeight()) return -1;
      // default, no change
      return 0;
    }
  }

  /**
   * Converts height in meters to inches.
   *
   * @param lengthMeters length in meters as float
   * @return length in inches
   */
  public static float metreToInchConversion(float lengthMeters) {
    // 1 metre = 39.3701 inch
    return (lengthMeters * 39.3701f);
  }
}
