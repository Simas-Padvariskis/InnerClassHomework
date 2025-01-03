import java.util.ArrayList;
import java.util.Scanner;

public class ElectricitySystem {
    public static class Appliance {
        private String name;
        private double power;
        private double consumption;

        Appliance (String name, double power){
            this.name = name;
            this.power = power; //Watts
            this.consumption = power / 1000; //kWh
        }
    }

    public static void CalculateConsumption (ArrayList<Appliance> appliances, String limit) {
        class ConsumptionCalculator {
            public double calculate (ArrayList<Appliance> appliances, String limit){
                if (limit.equalsIgnoreCase("weekly")){
                    double summ = 0.0;
                    for (Appliance appliance : appliances){
                        summ += (appliance.consumption * 168); //1 week has 168 hours
                    }
                    return summ;
                }
                else {
                    double summ = 0.0;
                    for (Appliance appliance : appliances){
                        summ += (appliance.consumption * 720); //1 month has ~ 720 hours
                    }
                    return summ;
                }
            }
        }
        ConsumptionCalculator calculator = new ConsumptionCalculator();
        double totalConsumption = calculator.calculate(appliances, limit);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Total " + (limit.toUpperCase()) + " consumption is " + totalConsumption + " kWh");
        System.out.println("-----------------------------------------------------------------");
    }

    public static void main(String[] args) {

        ArrayList<Appliance> appliances = new ArrayList<>();

        Runnable DataProcessor = new Runnable() {
            Scanner scanner = new Scanner(System.in);
            @Override
            public void run() {
                int choice = 0;
                while (choice != 3){
                    System.out.println("|  Add and appliance - 1                     |");
                    System.out.println("|  Set limit and calculate consumption - 2   |");
                    System.out.println("|  Exit - 3                                  |\n");
                    System.out.println("Type in your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 1){
                        System.out.println("Type in the appliance name: ");
                        String name = scanner.nextLine();
                        System.out.println("Type in the power (Watts): ");
                        double power = scanner.nextDouble();
                        scanner.nextLine();

                        appliances.add(new Appliance(name, power));
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("New appliance has been added!");
                        System.out.println("-----------------------------------------------------------------");
                    }
                    else if (choice == 2){
                        System.out.println("Type in the limit (weekly / monthly): ");
                        String limit = scanner.nextLine();
                        if (limit.equalsIgnoreCase("weekly") || limit.equalsIgnoreCase("monthly"))
                            CalculateConsumption(appliances, limit);
                        else
                            System.out.println("\nInvalid limit\n");
                    }
                    else if (choice == 3){
                        System.out.println("\nExit");
                    }
                    else {
                        System.out.println("\nInvalid choice, try again\n");
                    }
                }
                scanner.close();
            }
        };

        DataProcessor.run();

    }
}
