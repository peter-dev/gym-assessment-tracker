package controllers;

import java.util.List;
import models.Member;
import models.Trainer;
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
}
