import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import com.marvelsnap.controller.MainController;

/**
 * This class tests the main navigation flow of the application.
 * I'm using AssertJ Swing to make sure that the buttons actually 
 * change the screens as expected in the MainController.
 * * @author YourName (P2)
 */
public class MainNavigationTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;

    /**
     * Standard setup method for AssertJ Swing.
     * It starts the application and hooks the testing tool to the MainFrame.
     */
    @Override
    protected void onSetUp() {
        MainController controller = GuiActionRunner.execute(() -> new MainController());

        window = new FrameFixture(robot(), controller.getMainFrame());
        window.show();
    }

    /**
     * Full flow test: goes from the Menu to the Game screen
     * by filling in the player names and clicking start.
     */
    @Test
    public void testFullFlowToGame() {
        window.button(org.assertj.swing.core.matcher.JButtonMatcher.withText("Nuova partita")).click();

        window.textBox("p1NameField").enterText("Genna");
        window.textBox("p2NameField").enterText("Matte");

        window.button(org.assertj.swing.core.matcher.JButtonMatcher.withText("INIZIA BATTAGLIA")).click();

        window.label("labelTurno").requireText("TURNO 1/6");
    }
}