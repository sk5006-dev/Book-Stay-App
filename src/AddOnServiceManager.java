import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CLASS AddOnServiceManager
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class manages optional services
 * associated with confirmed reservations.
 * It supports attaching multiple services
 * to a single reservation.
 *
 * @author sk5006-dev
 * @version 7.0
 */
public class AddOnServiceManager {
    /**
     * Maps reservation ID to selected services.
     *
     * Key -> Reservation ID
     * Value -> List of selected services
     */
    private Map<String, List<AddOnService>> servicesByReservation;

    /** Initializes the service manager. */
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    /**
     * Attaches a service to a reservation.
     *
     * @param reservationId confirmed reservation ID
     * @param service add-on service
     */
    public void addService(String reservationId, AddOnService service) {
        servicesByReservation
                .computeIfAbsent(reservationId, key -> new ArrayList<>())
                .add(service);
    }

    /**
     * Calculates total add-on cost
     * for a reservation.
     *
     * @param reservationId reservation ID
     * @return total service cost
     */
    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = servicesByReservation.getOrDefault(reservationId, new ArrayList<>());
        double totalCost = 0.0;

        for (AddOnService service : services) {
            totalCost += service.getCost();
        }

        return totalCost;
    }

    /**
     * Returns services attached to a reservation.
     *
     * @param reservationId reservation ID
     * @return selected services
     */
    public List<AddOnService> getServicesForReservation(String reservationId) {
        return servicesByReservation.getOrDefault(reservationId, new ArrayList<>());
    }
}
