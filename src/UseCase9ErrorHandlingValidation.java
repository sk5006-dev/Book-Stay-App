import java.util.Scanner;

/**
 * MAIN CLASS UseCase9ErrorHandlingValidation
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * This class demonstrates how user input
 * is validated before booking is processed.
 * The system:
 * Accepts user input
 * Validates input centrally
 * Handles errors gracefully
 *
 * @author sk5006-dev
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Booking Validation");
        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type: ");
            String roomType = scanner.nextLine();

            validator.validate(guestName, roomType, inventory);

            bookingQueue.addRequest(new Reservation(guestName, roomType));
            System.out.println("Booking request accepted for " + guestName + ".");
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
