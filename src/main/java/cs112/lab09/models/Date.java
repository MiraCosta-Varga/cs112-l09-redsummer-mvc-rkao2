package cs112.lab09.models;

/**
 * Date.java - Date class represents a month, day, year date
 *
 * Class invariant:
 * Although validity is checked, it is deffined as generally valid individual
 * parts. Month: spelled correctly or 1-12. Day: 1-31. Year: 4 digits (1000-9999).
 * February 31st would be allowed, this kind of validity is defined and allowed to
 * keep the solution simple (but can definitely be upgraded later)!
 *
 * @author Nery Chapeton-Lamas <nery@miracosta.edu>
 * @version 1.0
 */

public class Date
{
    /********** CONSTANTS *********/
    public static final String DEFAULT_MONTH = "December";
    public static final int DEFAULT_DAY = 31;
    public static final int DEFAULT_YEAR = 1959;


    /********** INSTANCE VARIABLES **********/
    private String month;
    private int day;
    private int year; //four digit year


    /********** CONSTRUCTORS **********/
    /**
     * Default constructor sets Date object to default values
     */
    public Date()
    {
        this(DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_YEAR);
    }

    /**
     * Full constructor sets Date object to parameter values if valid, otherwise
     * outputs error message and exits program.
     *
     * @param month String version of month, should start with capital letter but allows lowercase
     * @param day integer between 1 and 31, inclusive
     * @param year 4-digit integer
     */
    public Date(String month, int day, int year)
    {
        boolean isValid;

        isValid = this.setAll(month, day, year); //if valid, sets data for us!
        if( !isValid )
        {
            System.out.println("ERROR: bad date in full constructor!");
            System.out.println("Exiting program...");
            System.exit(0);
        }
    }

    /**
     * Full constructor sets Date object to parameter values if valid, otherwise
     * outputs error message and exits program.
     *
     * @param month integer version of month, 1-12 inclusive
     * @param day integer between 1 and 31, inclusive
     * @param year 4-digit integer
     */
    public Date(int month, int day, int year)
    {
        this(Date.monthToString(month), day, year);
    }

    /**
     * Copy constructor creates a deep copy of original Date object, if object null
     * outputs error message and exits program.
     *
     * @param original valid Date object to deep copy
     */
    public Date(Date original)
    {
        if (original != null)
        {
            this.setAll(original.month, original.day, original.year);
        }
        else
        {
            System.out.println("ERROR: trying to copy NULL Date object. Exiting program...");
            System.exit(0);
        }

    }


    /********** SETTERS / MUTATORS **********/
    /**
     * Sets month instance variable with error checking
     *
     * @param month case-insensitive for data validation, but stores it as argument casing
     *
     * @return true if month is valid (spelled month correctly), false otherwise.
     */
    public boolean setMonth(String month)
    {
        //BEST WAY (boolean method, error checking only so more flexible):
        boolean isValid;

        isValid = Date.isValidMonth(month);

        if(isValid)
        {
            this.month = month;
        }

        return isValid;


    }

    /**
     * Sets month instance variable with error checking
     *
     * @param month integer, checked for validity
     *
     * @return true if month is valid (1-12 inclusive), false otherwise.
     */
    public boolean setMonth(int month)
    {
        boolean isValid;

        isValid = (month >= 1 && month <= 12);

        if(isValid)
        {
            this.month = this.monthToString(month); //convert month # to string!
        }

        return isValid;
    }

    /**
     * Sets day instance variable with error checking
     *
     * @param day integer, checked for validity
     *
     * @return true if day is valid (1-31 regaqrdless of month/year), false otherwise.
     */
    public boolean setDay(int day)
    {
        boolean isValid;

        isValid = Date.isValidDay(day);

        if(isValid)
        {
            this.day = day;
        }

        return isValid;
    }

    /**
     * Sets year instance variable with error checking
     *
     * @param year integer, checked for validity
     *
     * @return true if year is valid 4 digit year (1000-9999), false otherwise.
     */
    public boolean setYear(int year)
    {
        boolean isValid;

        isValid = Date.isValidYear(year);

        if(isValid)
        {
            this.year = year;
        }

        return isValid;
    }

    /**
     * Sets all instance variable with error checking
     *
     * @param month case-insensitive for data validation, but stores it as argument casing
     * @param day integer, checked for validity
     * @param year integer, checked for validity
     *
     * @return true if all arguments valid, false otherwise.
     */
    public boolean setAll(String month, int day, int year) {
        boolean isValid;

        isValid = Date.isValidMonth(month) && Date.isValidDay(day)
                && Date.isValidYear(year);
        if(isValid)
        {
            this.setMonth(month);
            this.setDay(day);
            this.setYear(year);
        }

        return isValid;
    }



    /********** GETTERS / ACCESSOR METHODS **********/
    /**
     * Access value of month instance variable
     *
     * @return month String value stored in case originally set in
     */
    public String getMonth()
    {
        return this.month;
    }

