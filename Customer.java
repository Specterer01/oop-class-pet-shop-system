/**
 * Customer represents a shopper registered with the pet shop.
 * Like Pet, every attribute is private and reached only via getters/setters.
 */
public class Customer {

    // ---------- Attributes (all private) ----------
    private String customerId;      // e.g. "C001"
    private String name;            // customer's full name
    private String phone;           // contact number
    private String membership;      // Standard, Silver or Gold
    private int loyaltyPoints;      // points collected from purchases
    private double totalSpent;      // money spent in the shop so far (RM)
    private boolean active;         // is the account still active?

    // ---------- Constructors ----------

    /** Default constructor: new walk-in customer with a Standard membership. */
    public Customer() {
        this.customerId = "C000";
        this.name = "Walk-in";
        this.phone = "N/A";
        this.membership = "Standard";
        this.loyaltyPoints = 0;
        this.totalSpent = 0.0;
        this.active = true;
    }

    /** Parameterized constructor: reuses the setters for validation. */
    public Customer(String customerId, String name, String phone, String membership,
                    int loyaltyPoints, double totalSpent, boolean active) {
        setCustomerId(customerId);
        setName(name);
        setPhone(phone);
        setMembership(membership);
        setLoyaltyPoints(loyaltyPoints);
        setTotalSpent(totalSpent);
        setActive(active);
    }

    // ---------- Getters ----------
    public String getCustomerId() { return customerId; }
    public String getName()       { return name; }
    public String getPhone()      { return phone; }
    public String getMembership() { return membership; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public double getTotalSpent() { return totalSpent; }
    public boolean isActive()     { return active; }

    // ---------- Setters (with simple validation) ----------
    public void setCustomerId(String customerId) {
        if (customerId != null && !customerId.trim().isEmpty()) {
            this.customerId = customerId.trim();
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone.trim();
        }
    }

    public void setMembership(String membership) {
        // only three tiers are allowed
        if (membership != null) {
            String m = membership.trim();
            if (m.equalsIgnoreCase("Standard") || m.equalsIgnoreCase("Silver")
                    || m.equalsIgnoreCase("Gold")) {
                this.membership = m.substring(0, 1).toUpperCase() + m.substring(1).toLowerCase();
            }
        }
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints >= 0) {
            this.loyaltyPoints = loyaltyPoints;
        }
    }

    public void setTotalSpent(double totalSpent) {
        if (totalSpent >= 0) {
            this.totalSpent = totalSpent;
        }
    }

    public void setActive(boolean active) { this.active = active; }

    // ---------- Additional methods ----------

    /** Discount rate (in %) depends on membership tier. Uses a switch. */
    public double getDiscountRate() {
        switch (membership) {
            case "Gold":   return 15.0;
            case "Silver": return 8.0;
            default:       return 0.0;
        }
    }

    /**
     * Records a purchase: adds to total spent and gives 1 point per RM10.
     * Then checks whether the customer should move up a tier.
     */
    public void addPurchase(double amount) {
        if (amount <= 0) {
            return;
        }
        totalSpent += amount;
        loyaltyPoints += (int) (amount / 10);
        upgradeMembershipIfEligible();
    }

    /** Silver at RM1000 total spent, Gold at RM3000. */
    public void upgradeMembershipIfEligible() {
        if (totalSpent >= 3000) {
            membership = "Gold";
        } else if (totalSpent >= 1000) {
            membership = "Silver";
        }
    }

    /** Prints a short summary of the customer. */
    public void displayInfo() {
        System.out.printf("[%s] %-10s %-12s %-8s Points: %4d  Spent: RM%8.2f  Account: %s%n",
                customerId, name, phone, membership, loyaltyPoints, totalSpent,
                active ? "Active" : "Inactive");
    }
}
