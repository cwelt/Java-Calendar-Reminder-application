package reminderAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import date.Date;

public interface IReminderModel
{
	// Constants:
	public final int maxDays = 30;
	public final int maxMonths = 12;
	public final int maxYears = 50;
	
	// Getters: 
	public Date getCurrentDate();
	public String getReminder(Date date);
	public String getFileName();
	public Hashtable<Date, String> getCalendar();
	public ArrayList<Integer> getYears();
	public ArrayList<String> getMonths();
	public ArrayList<Integer> getValidDays(Date date);
	
	// Setters: 
	public void setCalendar(Hashtable<Date, String> databaseCalendar);
	public void setFileName(String fileName);
	public void setReminder (Date date, String text);
	
	// I/O Interface: 
	public void WriteCalendarToDatabase() throws IOException;
	public boolean readCalendarFromDatabase() throws IOException, ClassNotFoundException;
}
