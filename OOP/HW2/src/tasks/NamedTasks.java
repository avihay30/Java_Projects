package tasks;

/**
 * NamedTasks represents tasks as names, and handles adding dependents
 * and ordering them if possible, by subclassing Tasks Class.
 */
public class NamedTasks extends Tasks {
    // holds all names for tasks,
    // names[i] dependencies is represented in Tasks superclass as tasks[i] array.
    private String[] names;

    public NamedTasks(String[] names) {
        super(names.length);
        this.names = new String[names.length];
        // copy names array to this.names
        for (int i = 0; i < names.length; i++) {
            this.names[i] = names[i];
        }
    }

    /**
     * adding task1 is dependent on task2
     * @return true if adding dependence has been done. false, if tasks are invalid.
     */
    public boolean dependsOn(String task1, String task2) {
        return super.dependsOn(getIndexOf(task1), getIndexOf(task2));
    }

    /**
     * ordering names array according to super.order() algorithm
     * @return ordered array of named tasks.
     */
    public String[] nameOrder() {
        // calling order of super.
        int[] orderedTasks = order();
        // if order() returns that order arrangement cannot be done.
        if (orderedTasks == null) return null;
        // init new empty arr to be returned
        String[] orderedNamedTasks = new String[orderedTasks.length];

        // arranging the named tasks according to `orderedTasks` ints
        // and inserting the appropriate tasks
        for (int i = 0; i < orderedTasks.length; i++) {
            orderedNamedTasks[i] = names[orderedTasks[i]];
        }
        return orderedNamedTasks;
    }

    /**
     * @param task the specified element to search for
     * @return index of task in names array if exists, else returns -1
     */
    private int getIndexOf(String task) {
        for (int i = 0; i < names.length; i++) {
            if (task.equals(names[i])) return i;
        }
        return -1;
    }
}
