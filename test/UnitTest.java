import akka.actor.ActorSystem;
import controllers.AddressController;
import controllers.AsyncController;
import controllers.CountController;
import org.junit.Test;
import play.mvc.Result;
import scala.concurrent.ExecutionContextExecutor;

import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static play.test.Helpers.contentAsString;

/**
 * Unit testing does not require Play application start up.
 *
 * https://www.playframework.com/documentation/latest/JavaTest
 */
public class UnitTest {

    }

    // Unit test a controller
    @Test
    public void testCount() {
        final AddressController controller = new AddressController();
        Result result = controller.count();
        assertThat(contentAsString(result)).isEqualTo("49");
    }

    // Unit test a controller with async return
    @Test
    public void testAsync() {
    }

}
