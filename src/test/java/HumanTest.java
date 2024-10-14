import org.junit.Assert;
import org.junit.Test;
import ru.practicum.mediasoft.Human;

import java.time.LocalDate;

public class HumanTest {

    @Test
    public void testValidEqualsObjects() {
        Human human = new Human("John", LocalDate.now().plusDays(1), "male");
        Human human2 = new Human("John", LocalDate.now().plusDays(1), "male");
        Assert.assertEquals(human, human2);
    }

    @Test
    public void testInvalidEqualsObjects() {
        Human human = new Human("John", LocalDate.now().plusDays(1), "male");
        Human human2 = new Human("John", LocalDate.now().plusDays(2), "male");
        Assert.assertNotEquals(human, human2);
    }


}
