import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClassifierSystem {
    public static class Task {
        String name;
        String description;
        int difficulty;

        Task (String name, String description, int difficulty){
            this.name = name;
            this.description = description;
            this.difficulty = difficulty;
        }

        public int getDifficulty() {
            return difficulty;
        }

        @Override
        public String toString() {
            return ("NAME: " + "|" + name +  "|" +" DESCRIPTION: " + "|" + description + "|" + " DIFFICULTY: " + "|" + difficulty + "|");
        }
    }

    public static void assignPriority (ArrayList<Task> tasks, String priorityType){
        class PriorityAssigner {
            public void difficultyDescending (ArrayList<Task> tasks){
                Collections.sort(tasks, difficultyCompare);
            }
            public void difficultyAscending (ArrayList<Task> tasks){
                Collections.sort(tasks, difficultyCompare.reversed());
            }
        }

        PriorityAssigner priority = new PriorityAssigner();
        if (priorityType.equalsIgnoreCase("Ascending")) {
            System.out.println("\nTasks in ASCENDING order by difficulty: ");
            priority.difficultyAscending(tasks);
        } else if (priorityType.equalsIgnoreCase("Descending")) {
            System.out.println("\nTasks in DESCENDING order by difficulty: ");
            priority.difficultyDescending(tasks);
        }
        else {
            System.out.println("Invalid priority type");
        }
    }

    static Comparator<Task> difficultyCompare = new Comparator<Task>(){
        @Override
        public int compare(Task t1, Task t2) {
            if (t1.getDifficulty() == t2.getDifficulty())
                return 0;
            else if (t2.getDifficulty() > t1.getDifficulty())
                return 1;
            else
                return -1;
        } // can be used as: return Integer.compare(t2.getDifficulty(), t1.getDifficulty());
    };

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Anonimines klases", "Uzduotys su anoniminemis klasemis", 3));
        tasks.add(new Task("Nestes klases", "Uzduotys su nested klasemis", 1));
        tasks.add(new Task("Lokalios klases", "Uzduotys su lokaliomis klasemis", 2));

        assignPriority(tasks, "ASCENDING");
        for (Task task : tasks) {
            System.out.println(task);
        }

        assignPriority(tasks, "descending");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
