public class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode,
                         double finesOwed, String displayName) {

        if (membershipId == null ||
            membershipId.trim().isEmpty() ||
            membershipId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid membership ID");
        }

        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static String summarizeByModifier(
            String[][] attempts) {

        String[] modifiers = {
                "private",
                "default",
                "protected",
                "public"
        };

        String result = "";

        for (int m = 0; m < modifiers.length; m++) {

            int allowed = 0;
            int denied = 0;

            for (int i = 0; i < attempts.length; i++) {

                if (attempts[i][0].equals(modifiers[m])) {

                    String answer = classifyAccess(
                            attempts[i][0],
                            attempts[i][1]);

                    if (answer.equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            if (allowed + denied > 0) {

                if (!result.isEmpty()) {
                    result += " | ";
                }

                result += modifiers[m] + ": "
                        + allowed + " allowed / "
                        + denied + " denied";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess("private", "SAME_CLASS"));

        System.out.println(
                classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                summarizeByModifier(attempts));

        try {
            new LibraryMember(
                    "LB9",
                    "BR1",
                    0,
                    "Priya Nair");

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }
    }
}