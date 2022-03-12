package tasks;

import java.util.Arrays;

public class Tasks {
    // holds all tasks and their dependents
    // (i.g: task 3 depends on 2 => tasks[3][2] == 1)
    // 0 in inner array stands for "no dependency"
    private int[][] tasks;

    public Tasks(int num) {
        tasks = new int[num][num];
    }

    public boolean dependsOn(int task1, int task2) {
        if (!isValidTask(task1) || !isValidTask(task2)) return false;

        tasks[task1][task2] = 1;
        return true;
    }

    public int[] order() {
        int[] outputOrder = new int[tasks.length];
        int outputIndex = 0;
        int task = 0;
        boolean isStandalone;

        // removing all standalone tasks from dependency in other tasks
        while (task < tasks.length) {
            // checking if a task is standalone and not been found yet.
            if (!contains(outputOrder, task) && isStandaloneTask(task)) {
                outputOrder[outputIndex++] = task;
                clearDependents(task);
                task = 0;
            } else
                task++;
        }

        // checking if there are dependencies left. if so, returns null as required.
        for (int[] taskDependents : tasks) {
            for (int aDependent : taskDependents) {
                // if aDependent is exists in taskDependents
                if (aDependent == 1) return null;
            }
        }

        return outputOrder;
    }

    private boolean isValidTask(int task) {
        return task >= 0 && task < tasks.length;
    }

    private boolean isStandaloneTask(int task) {
        for (int isDependent : tasks[task]) {
            if (isDependent == 1) return false;
        }
        return true;
    }

    /**
     * clear dependents of on given task
     *
     * @param task
     */
    private void clearDependents(int task) {
        for (int i = 0; i < tasks.length; i++) {
            // reset dependence
            if (tasks[i][task] == 1) tasks[i][task] = 0;
        }
    }

    /**
     * @param arr to search in
     * @param i   the specified element to search for
     * @return true if this arr contains the specified i.
     */
    private boolean contains(int[] arr, int i) {
        for (int element : arr) {
            if (element == i) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Tasks t = new Tasks(6);
        t.dependsOn(3, 2);
        t.dependsOn(0, 3);
        t.dependsOn(2, 5);
        t.dependsOn(4, 5);
        boolean x = t.dependsOn(5, 6);
//        t.dependsOn(5, 3);
        System.out.println(Arrays.toString(t.order()));
        System.out.println(x);
    }
}
