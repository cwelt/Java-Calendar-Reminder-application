package date;

import java.io.Serializable;
import java.time.LocalDate;

public class Date implements Serializable, Comparable<Date>
{
	/**
	 * Utility Class that represents a date by day, month & year.
	 * The date instances of this class represent a key identification
	 * for hash data structures, there it overwrites hashCode & equals.
	 * This class implements Serializable so that is could be written in binary format to streams.
	 */
	
	// Static variables: 
	private static final long serialVersionUID = -3524210606929611060L;
	
	// Instance Variables 
	private int day;
	private int month;
	private int year;
	
	// Customized Constructor: 
	public Date(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	// No argument Constructor:
	public Date()
	{
		LocalDate date = LocalDate.now();
		this.day = date.getDayOfMonth();
		this.month = date.getMonthValue();
		this.year = date.getYear();
	}

	// Getters: 
	public int getDay() {return day;}
	public int getMonth() {return month;}
	public int getYear() {return year;}

	// Setters: 
	public void setDay(int day) {this.day = day;}
	public void setMonth(int month) {this.month = month;}
	public void setYear(int year) {this.year = year;}

	// Generate hashCode for this object's date value: 
	@Override
	public int hashCode() 
	{
		int hashCode;
		String hashFunction = String.format("%d%d%d", day, month, year);
		hashCode = Integer.parseInt(hashFunction);
		return hashCode;
	}

	// Check equation of two dates
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	// Compare two dates:
	@Override
	public int compareTo(Date parameterDate) 
	{
		LocalDate thisDate, otherDate; 
		thisDate = LocalDate.of(this.year, this.month, this.day);
		otherDate = LocalDate.of(parameterDate.year, parameterDate.month, parameterDate.day); 
		return thisDate.compareTo(otherDate);
	}

	// Return string format represting date: 
	@Override
	public String toString()
	{
		return (LocalDate.of(getYear(), getMonth(), getDay())).toString();
	}
	
	// Return max valid day according to year and month 
	public static int maxDayOfMonthAndYear(int year, int month)
	{
		switch (month)
		{
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return 31;
			case 4: case 6: case 9: case 11: 
				return 30;
			case 2:
				if (LocalDate.of(year, 1, 1).isLeapYear())
					return 29;
				else return 28;
			default: return 0; //invalid month
		}
	}
}
