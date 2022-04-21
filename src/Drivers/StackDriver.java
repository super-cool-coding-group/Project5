package src.Drivers;

import src.StackADT.*;

/**
 * The StackDriver class that tests basic functionality of the
 * Stack class.
 *
 * The class only has one method: a main method.
 *
 * @author George Matta
 * @version 1.0
 */
public class StackDriver{

    public static void main(String[] args){

        StackInterface<Integer> testStack = new Stack<Integer>(1);

        System.out.println(testStack);

        testStack.push(1);
        System.out.println(testStack);

        testStack.push(2);
        System.out.println(testStack);

        System.out.println(testStack.peek());

        testStack.push(3);
        System.out.println(testStack);

        System.out.println(testStack.peek());
        System.out.println(testStack.pop());
        System.out.println(testStack);
    }

}
