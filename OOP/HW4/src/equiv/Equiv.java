package equiv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Generic class that supports managing information
// about equiv classes of some elements of type E
public class Equiv<E> {
    private List<Set<E>> equivClasses = new ArrayList<>();

    /**
     * Setting that both specified elements are equivalent,
     * so merging both element's equivClasses to the same equivClass
     */
    public void add(E e1, E e2) {
        // adding both element if they aren't belong to some equiv class
        addIfNotExist(e1);
        addIfNotExist(e2);

        // union both equivClasses.
        Set<E> equivClass1 = get(e1);
        Set<E> equivClass2 = get(e2);

        // if both get() succeeded (always true because of `addIfNotExist` above)
        if (equivClass1 != null && equivClass2 != null) {
            // if both elements are not in the same equivClass
            if (!equivClass1.equals(equivClass2)) {
                equivClass1.addAll(equivClass2);
                equivClasses.remove(equivClass2);
            }
        }
    }

    /**
     * @return if both elements are in the same equiv class
     */
    public boolean are(E e1, E e2) {
        if (e1.equals(e2)) return true;

        for (Set<E> equivClass : equivClasses) {
            if (equivClass.contains(e1) && equivClass.contains(e2))
                return true;
        }
        return false;
    }

    /**
     * @return the equivClass of the specified element
     * if not exist returns null
     */
    private Set<E> get(E e) {
        for (Set<E> equivClass : equivClasses) {
            if (equivClass.contains(e)) return equivClass;
        }
        return null;
    }

    /**
     * @param e adding to new Set if not already exist in list
     */
    private void addIfNotExist(E e) {
        if (get(e) == null) {
            Set<E> newEquivClass = new HashSet<>();
            newEquivClass.add(e);
            equivClasses.add(newEquivClass);
        }
    }

    public static void main(String[] args) {
        Equiv<String> equiv = new Equiv<>();
        System.out.println(equiv.are("balloon", "balloon"));
        equiv.add("ball", "ball");
        equiv.add("ball", "balloon");
        equiv.add("child", "person");
        equiv.add("girl", "child");
        equiv.add("ball", "sphere");
        equiv.add("sphere", "circle");
        equiv.add("dog", "cat");

        System.out.println(equiv.are("balloon", "circle"));
        System.out.println(equiv.are("child", "girl"));
        System.out.println(equiv.are("sun", "sun"));
        System.out.println(equiv.are("dog", "ball"));
        System.out.println(equiv.are("table", "dog"));
    }
}
