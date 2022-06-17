import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*This is my implementation of the classic problem FizzBuzz, but with an additional condition.
The main idea is not to use conditional statements.
To do this, we will use polymorphism to "catch" one of the four states that are possible.*/

//Common interface for states.
//The only thing our interface will do is display a number or text.
interface OutputFactory {
    Logger logger = Logger.getLogger(OutputFactory.class.getName());
    void print(int value);
}

//Next, we will create one implementation of the interface to reflect the conditions of the problem in the output.
class DivBy3Print implements OutputFactory {
    public void print(int value) {
        logger.info("Fizz");
    }
}

class DivBy5Print implements OutputFactory {
    public void print(int value){
        logger.info("Buzz");
    }
}
class DivBy3And5Print implements OutputFactory {
    public void print(int value){
        logger.info("FizzBuzz");
    }
}

class NotDivPrint implements OutputFactory {
    public void print(int value) {
        logger.info(() -> "" + value);
    }
}

//Now let's create a dictionary-based class to select one of the states.
class StatementFactory {
    static Map<String, OutputFactory> statementMap = new HashMap<>();

    StatementFactory() {
        statementMap.put("truefalse", new DivBy3Print());
        statementMap.put("falsetrue", new DivBy5Print());
        statementMap.put("truetrue", new DivBy3And5Print());
        statementMap.put("falsefalse", new NotDivPrint());
    }

    public OutputFactory getStatement(String statement){ //And not forget about the getter to get the current state.
        return statementMap.get(statement);
    }
}

public class FizzBuzzWithoutConditions {
    public static void main(String[] args){
        StatementFactory statements = new StatementFactory();
        int n = 100; //This number can be anything or entered by the user.
        for (int i = 1; i <= n; i++){
            /*This is where the main "magic" of the process takes place.
            Forming a string will allow us to use it as a key to get the current
            state from the state storage class and, thanks to polymorphism, the output will
            always be the one that is needed according to the conditions of the problem.
             */
            String statement = (i % 3 == 0) + String.valueOf(i % 5 == 0);
            statements.getStatement(statement).print(i);
        }
    }
}
