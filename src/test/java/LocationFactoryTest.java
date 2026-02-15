import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.marvelsnap.util.LocationFactory;
import com.marvelsnap.model.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for LocationFactory.
 */
public class LocationFactoryTest {
    /**
    * Tests whether LocationFactory actually produces three different locations.
    */
    @Test
    void testLocationFactory() {
        LocationFactory locationFactory = new LocationFactory();
        List<Location> locations = new ArrayList<>(locationFactory.createLocations());
        assertEquals(3, locations.size());
        assertNotEquals(locations.get(0), locations.get(1));
        assertNotEquals(locations.get(0), locations.get(2));
        assertNotEquals(locations.get(1), locations.get(2));
    }
}