public class LibrarySystem {
    public static class Book {
        private String title;
        private String author;
        private String ISBN;
        private Boolean isReserved;

        Book(String title, String author, String ISBN, Boolean isReserved) {
            this.title = title;
            this.author = author;
            this.ISBN = ISBN;
            this.isReserved = isReserved;
        }

        public String getTitle() {
            return title;
        }

        public boolean isReserved() {
            return isReserved;
        }

        public void setReserved(boolean isReserved) {
            this.isReserved = isReserved;
        }
    }

    interface Notifier {
        void notifyUser(String notification);
    }

    public void reserveBook(Book book, String user) {

        Notifier notifier = new Notifier() {
            @Override
            public void notifyUser(String notification) {
                System.out.println(notification);
            }
        };

        ReservationValidator validator = new ReservationValidator(5, 1, false);

        if (validator.validate()) {
            if (!book.isReserved()) {
                book.setReserved(true);
                notifier.notifyUser("Reservation successful: " + '"' + book.getTitle() + '"');
            } else {
                notifier.notifyUser("Reservation failed: " + '"' + book.getTitle() + '"' + " is already reserved.");
            }
        } else {
            notifier.notifyUser("Reservation failed: User: " + user + " does not meet the reservation criteria.");
        }
    }

    class ReservationValidator {
        private int maxReservations;
        private int userActiveReservations;
        private boolean isInDebt;

        ReservationValidator (int maxReservations, int userActiveReservations, boolean isInDebt){
            this.maxReservations = maxReservations;
            this.userActiveReservations = userActiveReservations;
            this.isInDebt = isInDebt;
        }

        // Method to validate user reservation eligibility
        public boolean validate() {
            if (userActiveReservations < maxReservations && !isInDebt) {
                return true;
            }
            else
                return false;
        }
    }

    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();

        Book book1 = new Book("A perfect book", "Jonas Jonaitis", "123456789", false);
        Book book2 = new Book("Another good book", "Petras petraitis", "987654321", true);

        librarySystem.reserveBook(book1, "Tomas");
        librarySystem.reserveBook(book1, "Tomas");
        librarySystem.reserveBook(book2, "Algis");
    }
}
