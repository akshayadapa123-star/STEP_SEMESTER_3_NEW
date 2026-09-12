public class LibraryMember {

    /*
     * BROKEN VERSION
     *
     * These fields are static, so all LibraryMember objects
     * share the same copy of the fields.
     *
     * name is wrong as static because every member has a
     * different name.
     *
     * memberId is wrong as static because every member has
     * a different member ID.
     *
     * booksIssued is wrong as static because each member
     * can have a different number of issued books.
     */
    static class BrokenLibraryMember {

        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId,
                            int booksIssued) {

            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }

        void printName() {
            System.out.println(name);
        }
    }

    /*
     * FIXED VERSION
     *
     * name, memberId and booksIssued are instance fields
     * because they belong to individual members.
     *
     * libraryName and memberCount are static because they
     * are shared by the whole library.
     */
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "SRM Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {

        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {

        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember(
                        "Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember(
                        "Rohan", "LM-1002", 3);

        member1.printName();
        member2.printName();

        /*
         * Both print Rohan because the second object overwrote
         * the shared static name field.
         */

        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember fixedMember1 =
                new LibraryMember("Aditi", 2);

        LibraryMember fixedMember2 =
                new LibraryMember("Rohan", 3);

        fixedMember1.printMemberCard();
        fixedMember2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}