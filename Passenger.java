public class Passenger {
    public int passengerId;
    public String name;
    public String email;
    public String phone;

    public Passenger(int passengerId, String name, String email, String phone) {
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getPassengerId() { return passengerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "Passenger [ID=" + passengerId + ", Name=" + name + ", Email=" + email + ", Phone=" + phone + "]";
    }
}