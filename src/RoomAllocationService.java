import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * CLASS RoomAllocationService
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class is responsible for confirming
 * booking requests and assigning rooms.
 * It ensures:
 * Each room ID is unique
 * Inventory is updated immediately
 * No room is double-booked
 *
 * @author sk5006-dev
 * @version 6.0
 */
public class RoomAllocationService {
    /**
     * Stores all allocated room IDs to
     * prevent duplicate assignments.
     */
    private Set<String> allocatedRoomIds;

    /**
     * Stores assigned room IDs by room type.
     *
     * Key -> Room type
     * Value -> Set of assigned room IDs
     */
    private Map<String, Set<String>> assignedRoomsByType;

    /** Initializes allocation tracking structures. */
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    /**
     * Confirms a booking request by assigning
     * a unique room ID and updating inventory.
     *
     * @param reservation booking request
     * @param inventory centralized room inventory
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String normalizedRoomType = normalizeRoomType(reservation.getRoomType());
        Integer availableCount = inventory.getRoomAvailability().get(normalizedRoomType);

        if (availableCount == null || availableCount <= 0) {
            System.out.println("Reservation failed for " + reservation.getGuestName()
                    + ": no " + normalizedRoomType + " available.");
            return;
        }

        String roomId = generateRoomId(normalizedRoomType);
        allocatedRoomIds.add(roomId);
        assignedRoomsByType
                .computeIfAbsent(normalizedRoomType, key -> new HashSet<>())
                .add(roomId);

        inventory.updateAvailability(normalizedRoomType, availableCount - 1);

        System.out.println("Reservation confirmed for " + reservation.getGuestName()
                + ". Assigned room: " + roomId);
    }

    /**
     * Generates a unique room ID
     * for the given room type.
     *
     * @param roomType type of room
     * @return unique room ID
     */
    private String generateRoomId(String roomType) {
        String prefix = roomType.substring(0, 3).toUpperCase().replace(" ", "");
        int sequence = assignedRoomsByType.getOrDefault(roomType, new HashSet<>()).size() + 1;
        String roomId = prefix + "-" + sequence;

        while (allocatedRoomIds.contains(roomId)) {
            sequence++;
            roomId = prefix + "-" + sequence;
        }

        return roomId;
    }

    private String normalizeRoomType(String roomType) {
        if ("Single".equalsIgnoreCase(roomType)) {
            return "Single Room";
        }
        if ("Double".equalsIgnoreCase(roomType)) {
            return "Double Room";
        }
        if ("Suite".equalsIgnoreCase(roomType)) {
            return "Suite Room";
        }
        return roomType;
    }
}
