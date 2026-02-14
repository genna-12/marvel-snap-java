import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import com.marvelsnap.controller.MainController;

public class MainNavigationTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;

    @Override
    protected void onSetUp() {
        MainController controller = GuiActionRunner.execute(() -> new MainController());

        window = new FrameFixture(robot(), controller.getMainFrame());
        window.show();
    }

    @Test
    public void testFullFlowToGame() {
        window.button(org.assertj.swing.core.matcher.JButtonMatcher.withText("Nuova partita")).click();

        window.textBox("p1NameField").enterText("Genna");
        window.textBox("p2NameField").enterText("Matte");

        window.button(org.assertj.swing.core.matcher.JButtonMatcher.withText("INIZIA BATTAGLIA")).click();

        window.label("labelTurno").requireText("TURNO 1/6");
    }
}