/**
 * MAIN CLASS UseCase10BookingCancellation
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class demonstrates how confirmed
 * bookings can be cancelled safely.
 * Inventory is restored and rollback
 * history is maintained.
 *
 * @author sk5006-dev
 * @version 10.0
 */
public class UseCase10BookingCancellation {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Booking Cancellation & Inventory Rollback");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        inventory.updateAvailability("Single Room", 4);
        inventory.updateAvailability("Double Room", 2);
        inventory.updateAvailability("Suite Room", 0);

        cancellationService.registerBooking("SIN-1", "Single Room");
        cancellationService.registerBooking("DOU-1", "Double Room");
        cancellationService.registerBooking("SUI-1", "Suite Room");

        cancellationService.cancelBooking("SUI-1", inventory);
        cancellationService.cancelBooking("DOU-1", inventory);
        cancellationService.cancelBooking("SUI-1", inventory);

        System.out.println("Updated Inventory");
        System.out.println("Single Room: " + inventory.getRoomAvailability().get("Single Room"));
        System.out.println("Double Room: " + inventory.getRoomAvailability().get("Double Room"));
        System.out.println("Suite Room: " + inventory.getRoomAvailability().get("Suite Room"));

        cancellationService.showRollbackHistory();
    }
}
