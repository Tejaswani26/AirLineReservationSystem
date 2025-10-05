public class Booking {
    public int bookingId;
    public int passengerId;
    public int flightId;
    public String status;

    public Booking(int bookingId, int passengerId, int flightId, String status) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.status = status;
    }

    public int getBookingId() { return bookingId; }
    public int getPassengerId() { return passengerId; }
    public int getFlightId() { return flightId; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Booking [ID=" + bookingId + ", Passenger=" + passengerId + ", Flight=" + flightId + ", Status=" + status + "]";
    }
}

