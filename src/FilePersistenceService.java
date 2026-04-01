import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * CLASS FilePersistenceService
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class is responsible for persisting
 * critical system state to a plain text file.
 * It supports:
 * Saving room inventory state
 * Restoring inventory on system startup
 * No database or serialization framework
 * is used in this use case.
 *
 * @author sk5006-dev
 * @version 12.0
 */
public class FilePersistenceService {
    /**
     * Saves room inventory state to a file.
     *
     * Each line follows the format:
     * roomType=availableCount
     *
     * @param inventory centralized room inventory
     * @param filePath path to persistence file
     */
    public void saveInventory(RoomInventory inventory, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getRoomAvailability().entrySet()) {
                writer.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("Inventory saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save inventory: " + e.getMessage());
        }
    }

    /**
     * Loads room inventory state from a file.
     *
     * @param inventory centralized room inventory
     * @param filePath path to persistence file
     */
    public void loadInventory(RoomInventory inventory, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length != 2) {
                    System.out.println("Skipping corrupted inventory line: " + line);
                    continue;
                }

                try {
                    inventory.updateAvailability(parts[0], Integer.parseInt(parts[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid inventory count for line: " + line);
                }
            }
            System.out.println("Inventory restored from " + filePath);
        } catch (IOException e) {
            System.out.println("Persistence file unavailable. Starting with default inventory.");
        }
    }
}
