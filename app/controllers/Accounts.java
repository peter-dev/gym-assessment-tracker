package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import utils.UserType;

/** @author Piotr Baran */
public class Accounts extends Controller {

  public static final String LOGGED_IN_ID = "logged_in_Personid";

  /** Renders the app/views/signup.html template */
  public static void signup() {
    render("signup.html");
  }

  /** Renders the app/views/login.html template */
  public static void login() {
    render("login.html");
  }

  /**
   * Retrieves currently logged in gym member and renders the app/views/settings.html template;
   * passes the member details to the view so the form can be pre-populated
   */
  public static void settings() {
    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    render("settings.html", member);
  }

  /** Clears session and redirect to the home page */
  public static void logout() {
    session.clear();
    redirect("/");
  }

  /**
   * Registers new Member in the system and redirects to the app/views/dashboard.html template
   *
   * @param name
   * @param email
   * @param password
   * @param address
   * @param gender
   * @param height
   * @param startWeight
   */
  public static void register(
      String name,
      String email,
      String password,
      String address,
      String gender,
      float height,
      float startWeight) {
    Member member = new Member(name, email, password, address, gender, height, startWeight);
    member.save();
    Logger.info("Registering new user " + email);
    redirect("/dashboard");
  }

  /**
   * Authenticates user email and password, if user is a Trainer, redirects to the
   * app/views/admin.html template if user is a Member, redirects to the app/views/dashboard.html
   * template if password is not matching, edirects to the app/views/login.html template
   *
   * @param email
   * @param password
   */
  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    // identify if Member or Trainer
    Person person = Member.find("email", email).first();
    person = (person == null) ? Trainer.find("email", email).first() : person;

    if (person != null && person.checkPassword(password)) {
      Logger.info("Authentication successful");
      session.put(LOGGED_IN_ID, person.id);
      if (person instanceof Member) redirect("/dashboard");
      if (person instanceof Trainer) redirect("/admin");
    } else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  /**
   * Updates member profile and redirects to the app/views/dashboard.html template
   *
   * @param name
   * @param password
   * @param address
   * @param gender
   * @param height
   * @param startWeight
   */
  public static void update(
      String name,
      String password,
      String address,
      String gender,
      float height,
      float startWeight) {

    Member member = (Member) Accounts.getLoggedInPerson(UserType.MEMBER);
    // identify fields that were updated
    if (member != null) {
      if (name != null && !name.isEmpty()) member.setName(name);
      if (password != null && !password.isEmpty()) member.setPassword(password);
      if (address != null && !address.isEmpty()) member.setAddress(address);
      if (gender != null && !gender.isEmpty()) member.setGender(gender);
      if (height != 0.0f) member.setHeight(height);
      if (startWeight != 0.0f) member.setStartWeight(startWeight);
      member.save();
    }
    render("dashboard.html", member);
  }

  /**
   * Deletes requested Member along with all Assessments assigned to the Member and redirects to the
   * app/views/admin.html template
   *
   * @param id unique id of the Member to be deleted
   */
  public static void deleteMember(Long id) {
    Trainer trainer = (Trainer) Accounts.getLoggedInPerson(UserType.TRAINER);
    Member member = Member.findById(id);
    member.delete();
    Logger.info("Deleting member " + member.getName());
    redirect("/admin");
  }

  /**
   * Checks session cookie and retrieves currently logged in user from the db (member or trainer)
   *
   * @param userType specifies what type of user is being validated
   * @return Person (Trainer or Member)
   */
  public static Person getLoggedInPerson(UserType userType) {
    Person person = null;
    if (session.contains(LOGGED_IN_ID)) {
      String personId = session.get(LOGGED_IN_ID);

      if (userType == UserType.MEMBER) {
        person = Member.findById(Long.parseLong(personId));
      } else if (userType == UserType.TRAINER) {
        person = Trainer.findById(Long.parseLong(personId));
      }
    }
    /*
    Application did not identify correct user privileges, i.e
    Member tries to access /admin page but Member is not a Trainer
     */
    if (person == null) {
      session.clear();
      redirect("/login");
    }

    return person;
  }
}
