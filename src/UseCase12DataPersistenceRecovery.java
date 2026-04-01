/**
 * MAIN CLASS UseCase12DataPersistenceRecovery
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class demonstrates how system state
 * can be restored after an application restart.
 * Inventory data is loaded from a file
 * before any booking operations occur.
 *
 * @author sk5006-dev
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {
    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String filePath = "inventory-state.txt";
        FilePersistenceService persistenceService = new FilePersistenceService();

        RoomInventory inventoryBeforeShutdown = new RoomInventory();
        inventoryBeforeShutdown.updateAvailability("Single Room", 4);
        inventoryBeforeShutdown.updateAvailability("Double Room", 1);
        inventoryBeforeShutdown.updateAvailability("Suite Room", 0);

        persistenceService.saveInventory(inventoryBeforeShutdown, filePath);

        RoomInventory recoveredInventory = new RoomInventory();
        persistenceService.loadInventory(recoveredInventory, filePath);

        System.out.println("Recovered Inventory");
        System.out.println("Single Room: " + recoveredInventory.getRoomAvailability().get("Single Room"));
        System.out.println("Double Room: " + recoveredInventory.getRoomAvailability().get("Double Room"));
        System.out.println("Suite Room: " + recoveredInventory.getRoomAvailability().get("Suite Room"));
    }
}
