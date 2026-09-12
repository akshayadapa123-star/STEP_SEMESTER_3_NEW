public class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {

        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {

        this(routeCode, routeName, 2);
    }

    public int compareTo(BusRoute other) {

        // Higher priority comes first
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        // Route code comparison without changing stored code
        int codeComparison =
                this.routeCode.compareToIgnoreCase(other.routeCode);

        if (codeComparison != 0) {
            return codeComparison;
        }

        // Shorter route name comes first
        if (this.routeName.length() != other.routeName.length()) {
            return this.routeName.length() - other.routeName.length();
        }

        // Return 0 when all ranking rules are equal
        return 0;
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] result = new BusRoute[routes.length];

        for (int i = 0; i < routes.length; i++) {
            result[i] = routes[i];
        }

        // Stable insertion sort
        for (int i = 1; i < result.length; i++) {

            BusRoute current = result[i];
            int j = i - 1;

            while (j >= 0 && result[j].compareTo(current) > 0) {

                result[j + 1] = result[j];
                j--;
            }

            result[j + 1] = current;
        }

        return result;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] rankedRoutes = rankRoutes(routes);

        System.out.println("Ranked Routes:");

        for (BusRoute route : rankedRoutes) {
            System.out.println(route.routeCode);
        }
    }
}