/**
 * Pet represents one animal in a pet shop's inventory.
 * All attributes are private so other classes can only reach them
 * through the getters and setters (encapsulation).
 */
public class Pet {

    // ---------- Attributes (all private) ----------
    private String petId;        // unique code, e.g. "P001"
    private String name;         // pet's name
    private String species;      // Cat, Dog, Rabbit, etc.
    private int ageInMonths;     // age counted in months
    private double price;        // selling price in RM
    private boolean vaccinated;  // has the pet had its shots?
    private boolean available;   // still in the shop or already sold?

    // ---------- Constructors ----------

    /** Default constructor: sets safe starting values. */
    public Pet() {
        this.petId = "P000";
        this.name = "Unnamed";
        this.species = "Unknown";
        this.ageInMonths = 0;
        this.price = 0.0;
        this.vaccinated = false;
        this.available = true;
    }

    /** Parameterized constructor: goes through the setters so validation is reused. */
    public Pet(String petId, String name, String species, int ageInMonths,
               double price, boolean vaccinated, boolean available) {
        setPetId(petId);
        setName(name);
        setSpecies(species);
        setAgeInMonths(ageInMonths);
        setPrice(price);
        setVaccinated(vaccinated);
        setAvailable(available);
    }

    // ---------- Getters ----------
    public String getPetId()      { return petId; }
    public String getName()       { return name; }
    public String getSpecies()    { return species; }
    public int getAgeInMonths()   { return ageInMonths; }
    public double getPrice()      { return price; }
    public boolean isVaccinated() { return vaccinated; }
    public boolean isAvailable()  { return available; }

    // ---------- Setters (with simple validation) ----------
    public void setPetId(String petId) {
        if (petId != null && !petId.trim().isEmpty()) {
            this.petId = petId.trim();
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setSpecies(String species) {
        if (species != null && !species.trim().isEmpty()) {
            this.species = species.trim();
        }
    }

    public void setAgeInMonths(int ageInMonths) {
        // age cannot be negative
        if (ageInMonths >= 0) {
            this.ageInMonths = ageInMonths;
        }
    }

    public void setPrice(double price) {
        // price cannot be negative
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setVaccinated(boolean vaccinated) { this.vaccinated = vaccinated; }
    public void setAvailable(boolean available)   { this.available = available; }

    // ---------- Additional methods ----------

    /** Returns the price after taking off a percentage discount (0 to 100). */
    public double calculateDiscountedPrice(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            return price; // ignore a silly discount value
        }
        return price - (price * discountPercent / 100.0);
    }

    /** Pets younger than 6 months count as babies. */
    public boolean isBabyPet() {
        return ageInMonths < 6;
    }

    /** A pet can only be sold if it is still available AND has been vaccinated. */
    public boolean canBeSold() {
        return available && vaccinated;
    }

    /** Prints a short summary of the pet. */
    public void displayInfo() {
        System.out.printf("[%s] %-8s %-7s %2d mth  RM%8.2f  Vaccinated: %-3s  Status: %s%n",
                petId, name, species, ageInMonths, price,
                vaccinated ? "Yes" : "No",
                available ? "Available" : "Sold");
    }
}
