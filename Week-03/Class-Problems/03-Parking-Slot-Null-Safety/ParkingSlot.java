public class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {

        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo
                    + " allotted to slot " + slotNo);
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (int i = 0; i < slots.length; i++) {

            if (slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }

        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {

        ParkingSlot availableSlot =
                findAvailableSlot(slots);

        if (availableSlot == null) {

            System.out.println("No slots available for "
                    + vehicleNo);

        } else {

            availableSlot.allot(vehicleNo);
        }
    }

    /*
     * Passing the ParkingSlot array does not copy the slots.
     * The array contains references to the same ParkingSlot objects,
     * so changes made through those references affect the original objects.
     */
    public static void main(String[] args) {

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(slots, "TN09AB1234");

        ParkingSlot[] fullSlots = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(fullSlots, "TN09AB1234");
    }
}