public class Flight {
    public int flightId;
    public String source;
    public String destination;
    public double price;

    public Flight(int flightId, String source, String destination, double price) {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    public int getFlightId() { return flightId; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Flight [ID=" + flightId + ", From=" + source + ", To=" + destination + ", Price=" + price + "]";
    }
}