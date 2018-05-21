package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class Dashboard extends Controller {

  public static void index() {
    Logger.info("Rendering Dashboard -> Index");
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    render("dashboard.html", member);
  }

  public static void addAssessment(
      float weight, float chest, float thigh, float upperarm, float waist, float hips) {
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    Assessment assessment = new Assessment(weight, chest, thigh, upperarm, waist, hips);
    member.getAssessments().add(assessment);
    assessment.save();
    Logger.info("Adding new Assessment " + assessment.getFormattedDate());
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id) {
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    Assessment assessment = Assessment.findById(id);
    member.getAssessments().remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment.getFormattedDate());
    redirect("/dashboard");
  }
}
