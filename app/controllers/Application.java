package controllers;

import play.mvc.Controller;
import play.Logger;

/** @author Piotr Baran on 16/05/2018 */
public class Application extends Controller {

  public static void index() {
    Logger.info("Rendering Application Start Page");
    render("start.html");
  }
}
