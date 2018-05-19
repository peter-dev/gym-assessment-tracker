package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * @author Piotr Baran
 */
public class About extends Controller {

  public static void index() {
    Logger.info("Rendering About -> Index");
    render("about.html");
  }
}
