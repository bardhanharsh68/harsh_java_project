import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    String vehicleNumber;
    String vehicleType;

    Vehicle(String vehicleNumber, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }
}

public class ParkingLotManagement {

    static ArrayList<Vehicle> parkingLot = new ArrayList<>();

    // Check if vehicle type is valid
    public static boolean isValidVehicleType(String type) {
        return type.equalsIgnoreCase("Bike")
                || type.equalsIgnoreCase("Car")
                || type.equalsIgnoreCase("Bus")
                || type.equalsIgnoreCase("Truck");
    }

    // Check duplicate vehicle number
    public static boolean vehicleExists(String number) {
        for (Vehicle v : parkingLot) {
            if (v.vehicleNumber.equalsIgnoreCase(number)) {
                return true;
            }
        }
        return false;
    }

    public static void parkVehicle(String number, String type) {

        if (vehicleExists(number)) {
            System.out.println("\n!! Vehicle Number Already Exists!\n");
            return;
        }

        Vehicle vehicle = new Vehicle(
                number.toUpperCase(),
                type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase());

        parkingLot.add(vehicle);

        System.out.println("\n>> Vehicle Parked Successfully!");
        System.out.println("Total Parked Vehicles: " + parkingLot.size() + "\n");
    }

    public static void searchVehicle(String number) {

        for (Vehicle v : parkingLot) {
            if (v.vehicleNumber.equalsIgnoreCase(number)) {

                System.out.println("\n========== Vehicle Found ==========");
                System.out.println("Vehicle Number : " + v.vehicleNumber);
                System.out.println("Vehicle Type   : " + v.vehicleType);
                System.out.println("===================================\n");
                return;
            }
        }

        System.out.println("\n!! Vehicle Not Found!\n");
    }

    public static void removeVehicle(String number) {

        for (int i = 0; i < parkingLot.size(); i++) {

            Vehicle v = parkingLot.get(i);

            if (v.vehicleNumber.equalsIgnoreCase(number)) {

                parkingLot.remove(i);

                System.out.println("\n>> Vehicle Removed Successfully!");
                System.out.println("Remaining Vehicles: " + parkingLot.size() + "\n");
                return;
            }
        }

        System.out.println("\n!! Vehicle Not Found!\n");
    }

    public static void displayStatus() {

        if (parkingLot.isEmpty()) {
            System.out.println("\n-- Parking Lot is Empty --\n");
            return;
        }

        System.out.println("\n========== PARKING STATUS ==========");
        System.out.println("Total Vehicles Parked: " + parkingLot.size());
        System.out.println("------------------------------------");

        for (Vehicle v : parkingLot) {
            System.out.println("Number: " + v.vehicleNumber
                    + " | Type: " + v.vehicleType);
        }

        System.out.println("====================================\n");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("========================================");
            System.out.println("     PARKING LOT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Search Vehicle");
            System.out.println("3. Remove Vehicle");
            System.out.println("4. Display Status");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice;

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\n!! Please Enter a Valid Number.\n");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Vehicle Number: ");
                    String number = sc.nextLine().trim();

                    System.out.print("Enter Vehicle Type (Bike/Car/Bus/Truck): ");
                    String type = sc.nextLine().trim();

                    if (number.isEmpty() || type.isEmpty()) {
                        System.out.println("\n!! Fields Cannot Be Empty.\n");
                    }
                    else if (!isValidVehicleType(type)) {
                        System.out.println("\n!! Only Bike, Car, Bus and Truck are Allowed.\n");
                    }
                    else {
                        parkVehicle(number, type);
                    }

                    break;

                case 2:

                    System.out.print("Enter Vehicle Number: ");
                    number = sc.nextLine().trim();

                    if (number.isEmpty()) {
                        System.out.println("\n!! Enter a Valid Vehicle Number.\n");
                    } else {
                        searchVehicle(number);
                    }

                    break;

                case 3:

                    System.out.print("Enter Vehicle Number: ");
                    number = sc.nextLine().trim();

                    if (number.isEmpty()) {
                        System.out.println("\n!! Enter a Valid Vehicle Number.\n");
                    } else {
                        removeVehicle(number);
                    }

                    break;

                case 4:
                    displayStatus();
                    break;

                case 5:
                    System.out.println("\nThank You For Using Parking Lot Management System!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\n!! Invalid Choice. Please Try Again.\n");
            }
        }
    }
}