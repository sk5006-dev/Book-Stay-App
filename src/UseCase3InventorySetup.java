import java.util.Map;

/**
 * MAIN CLASS UseCase3InventorySetup
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This class demonstrates how room availability
 * is managed using a centralized inventory.
 * Room objects are used to retrieve pricing
 * and room characteristics.
 * No booking or search logic is introduced here.
 *
 * @author sk5006-dev
 * @version 3.0
 */
public class UseCase3InventorySetup {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        System.out.println("Centralized Room Inventory");
        displayRoomInventory("Single Room", singleRoom, inventory.getRoomAvailability());
        displayRoomInventory("Double Room", doubleRoom, inventory.getRoomAvailability());
        displayRoomInventory("Suite Room", suiteRoom, inventory.getRoomAvailability());

        inventory.updateAvailability("Suite Room", 1);

        System.out.println("Updated Inventory Snapshot");
        displayAvailabilityOnly("Single Room", inventory.getRoomAvailability());
        displayAvailabilityOnly("Double Room", inventory.getRoomAvailability());
        displayAvailabilityOnly("Suite Room", inventory.getRoomAvailability());
    }

    private static void displayRoomInventory(String roomType, Room room, Map<String, Integer> availability) {
        System.out.println(roomType + ":");
        room.displayRoomDetails();
        System.out.println("Available: " + availability.get(roomType));
    }

    private static void displayAvailabilityOnly(String roomType, Map<String, Integer> availability) {
        System.out.println(roomType + " Available: " + availability.get(roomType));
    }
}
