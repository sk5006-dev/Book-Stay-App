/**
 * CLASS ReservationValidator
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * This class is responsible for validating
 * booking requests before they are processed.
 * All validation rules are centralized
 * to avoid duplication and inconsistency.
 *
 * @author sk5006-dev
 * @version 9.0
 */
public class ReservationValidator {
    /**
     * Validates booking input provided by the user.
     *
     * @param guestName name of the guest
     * @param roomType requested room type
     * @param inventory centralized inventory
     * @throws InvalidBookingException if validation fails
     */
    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        String normalizedRoomType = normalizeRoomType(roomType);
        if (normalizedRoomType == null) {
            throw new InvalidBookingException("Unsupported room type: " + roomType);
        }

        Integer availableCount = inventory.getRoomAvailability().get(normalizedRoomType);
        if (availableCount == null) {
            throw new InvalidBookingException("Room type is not configured in inventory: " + normalizedRoomType);
        }

        if (availableCount <= 0) {
            throw new InvalidBookingException("No rooms available for " + normalizedRoomType + ".");
        }
    }

    private String normalizeRoomType(String roomType) {
        if (roomType == null) {
            return null;
        }

        String normalized = roomType.trim();
        if ("Single".equalsIgnoreCase(normalized) || "Single Room".equalsIgnoreCase(normalized)) {
            return "Single Room";
        }
        if ("Double".equalsIgnoreCase(normalized) || "Double Room".equalsIgnoreCase(normalized)) {
            return "Double Room";
        }
        if ("Suite".equalsIgnoreCase(normalized) || "Suite Room".equalsIgnoreCase(normalized)) {
            return "Suite Room";
        }
        return null;
    }
}
