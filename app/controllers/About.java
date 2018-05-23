package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class About extends Controller {

  /** Default action, renders the app/views/about.html template */
  public static void index() {
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    render("about.html");
  }
}
