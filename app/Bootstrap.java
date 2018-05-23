import models.Member;
import models.Trainer;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/** @author Piotr Baran */
@OnApplicationStart
public class Bootstrap extends Job {

  @Override
  public void doJob() throws Exception {

    if (Member.count() == 0 && Trainer.count() == 0) {
      Fixtures.loadModels("data.yml");
    }

  }
}
