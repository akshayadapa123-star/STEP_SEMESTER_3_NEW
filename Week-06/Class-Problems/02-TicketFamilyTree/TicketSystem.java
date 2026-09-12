class EventTicket {
    protected double price;

    EventTicket(double price) {
        this.price = price;
    }

    double getBalanceDue() {
        return price;
    }

    void printTicket() {
        System.out.println("Event Ticket");
    }
}

class WorkshopTicket extends EventTicket {
    WorkshopTicket(double price) {
        super(price);
    }

    void printTicket() {
        System.out.println("Workshop Ticket");
    }
}

class PremiumWorkshopTicket extends WorkshopTicket {
    PremiumWorkshopTicket(double price) {
        super(price);
    }

    void printTicket() {
        System.out.println("Premium Workshop Ticket");
    }
}

class HackathonTicket extends EventTicket {
    HackathonTicket(double price) {
        super(price);
    }

    void printTicket() {
        System.out.println("Hackathon Ticket");
    }
}

public class TicketSystem {

    static String classifyGeneration(EventTicket t) {
        if (t instanceof PremiumWorkshopTicket)
            return "Premium";

        if (t instanceof WorkshopTicket)
            return "Workshop";

        return "Base";
    }

    public static void main(String[] args) {
        EventTicket[] tickets = {
            new EventTicket(100),
            new WorkshopTicket(200),
            new PremiumWorkshopTicket(300),
            new HackathonTicket(400)
        };

        for (EventTicket t : tickets) {
            t.printTicket();
            System.out.println(t.getBalanceDue());
        }
    }
}