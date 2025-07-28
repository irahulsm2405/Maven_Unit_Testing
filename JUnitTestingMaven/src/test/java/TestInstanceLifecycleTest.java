import org.junit.jupiter.api.*;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD) //New instance of class is created for every method. Cannot share method states
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Single instance of class is created for all methods. Can share method states
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  //used to run method in order by order index/order that we want
public class TestInstanceLifecycleTest {

    StringBuilder build = new StringBuilder();

    @Order(1)
    @Test
    void testD() {
        System.out.println("This is test D");
        build.append("A");
        System.out.println(build);
    }

    @Order(2)
    @Test
    void testA() {
        System.out.println("This is test A");
        build.append("B");
        System.out.println(build);
    }

    @Order(3)
    @Test
    void testC() {
        System.out.println("This is test C");
        build.append("C");
        System.out.println(build);
    }

    @Order(4)
    @Test
    void testB() {
        System.out.println("This is test B");
        build.append("D");
        System.out.println(build);
    }

}
