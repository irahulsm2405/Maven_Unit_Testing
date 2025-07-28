import com.calculator.Calculator;
import org.junit.jupiter.api.*;

@Order(1)
//@DisplayName(value = "Test Math operations in Calculator class")  //Used to change classname in report
public class CalculatorTestBasic {

    Calculator c;

    //Used for initialization
    //if @TestInstance(TestInstance.Lifecycle.PER_CLASS) is used, these do not need to be static.
    @BeforeAll
    static void setup(){
        System.out.println("Preparing resources");
    }

    @BeforeEach
    void beforeEachMethod(){
        c = new Calculator();
        System.out.println("I am running before each method. Initializing calculator object");
    }

    //Used for cleanup purposes
    //if @TestInstance(TestInstance.Lifecycle.PER_CLASS) is used, these do not need to be static.
    @AfterAll
    static void cleanup(){
        System.out.println("Cleaning up resources");
    }

    @AfterEach
     void afterEachMethod(){
        System.out.println("I am running after each method");
    }

    @Test
    void add_Test(){
        int actual = c.add(2,4);
        int expected = 6;

        Assertions.assertEquals(actual, expected, "4+2 was not 6");
    }

    @Test
    void subtract_Test(){
        int actual = c.subtract(10,6);
        int expected = 4;
        Assertions.assertEquals(expected,actual,"10-6 was not 4");
    }

    @Test
    void multiply_Test(){
        int actual = c.multiply(6,4);
        int expected = 24;
        Assertions.assertEquals(expected,actual,"6*4 was not 24");
    }

    @Test
    void divide_Test(){
        int a = 21;
        int b = 3;
        double actual = c.divide(a,b);
        double expected = 7;
        Assertions.assertEquals(expected,actual,()->a + "/" + b +" was not " + expected); //this can be written without lambda too and it will work as expected
    }

    @Test
    //When the test is too big and functionality cannot be easily identified by reading the code use naming convention like this
    //test{MethodName}_{SystemName/WhatMethodIsSupposedToDo}_{ExpectedResult}
    void testAdd_WhenAddingTwoAndFour_ShouldReturnSix(){
        int actual = c.add(2,4);
        int expected = 6;
        Assertions.assertEquals(actual, expected, "4+2 was not 6");
    }

    @Test
    //This method name looks too long in the test report, so to shorten it and make it less technical in report we use this
    @DisplayName(value = "Test multiply 9*10 = 90")
    void testMultiply_WhenMultiplyingNineAndTen_ShouldReturnNinety(){
        int actual = c.multiply(9,10);
        int expected = 90;
        Assertions.assertEquals(expected,actual,"9+10 was not 90");
    }

    @Test
    @DisplayName(value = "Test divide 28/4 = 7")
    void testDivide_WhenTwentyEightDividedByFour_ShouldReturnSeven(){
        //Arrange
        Calculator c = new Calculator();
        int a = 28;
        int b = 4;
        double expected = 7;

        // Act
        double actual = c.divide(a,b);

        //Assert
        Assertions.assertEquals(expected,actual,a + "/" + b +" was not " + expected);
    }

    @Test
    @DisplayName("9/0 throws arithmatic exception")
    //This method will pass the test if arithmetic exception is thrown. If not thrown it will fail.
    void testDivision_WhenDividedByZero_ShouldThrowArithmeticException(){
        //Arrange
        int divident = 9;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        //Act and Assert
        ArithmeticException actualException = Assertions.assertThrows(ArithmeticException.class,
                                                                      ()->c.divide(divident,divisor),
                                                              "Division by zero should have thrown an arithmatic exception");

        Assertions.assertEquals(expectedExceptionMessage,actualException.getMessage());
    }
}