    /**
     * Access value of day instance variable
     *
     * @return day value for object
     */
    public int getDay()
    {
        return this.day;
    }

    /**
     * Access value of year instance variable
     *
     * @return year value for object
     */
    public int getYear()
    {
        return this.year;
    }



    /********** OTHER REQUIRED METHODS **********/
    @Override
    public String toString()
    {
        return this.monthToInt(this.month) + "/" + this.day + "/" + this.year;

        //OTHER OPTIONS (just make sure to update documentation appropriately):
        //return this.month + " " + this.day + ", " + this.year; //USA
        //return  this.day + " " + this.month  + ", " + this.year; //other countries
    }

    @Override
    public boolean equals(Object other)
    {
        Date otherDate;

        if(other == null)
        {
            return false;
        }
        else if(! (other instanceof Date)) //why not use getClass() introspection here?
        {
            return false;
        }
        else
        {
            otherDate = (Date) other;
            return this.month.equals(otherDate.month) && this.day == otherDate.day
                    && this.year == otherDate.year;
        }
    }

    /**
     * Determines if calling object comes before param Date (checks month, day, year completely)
     *
     * @param other Date object to check if it comes AFTER calling object
     *
     * @return true if calling object precedes Date parameter, false otherwise
     */
    public boolean precedes(Date other)
    {
        int otherMonth, thisMonth;
        otherMonth = Date.monthToInt(other.month);
        thisMonth = Date.monthToInt(this.month);

        return (this.year < other.year) ||
                (this.year == other.year && thisMonth < otherMonth) ||
                (this.year == other.year && thisMonth == otherMonth && this.day < other.day);
    }

    /********** VALIDITY METHODS **********/
    /**
     * Helper method to check String month for validity (case-insensitive)
     *
     * @param month String month to check for validity
     *
     * @return true for valid month (case-insensitive), false otherwise
     */
    public static boolean isValidMonth(String month)
    {
        return Date.monthToInt(month) != 0;
    }

    /**
     * Helper method to check day for validity (1-31 inclusive, regardless of month/year)
     *
     * @param day day to check for validity
     *
     * @return true for valid day, false otherwise
     */
    public static boolean isValidDay(int day)
    {
        return day >= 1 && day <= 31;
    }

    /**
     * Helper method to check year for validity (4 digits)
     *
     * @param year year to check for validity (1000-9999)
     *
     * @return true for valid year (4 digits), false otherwise
     */
    public static boolean isValidYear(int year)
    {
        return year >= 1000 && year <= 9999;
    }


    /********** HELPER METHODS **********/
    /**
     * Helper method converts month text into a number (case-insensitive)
     *
     * @param month String month to attempt to convert
     *
     * @return returns 1-12 corresponding month if valid, 0 if error
     */
    private static int monthToInt(String month)
    {
        int result;

        if(month.equalsIgnoreCase("January"))
        {
            result = 1;
        }
        else if (month.equalsIgnoreCase("February"))
        {
            result = 2;
        }
        else if (month.equalsIgnoreCase("March"))
        {
            result = 3;
        }
        else if (month.equalsIgnoreCase("April"))
        {
            result = 4;
        }
        else if (month.equalsIgnoreCase("May"))
        {
            result = 5;
        }
        else if (month.equalsIgnoreCase("June"))
        {
            result = 6;
        }
        else if (month.equalsIgnoreCase("July"))
        {
            result = 7;
        }
        else if (month.equalsIgnoreCase("August"))
        {
            result = 8;
        }
        else if(month.equalsIgnoreCase("September"))
        {
            result = 9;
        }
        else if (month.equalsIgnoreCase("October"))
        {
            result = 10;
        }
        else if (month.equalsIgnoreCase("November"))
        {
            result = 11;
        }
        else if (month.equalsIgnoreCase("December"))
        {
            result = 12;
        }
        else
        {
            result = 0; //0 indicates error!
        }

        return result;
    }

    /**
     * Helper method converts month number to text (starting with uppercase letter)
     *
     * @param month integer month to attempt to convert
     *
     * @return if valid, returns "January", etc., else returns null for error
     */
    private static String monthToString(int month)
    {
        String result;

        switch(month)
        {
            case 1:
                result = "January";
                break;
            case 2:
                result = "February";
                break;
            case 3:
                result = "March";
                break;
            case 4:
                result = "April";
                break;
            case 5:
                result = "May";
                break;
            case 6:
                result = "June";
                break;
            case 7:
                result = "July";
                break;
            case 8:
                result = "August";
                break;
            case 9:
                result = "September";
                break;
            case 10:
                result = "October";
                break;
            case 11:
                result = "November";
                break;
            case 12:
                result = "December";
                break;
            default:
                result = null;	//indicates error
                break;
        }

        return result;
    }
}