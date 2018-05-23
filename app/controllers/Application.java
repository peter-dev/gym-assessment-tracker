package controllers;

import play.mvc.Controller;
import play.Logger;

/** @author Piotr Baran on 16/05/2018 */
public class Application extends Controller {

  /** Default action, renders the app/views/start.html template */
  public static void index() {
    render("start.html");
  }
}
