import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/** @author Piotr Baran */
@OnApplicationStart
public class Bootstrap extends Job {

  @Override
  public void doJob() throws Exception {

    // https://www.playframework.com/documentation/1.4.x/test#fixtures
    // due to error: Cannot load fixture data.yml, duplicate id
    Fixtures.deleteDatabase();
    Fixtures.loadModels("data.yml");

    /*if (Member.count() == 0 && Trainer.count() == 0) {
      Fixtures.loadModels("data.yml");
    }*/

  }
}
