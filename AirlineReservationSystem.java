import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationSystem {

   private final Scanner sc = new Scanner(System.in);
   private final Connection conn = DBConnection.getConnection();

    // ==================== START MENU ====================
    public void start() {
        System.out.println("\n===== ✈️ AIRLINE RESERVATION SYSTEM =====");
        while (true) {
            System.out.println("\n1. View Flights");
            System.out.println("2. Add Passenger");
            System.out.println("3. Book Ticket");
            System.out.println("4. Make Payment");
            System.out.println("5. View Bookings");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewFlights();
                case 2 -> addPassenger();
                case 3 -> bookTicket();
                case 4 -> makePayment();
                case 5 -> viewBookings();
                case 6 -> {
                    closeConnection();
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    // ==================== VIEW FLIGHTS ====================
    public void viewFlights() {
        try {
            String query = "SELECT * FROM Flight";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            List<Flight> flights = new ArrayList<>();
            while (rs.next()) {
                Flight f = new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDouble("price")
                );
                flights.add(f);
            }

            System.out.println("\nAvailable Flights:");
            for (Flight f : flights) System.out.println(f);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==================== ADD PASSENGER ====================
    public void addPassenger() {
        try {
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Phone: "); String phone = sc.nextLine();

            String query = "INSERT INTO Passenger (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                Passenger p = new Passenger(rs.getInt(1), name, email, phone);
                System.out.println("✅ Passenger added: " + p);
            }

        } catch (SQLException e) { e.printStackTrace(); }
    }

    // ==================== BOOK TICKET ====================
    public void bookTicket() {
        try {
            System.out.print("Passenger ID: "); int pid = sc.nextInt();
            System.out.print("Flight ID: "); int fid = sc.nextInt();

            String query = "INSERT INTO Booking (passenger_id, flight_id, status) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pid);
            ps.setInt(2, fid);
            ps.setString(3, "Booked");
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                Booking b = new Booking(rs.getInt(1), pid, fid, "Booked");
                System.out.println("✅ Ticket booked: " + b);
            }

        } catch (SQLException e) { e.printStackTrace(); }
    }

    // ==================== MAKE PAYMENT ====================
    public void makePayment() {
        try {
            System.out.print("Booking ID: "); int bid = sc.nextInt();
            System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
            System.out.print("Payment Method: "); String method = sc.nextLine();

            String query = "INSERT INTO Payment (booking_id, amount, payment_method, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, bid);
            ps.setDouble(2, amt);
            ps.setString(3, method);
            ps.setString(4, "Success");
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                Payment payment = new Payment(rs.getInt(1), bid, amt, method, new Date(), "Success");
                System.out.println("✅ Payment successful: " + payment);
            }

        } catch (SQLException e) { e.printStackTrace(); }
    }

    // ==================== VIEW BOOKINGS ====================
    public void viewBookings() {
        try {
            String query = """
                    SELECT b.booking_id, p.name AS passenger, f.source, f.destination, f.price, b.status
                    FROM Booking b
                    JOIN Passenger p ON b.passenger_id = p.passenger_id
                    JOIN Flight f ON b.flight_id = f.flight_id;
                    """;
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            List<Booking> bookings = new ArrayList<>();
            while(rs.next()){
                Booking b = new Booking(rs.getInt("booking_id"), 0, 0, rs.getString("status"));
                bookings.add(b);

                System.out.println("BookingID: " + rs.getInt("booking_id") +
                                   ", Passenger: " + rs.getString("passenger") +
                                   ", From: " + rs.getString("source") +
                                   ", To: " + rs.getString("destination") +
                                   ", Price: " + rs.getDouble("price") +
                                   ", Status: " + rs.getString("status"));
            }

        } catch (SQLException e) { e.printStackTrace(); }
    }

    // ==================== CLOSE CONNECTION ====================
    public void closeConnection() {
        try {
            if(conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
    } 
}