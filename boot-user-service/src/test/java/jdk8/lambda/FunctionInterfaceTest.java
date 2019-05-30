package jdk8.lambda;

import org.junit.Test;

public class FunctionInterfaceTest {


    @Test
    public void testlambda() {
        func(new FunctionInterface() {

            @Override
            public boolean test(Object param) {
                System.out.println("lambda" + param);
                return false;
            }
        });

        func((x) -> {
            System.out.println("lamdbda ===");
            return true;
        });
    }

    private void func(FunctionInterface functionInterface) {
        functionInterface.test(100);
    }
}
