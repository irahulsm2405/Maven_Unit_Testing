import com.calculator.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

@Order(2)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //was added to validate that multiplyMultiple() runs without static
public class CalculatorTestAdvanced {

    Calculator calculator = new Calculator();
    /* This is a parameterized test
    * we can multiple values through it.
    * values will be inserted from @MethodSource("multiplyMultiple") where "multiplyMultiple" is the method.
    * it will be defined separately and should be static.
    * if @TestInstance(TestInstance.Lifecycle.PER_CLASS) is used, these do not need to be static.
    * we can pass multiple values in it using Stream<Arguments>
    */
    @DisplayName("Product of numbers with diff method name")
    @ParameterizedTest
    @MethodSource("multiplyMultiple") //This method is the source of input parameters.
    void testMultiply_WhenMultiplied_ReturnProduct(int a, int b, int expected){
        int actual = calculator.multiply(a,b);
        Assertions.assertEquals(expected,actual);
    }

    @DisplayName("Product of numbers with same method name")
    @ParameterizedTest
    @MethodSource //If the name of the source method is same then we dont need to declare method name in brackets
    void multiplyMultiple(int a, int b, int expected){
        int actual = calculator.multiply(a,b);
        Assertions.assertEquals(expected,actual);
    }

    /* This is the method used to send params to above method
    * it will return arguments and for all arguments the unit test will run
    * Uncomment the test instance annotation to run the method without static
    */
    private static Stream<Arguments> multiplyMultiple() {
        return Stream.of(
                Arguments.of(2, 4, 8),
                Arguments.of(9, 9, 81),
                Arguments.of(7, 8, 56)
        );
    }

    @DisplayName("Addition using CSV source")
    @ParameterizedTest
    @CsvSource({"2,2,4",
            "3,7,10",
            "15,5,20"
    }) //We can also pass string here id needed, do it in the same way
    void testAdd_WhenAdded_ShouldReturnSum(int a, int b, int expected){
    int actual = calculator.add(a,b);
    Assertions.assertEquals(expected,actual);
    }

    @DisplayName("Subtraction using CSV file")
    @ParameterizedTest
    @CsvFileSource(resources = "/integerSubtraction.csv") //This file is in resources folder
    void testSubtraction_WhenSubtracted_ReturnSubtractedResult(int a, int b, int expected){
        int actual = calculator.subtract(a,b);
        Assertions.assertEquals(expected,actual);
    }

    //This method is used for providing values simply. It takes only one input at a time
    @DisplayName("Check if input name is not null")
    @ParameterizedTest
    @ValueSource(strings = {"Rahul","Sanjay","More"})
    void testIsNameNull(String name){
        Assertions.assertNotNull(name);
    }

    //This is for repeated tests
    @DisplayName("Repeating test")
//  @RepeatedTest(3) //Normal way to repeat the test 3 times. If we want to rename tests then use below
    @RepeatedTest(value = 3, name ="{displayName}. Repetition {currentRepetition} of {totalRepetitions}") //number of times we want to run a test
    void testDivide_WhenEightDividedByTwo_ShouldReturnFour(RepetitionInfo repetitionInfo){
        System.out.println("Current repetition: " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        int expected = 4;
        double actual = calculator.divide(8,2);
        Assertions.assertEquals(expected,actual);
    }
}
