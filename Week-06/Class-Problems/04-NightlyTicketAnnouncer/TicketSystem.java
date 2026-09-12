class EventTicket {
    void printTicket() {
        System.out.println("Event Ticket");
    }
}

class WorkshopTicket extends EventTicket {
    String track = "AI";

    void printTicket() {
        System.out.println("Workshop Ticket");
    }
}

public class TicketSystem {

    static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (EventTicket t : tickets) {
            t.printTicket();

            if (t instanceof WorkshopTicket) {
                WorkshopTicket w = (WorkshopTicket) t;
                sb.append(w.track).append("\n");
            }
        }

        return sb.toString();
    }
}