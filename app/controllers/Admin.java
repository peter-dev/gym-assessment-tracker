package controllers;

import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class Admin extends Controller {

  public static void index() {
    Logger.info("Rendering Admin -> Index");
    Trainer trainer = (Trainer)Accounts.getLoggedInPerson(UserType.TRAINER);
    render("admin.html", trainer);
  }
}
