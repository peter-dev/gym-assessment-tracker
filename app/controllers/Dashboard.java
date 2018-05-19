package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class Dashboard extends Controller {

  public static void index() {
    Logger.info("Rendering Dashboard -> Index");
    Member member = (Member)Accounts.getLoggedInPerson(UserType.MEMBER);
    render("dashboard.html", member);
  }
}
