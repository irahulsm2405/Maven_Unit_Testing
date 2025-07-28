import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(4)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  //used to run method in order by order index/order that we want
public class MethodOrderedByOrderIndexTest {

    @Order(1)
    @Test
    void testD() {
        System.out.println("This is test D");
    }

    @Order(2)
    @Test
    void testA() {
        System.out.println("This is test A");
    }

    @Order(3)
    @Test
    void testC() {
        System.out.println("This is test C");
    }

    @Order(4)
    @Test
    void testB() {
        System.out.println("This is test B");
    }

}
