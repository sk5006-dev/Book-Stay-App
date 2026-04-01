import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * CLASS CancellationService
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class is responsible for handling
 * booking cancellations.
 * It ensures that:
 * Cancelled room IDs are tracked
 * Inventory is restored correctly
 * Invalid cancellations are prevented
 * A stack is used to model rollback behavior.
 *
 * @author sk5006-dev
 * @version 10.0
 */
public class CancellationService {
    /** Stack that stores recently released room IDs. */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID to room type. */
    private Map<String, String> reservationRoomTypeMap;

    /** Initializes cancellation tracking structures. */
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking.
     *
     * This method simulates storing confirmation
     * data that will later be required for cancellation.
     *
     * @param reservationId confirmed reservation ID
     * @param roomType allocated room type
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels a confirmed booking and
     * restores inventory safely.
     *
     * @param reservationId reservation to cancel
     * @param inventory centralized room inventory
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Cancellation failed: reservation " + reservationId + " does not exist.");
            return;
        }

        String roomType = reservationRoomTypeMap.remove(reservationId);
        Integer currentCount = inventory.getRoomAvailability().get(roomType);

        if (currentCount == null) {
            System.out.println("Cancellation failed: inventory is not configured for " + roomType + ".");
            return;
        }

        inventory.updateAvailability(roomType, currentCount + 1);
        releasedRoomIds.push(reservationId);

        System.out.println("Reservation cancelled: " + reservationId);
        System.out.println("Inventory restored for " + roomType + ".");
    }

    /**
     * Displays recently cancelled reservations.
     *
     * This method helps visualize rollback order.
     */
    public void showRollbackHistory() {
        System.out.println("Rollback History");
        while (!releasedRoomIds.isEmpty()) {
            System.out.println(releasedRoomIds.pop());
        }
    }
}
