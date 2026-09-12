public final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        if (bookIds == null) {
            throw new IllegalArgumentException(
                    "Book IDs cannot be null");
        }

        for (String bookId : bookIds) {

            if (!isValidBookId(bookId)) {
                throw new IllegalArgumentException(
                        "Invalid book ID");
            }
        }

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = bookIds.clone();
    }

    private static boolean isValidBookId(
            String bookId) {

        if (bookId == null ||
            bookId.length() != 6) {

            return false;
        }

        if (!bookId.startsWith("BK-")) {
            return false;
        }

        for (int i = 3; i < 6; i++) {

            if (!Character.isDigit(bookId.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public String getMemberId() {

        return memberId;
    }

    public String[] getBookIds() {

        // Defensive copy
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException(
                    "Invalid book ID index");
        }

        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException(
                    "Invalid book ID");
        }

        String[] correctedIds = bookIds.clone();

        correctedIds[index] = newId;

        return new LoanReceipt(
                memberId,
                correctedIds);
    }
}