import java.util.Date;

public class Payment {
    public int paymentId;
    public int bookingId;
    public double amount;
    public String paymentMethod;
    public Date paymentDate;
    public String status;

    public Payment(int paymentId, int bookingId, double amount, String paymentMethod, Date paymentDate, String status) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getPaymentId() { return paymentId; }
    public int getBookingId() { return bookingId; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public Date getPaymentDate() { return paymentDate; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Payment [ID=" + paymentId + ", BookingID=" + bookingId + ", Amount=" + amount +
               ", Method=" + paymentMethod + ", Date=" + paymentDate + ", Status=" + status + "]";
    }
}
