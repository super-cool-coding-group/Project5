package src.Drivers;

import src.ListADT.*;

/**
 * The ResizeableListDriver class that tests basic functionality of the
 * ResizeableList class.
 *
 * These functionalities include adding, removing, swapping, setting, getting,
 * capacity management, and numOfEntries management.
 *
 * The class only has one method: a main method.
 *
 * @author George Matta
 * @version 2.0
 */
public class ResizeableListDriver {

    /**
     * Demos typical usage of a ResizeableList object.
     *
     * We test different constructors with different capacities, test different
     * additions at various indicies,
     * clear the list, add more to the list, remove from the list, etc.
     *
     * @param args The basic String[] args for any main method in Java.
     */
    public static void main(String[] args) {

        // ResizeableList<String> negArr = new ResizeableList<>(-1);

        // ResizeableList<String> emptArr = new ResizeableList<>(0);

        ResizeableList<String> oneArr = new ResizeableList<>(1);
        System.out.println(oneArr);

        // ResizeableList<String> defArr = new ResizeableList<>();
        // System.out.println(defArr);

        ResizeableList<String> maxArr = new ResizeableList<>(10_000);
        System.out.println(maxArr);

        // ResizeableList<String> moreMaxArr = new ResizeableList<>(10_001);

        oneArr.add("1");
        oneArr.add("2");
        oneArr.add("3");
        System.out.println(oneArr);
        oneArr.add(1, "4");
        System.out.println(oneArr);
        System.out.println(oneArr.remove(1));

        oneArr.clear();

        oneArr.add("1");
        System.out.println(oneArr);
        oneArr.add("2");
        System.out.println(oneArr);
        // System.out.println(oneArr.get(0));
        System.out.println(oneArr.get(1));
        System.out.println(oneArr.get(2));
        oneArr.add("3");
        System.out.println(oneArr);
        System.out.println(oneArr.get(1));
        System.out.println(oneArr.get(3));
        oneArr.add(4, "4");
        System.out.println(oneArr);
        oneArr.add(6, "5");
        System.out.println(oneArr);
        oneArr.add(9, "6");
        System.out.println(oneArr);
        oneArr.set(1, "10");
        System.out.println(oneArr);
        oneArr.set(9, "9");
        oneArr.set(5, "5");

        System.out.println(oneArr);
        System.out.println(oneArr.getFreqOf("5"));

        oneArr.remove();
        System.out.println(oneArr);

        oneArr.remove(5);
        System.out.println(oneArr);

        oneArr.remove(2);
        System.out.println(oneArr);

        System.out.println(oneArr.get(2));

        // oneArr.remove(5);

        oneArr.swap(1, 2);
        System.out.println(oneArr);

        // oneArr.swap(1, 10);
        oneArr.swap(1, 4);
        System.out.println(oneArr);

        maxArr.add(5, "hey");
        System.out.println(maxArr);

        System.out.println(maxArr.getIndexOf("hey"));
        System.out.println(maxArr.getFreqOf("hey"));


        // maxArr.add(10_000, "he");
        // System.out.println(maxArr);
    }
}
