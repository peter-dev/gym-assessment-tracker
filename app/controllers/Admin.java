package controllers;

import java.util.List;
import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class Admin extends Controller {

  /** Default action, renders the app/views/admin.html template */
  public static void index() {
    Trainer trainer = (Trainer) Accounts.getLoggedInPerson(UserType.TRAINER);
    List<Member> members = Member.findAll();
    render("admin.html", trainer, members);
  }

  /**
   * Checks if Trainer is logged in and renders the app/views/assessments.html template
   *
   * @param memberid unique identifier of a gym member
   */
  public static void showMember(Long memberid) {
    Trainer trainer = (Trainer) Accounts.getLoggedInPerson(UserType.TRAINER);
    Member member = Member.findById(memberid);
    render("assessments.html", member);
  }

  /**
   * Deletes requested Member along with all Assessments assigned to the Member and redirects to the
   * app/views/admin.html template
   *
   * @param memberid unique identifier of a gym member
   */
  public static void deleteMember(Long memberid) {
    Member member = Member.findById(memberid);
    member.delete();
    Logger.info("Deleting member " + member.getName());
    index();
  }

  /**
   * Retrieves member's id, assessment's id, updates Assessment's comment in a db and redirects to
   * the app/views/assessments.html template
   *
   * @param memberid unique identifier of a gym member
   * @param id unique identifier of an assessment in the system
   * @param comment text to be used to update the comment field
   */
  public static void updateAssessment(Long memberid, Long id, String comment) {
    Assessment assessment = Assessment.findById(id);
    if (assessment != null && comment != null && !comment.isEmpty()) {
      assessment.setComment(comment);
      assessment.save();
    }
    Member member = Member.findById(memberid);
    showMember(memberid);
  }
}
