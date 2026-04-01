import java.util.Map;

/**
 * CLASS RoomSearchService
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This class provides search functionality
 * for guests to view available rooms.
 * It reads room availability from inventory
 * and room details from Room objects.
 * No inventory mutation or booking logic
 * is performed in this class.
 *
 * @author sk5006-dev
 * @version 4.0
 */
public class RoomSearchService {
    /**
     * Displays available rooms along with
     * their details and pricing.
     *
     * This method performs read-only access
     * to inventory and room data.
     *
     * @param inventory centralized room inventory
     * @param singleRoom single room definition
     * @param doubleRoom double room definition
     * @param suiteRoom suite room definition
     */
    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single Room") > 0) {
            displayRoom("Single Room", singleRoom, availability.get("Single Room"));
        }

        if (availability.get("Double Room") > 0) {
            displayRoom("Double Room", doubleRoom, availability.get("Double Room"));
        }

        if (availability.get("Suite Room") > 0) {
            displayRoom("Suite Room", suiteRoom, availability.get("Suite Room"));
        }
    }

    private void displayRoom(String roomType, Room room, int availableCount) {
        System.out.println(roomType + ":");
        room.displayRoomDetails();
        System.out.println("Available: " + availableCount);
    }
}
