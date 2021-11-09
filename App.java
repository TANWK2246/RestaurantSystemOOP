import java.util.Scanner;

public class App{
    public static void main(String[] args)
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();

        do{
            System.out.println("Perform the following methods:");
            System.out.println("1: Display Menu");
            System.out.println("2: Edit Menu");
            System.out.println("3: Display Empty Tables");
            System.out.println("4: Assign New Customer");
            System.out.println("5: Create Order");
            System.out.println("6: View Order");
            System.out.println("7: Edit Order");
            System.out.println("8: Create Reservation Booking");
            System.out.println("9: Check Reservation Booking");
            System.out.println("10: Remove Reservation Booking");
            System.out.println("11: Make Payment / Print Order Invoice");
            System.out.println("12: Print Sales Revenue Report");
            System.out.println("13: Terminate the Programme");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    restaurant.getMenu().displayMenu();
                    break;
                case 2: 
                    restaurant.getMenu().editMenu();
                    break;
                case 3:
                    restaurant.getTableManager().showEmptyTables();
                    break;
                case 4: 
                    restaurant.getReservationManager().updateReservationValidity(restaurant.getTableManager());
                    restaurant.getCustomerManager().assignNewCustomerToTable(restaurant.getTableManager());
                    break;
                case 5:
                    restaurant.getOrderManager().createOrder(restaurant.getTableManager(), restaurant.getStaffManager(), restaurant.getMenu());
                    break;
                case 6:
                    restaurant.getOrderManager().viewOrder(restaurant.getTableManager());
                    break;
                case 7:
                    restaurant.getOrderManager().editOrder(restaurant.getTableManager(), restaurant.getMenu());
                    break;
                case 8:
                    restaurant.getReservationManager().createReservation(restaurant.getTableManager(), restaurant.getCustomerManager());
                    break;
                case 9:
                    restaurant.getReservationManager().updateReservationValidity(restaurant.getTableManager());
                    restaurant.getReservationManager().checkReservation();
                    break;
                case 10:
                    restaurant.getReservationManager().removeReservation(restaurant.getTableManager());
                    break;
                case 11:
                    restaurant.getBillManager().generateBill(restaurant.getTableManager());
                    break;
                case 12:
                    break;

                case 13: System.out.println("Program terminating...");
            }

        }while (choice < 12);

    }
}