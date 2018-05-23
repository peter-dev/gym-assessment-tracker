package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class Dashboard extends Controller {

  /** Default action, renders the app/views/dashboard.html template */
  public static void index() {
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    render("dashboard.html", member);
  }

  /**
   * Adds new Assessment to the gym member that is currently logged in and redirects to the
   * app/views/dashboard.html template
   *
   * @param weight
   * @param chest
   * @param thigh
   * @param upperarm
   * @param waist
   * @param hips
   */
  public static void addAssessment(
      float weight, float chest, float thigh, float upperarm, float waist, float hips) {
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    Assessment assessment = new Assessment(weight, chest, thigh, upperarm, waist, hips);
    member.getAssessments().add(assessment);
    assessment.save();
    Logger.info("Adding new Assessment " + assessment.getFormattedDate());
    redirect("/dashboard");
  }

  /**
   * Deletes requested Assessment that belongs to currently logged in gym member and redirects to
   * the app/views/dashboard.html template
   *
   * @param id unique id of the Assessment to be deleted
   */
  public static void deleteAssessment(Long id) {
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    Assessment assessment = Assessment.findById(id);
    member.getAssessments().remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting assessment " + assessment.getFormattedDate());
    redirect("/dashboard");
  }
}
