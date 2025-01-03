import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ResultSystem {
    public static class Player {
        private String name;
        private int age;
        private String team;
        private int points;

        Player (String name, int age, String team, int points){
            this.name = name;
            this.age = age;
            this.team = team;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getTeam() {
            return team;
        }

        public int getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", age: " + age + ", team: " + team + ", points: " + points;
        }
    }

//    public static class PlayerComparator implements Comparator<Player> {
//        @Override
//        public int compare(Player p1, Player p2) {
//            return Integer.compare(p2.getPoints(), p1.getPoints());
//        }
//    }

    static Comparator<Player> pointSort = new Comparator<Player>(){
        @Override
        public int compare(Player p1, Player p2) {
            if (p2.getPoints() == p1.getPoints())
                return 0;
            else if (p2.getPoints() > p1.getPoints())
                return 1;
            else
                return -1;
        }
    };

    public static ArrayList<Player> filterPlayers(ArrayList<Player> players, char filterType, Object filterValue) {
        class PlayerFilter {
            ArrayList<Player> filterByAge(ArrayList<Player> players, int ageThreshold) {
                ArrayList<Player> filteredPlayers = new ArrayList<>();
                for (Player player : players) {
                    if (player.getAge() > ageThreshold) {
                        filteredPlayers.add(player);
                    }
                }
                return filteredPlayers;
            }

            ArrayList<Player> filterByTeam(ArrayList<Player> players, String team) {
                ArrayList<Player> filteredPlayers = new ArrayList<>();
                for (Player player : players) {
                    if (player.getTeam().equalsIgnoreCase(team)) {
                        filteredPlayers.add(player);
                    }
                }
                return filteredPlayers;
            }
        }

        PlayerFilter filter = new PlayerFilter();
        if (filterType == 'A') {
            return filter.filterByAge(players, (int) filterValue);
        } else if (filterType == 'T') {
            return filter.filterByTeam(players, (String) filterValue);
        }
        return players;
    }

    public static void main(String[] args) {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Jonas", 25, "A", 20));
        players.add(new Player("Petras", 22, "B", 15));
        players.add(new Player("Tomas", 17, "B", 47));
        players.add(new Player("Martynas", 30, "A", 30));

//        PlayerComparator comp1 = new PlayerComparator();
//        players.sort(comp1);

        Collections.sort(players, pointSort);

        System.out.println("Players sorted by descending points: ");
        for (Player player : players) {
            System.out.println(player);
        }

        ArrayList<Player> filteredByAge = filterPlayers(players, 'A', 18);
        System.out.println("\nPlayers older than 18:");
        for (Player player : filteredByAge) {
            System.out.println(player);
        }

        ArrayList<Player> filteredByTeam = filterPlayers(players, 'T', "A");
        System.out.println("\nPlayers in team A:");
        for (Player player : filteredByTeam) {
            System.out.println(player);
        }
    }
}
