package tasks;

/**
 * Tasks represents tasks as numbers, and handles adding dependents
 * and ordering them if possible.
 */
public class Tasks {
    // holds all tasks and their dependents
    // (i.g: task 3 depends on 2 => tasks[3][2] == 1)
    // 0 in inner array stands for "no dependency"
    private boolean[][] tasks;

    public Tasks(int num) {
        tasks = new boolean[num][num];
    }

    public boolean dependsOn(int task1, int task2) {
        if (isInvalidTask(task1) || isInvalidTask(task2)) return false;

        // put "1" (=task1 depends on task2) to the appropriate cell.
        tasks[task1][task2] = true;
        return true;
    }

    public int[] order() {
        int[] outputOrder = new int[tasks.length];
        arrFill(outputOrder, -1);
        int outputIndex = 0;
        int task = 0;

        // removing all standalone tasks from dependency in other tasks
        while (task < tasks.length) {
            // checking if a task is standalone and not been found yet.
            if (!contains(outputOrder, task) && isStandaloneTask(task)) {
                outputOrder[outputIndex++] = task;
                clearDependents(task);
                // re-run on all task again
                task = 0;
            } else
                task++;
        }

        // checking if there are dependencies left. if so, returns null as required.
        if (outputIndex < tasks.length - 1) return null;

        return outputOrder;
    }

    /**
     * @param task to check validation on.
     * @return whether a task isn't in the correct range.
     */
    private boolean isInvalidTask(int task) {
        return task < 0 || task >= tasks.length;
    }

    /**
     * @param task to check if it's standalone
     * @return whether a task is not dependents on anything (standalone)
     */
    private boolean isStandaloneTask(int task) {
        for (boolean isDependent : tasks[task]) {
            if (isDependent) return false;
        }
        return true;
    }

    /**
     * clear dependents of on given task
     *
     * @param task to clear dependence on
     */
    private void clearDependents(int task) {
        for (int i = 0; i < tasks.length; i++) {
            // reset dependence
            if (tasks[i][task]) tasks[i][task] = false;
        }
    }

    /**
     * @param arr to search in
     * @param i   the specified element to search for
     * @return true if this arr contains the specified i.
     */
    private static boolean contains(int[] arr, int i) {
        for (int element : arr) {
            if (element == i) return true;
        }
        return false;
    }

    /**
     * Init items in specified arr to -1
     * @param arr to init in
     * @param value the value to insert to all item
     */
    private static void arrFill(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) arr[i] = value;
    }
}
