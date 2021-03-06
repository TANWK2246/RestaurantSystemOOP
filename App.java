import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;

/**
 * The type App.
 */
public class App{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        Restaurant restaurant = null;
        try {
			FileInputStream fi = new FileInputStream(new File("restaurant.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
            restaurant = (Restaurant) oi.readObject();

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

        System.out.println("Restaurant Management System");

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do{
            try{
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
                System.out.println("13: Terminate the System");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        restaurant.getMenu().displayMenu();
                        break;
                    case 2: 
                        MenuUI.menuEditor(restaurant);
                        break;
                    case 3:
                        restaurant.getTableArray().showEmptyTables();
                        break;
                    case 4: 
                        ReservationManager.updateReservationValidity(restaurant.getReservationArray());
                        CustomerUI.newCustomer(restaurant);
                        break;
                    case 5:
                        OrderUI.orderCreator(restaurant);
                        break;
                    case 6:
                        OrderUI.orderViewer(restaurant);
                        break;
                    case 7:
                        OrderUI.orderEditor(restaurant);
                        break;
                    case 8:
                        ReservationManager.updateReservationValidity(restaurant.getReservationArray());
                        ReservationUI.reservationCreator(restaurant);
                        break;
                    case 9:
                        ReservationManager.updateReservationValidity(restaurant.getReservationArray());
                        ReservationUI.reservationViewer(restaurant);
                        break;
                    case 10:
                        ReservationUI.reservationRemover(restaurant);
                        break;
                    case 11:
                        BillUI.billGenerator(restaurant);
                        break;
                    case 12:
                        ReportUI.reportViewer(restaurant);
                        break;
                    case 13: 
                        System.out.println("System terminating...");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter again!");
                        break;
                }
			}catch (InputMismatchException e){
				System.out.println("Invalid format. Please enter again!");
                sc.nextLine();
			}
        }while (choice != 13);
        
        try {
            FileOutputStream f = new FileOutputStream(new File("restaurant.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(restaurant);

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}

        System.out.println("Saving restaurant data....");
    }
}