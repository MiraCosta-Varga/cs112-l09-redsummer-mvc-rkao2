/* 
HistoricalEvent.Java - Represents any historical event with a description and date. 

Class Invarients- NO b/c of the Instance varibles 
*/
package cs112.lab09.models;

public class HistoricalEvent {
    // Constants
    public static final String DEFAULT_DESCRIPTION = "Historical event description";
    public static final Date DEFAULT_EVENT_DAY = new Date();

    // Instance varibles
    private String description;
    private Date eventDay;

    // Full Constructor
    /*
     * Full constructor sets object to parameter values if valis, otherwise outputs
     * error message and exits program.
     *
     * @param description original "historical" description
     *
     * @param eventDay Date of historical event
     */
    public HistoricalEvent(String description, Date eventDay) {
        if (!this.setAll(description, eventDay)) {
            System.out.println("ERROR: bad data given to full constructor");
            System.exit(0);
        }
    }

    // Default Constructor
    /*
     * Default constructor sets HistoricalEvent object to default values
     */
    public HistoricalEvent() {
        this(DEFAULT_DESCRIPTION, DEFAULT_EVENT_DAY);
    }

    /*
     * Copy Constructor creates a deep copy of original HistoricalEvent *object, if
     * object null outputs error message and exits program.
     *
     * @param original valid HistoricalEvent object to deep copy
     */
    // Copy Constructor
    public HistoricalEvent(HistoricalEvent original) {
        if (original != null) {
            this.setAll(original.description, original.eventDay);
        } else {
            System.out.println("ERROR: trying to copy NULL Date object. Exiting program...");
            System.exit(0);
        }
    }

    // Setter Methods
    /*
     * Sets description instance variable (no error checking, except checks for
     * null)
     *
     * @param description historical event description
     *
     * @return true if set, false if parameter is null
     */
    public boolean setDescription(String description) {
        if (description != null) {
            this.description = description;
            return true;
        } else {
            return false;
        }
    }

    /*
     * Sets eventDay variable (no error checking, except checks for null) Deep
     * copies object to ensure encapsulation
     *
     * @param eventDay Date object representing day of historical event, should not
     * change in any way
     *
     * @return true if set, false if parameter is null
     */
    public boolean setEventDay(Date eventDay) {
        if (eventDay != null) {
            this.eventDay = new Date(eventDay);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Sets all instance variables (no error checking, except checks for null) Deep
     * copies object to ensure encapsulation
     *
     * @param description and eventDay
     *
     * @return true if set, false if parameter is null
     */
    // SetAll Method
    public boolean setAll(String description, Date eventDay) {
        return (this.setDescription(description) && this.setEventDay(eventDay));
    }

    // Getter Methods
    /*
     * Access value of description instance variable
     *
     * @return description of historical event
     */
    public String getDescription() {
        return this.description;
    }

    /*
     * Access value of eventDay instance variable (deep copy to preserve
     * encapsulation)
     *
     * @return date of historical event
     */
    public Date getEventDay() {
        return new Date(this.eventDay); // This is the deep copy :)
    }

    // toString Method
    @Override
    public String toString() {
        return "On " + this.eventDay + "; " + this.description ;
    }

    // Equals Method
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (!(other instanceof HistoricalEvent)) {
            return false;
        } else {
            HistoricalEvent otherEvent = (HistoricalEvent) other;
            return this.description.equals(otherEvent.description) && this.eventDay.equals(otherEvent.eventDay);
        }
    }

}