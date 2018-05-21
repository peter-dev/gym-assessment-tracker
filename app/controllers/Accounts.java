package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class Accounts extends Controller {

  public static void signup() {
    Logger.info("Rendering Accounts -> Signup");
    render("signup.html");
  }

  public static void login() {
    Logger.info("Rendering Accounts -> Login");
    render("login.html");
  }

  public static void logout() {
    session.clear();
    redirect("/");
  }

  public static void register(
      String name,
      String email,
      String password,
      String address,
      String gender,
      float height,
      float startWeight) {
    Logger.info("Registering new user " + email);
    Member member = new Member(name, email, password, address, gender, height, startWeight);
    member.save();
    redirect("/dashboard");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    // identify if Member or Trainer
    Person person = Member.find("email", email).first();
    person = (person == null) ? Trainer.find("email", email).first() : person;

    if (person != null && person.checkPassword(password)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Personid", person.id);
      if (person instanceof Member) redirect("/dashboard");
      if (person instanceof Trainer) redirect("/admin");

      redirect("/");
    } else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static Person getLoggedInPerson(UserType userType) {
    Person person = null;
    if (session.contains("logged_in_Personid")) {
      String personId = session.get("logged_in_Personid");

      switch (userType) {
        case MEMBER:
          person = Member.findById(Long.parseLong(personId));
          break;
        case TRAINER:
          person = Trainer.findById(Long.parseLong(personId));
          break;
      }
    }
    /*
    Application did not identify correct user privileges, i.e
    Member tries to access /admin page but Member ia not a Trainer
     */
    if (person == null) {
      session.clear();
      redirect("/login");
    }

    return person;
  }
}
