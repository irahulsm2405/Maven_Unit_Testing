import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(3)
@TestMethodOrder(MethodOrderer.MethodName.class)  //used to run method in order by name
public class MethodOrderedByNameTest {

    @Test
    void testA() {
        System.out.println("This is test A");
    }

    @Test
    void testB() {
        System.out.println("This is test B");
    }

    @Test
    void testC() {
        System.out.println("This is test C");
    }

    @Test
    void testD() {
        System.out.println("This is test D");
    }
}
