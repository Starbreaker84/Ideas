import java.util.HashMap;
import java.util.Map;

abstract class Statement{
    protected int value;
    Statement(int value){
        this.value = value;
    }
    public abstract void print();
}

class DivBy3 extends Statement {
    DivBy3(int value) {
        super(value);
    }

    public void print() {
        System.out.println("Fizz");
    }
}

class DivBy5 extends Statement {
    DivBy5(int value) {
        super(value);
    }

    public void print(){
        System.out.println("Buzz");
    }
}

class DivBy35 extends Statement {
    DivBy35(int value) {
        super(value);
    }

    public void print(){
        System.out.println("FizzBuzz");
    }
}

class NotDiv extends Statement {
    NotDiv(int value) {
        super(value);
    }

    public void print() {
        System.out.println(this.value);
    }
}

class StatementFactory {
    static Map<String, Statement> statementMap = new HashMap<>();

    StatementFactory(int value) {
        statementMap.put("truefalse", new DivBy3(value));
        statementMap.put("falsetrue", new DivBy5(value));
        statementMap.put("truetrue", new DivBy35(value));
        statementMap.put("falsefalse", new NotDiv(value));
    }

    public Statement getStatement(String statement){
        return statementMap.get(statement);
    }

}

public class FizzBuzz {
    public static void main(String[] args){
        int n = 100;
        for (int i = 1; i <= n; i++){
            StatementFactory factory = new StatementFactory(i);
            String statement = (i % 3 == 0) + String.valueOf(i % 5 == 0);
            factory.getStatement(statement).print();
        }
    }
}
