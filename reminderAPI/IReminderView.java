package reminderAPI;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Observer;

import date.Date;

public interface IReminderView extends Observer
{
	// Getters:
	public int getInputDay();
	public int getInputMonth();
	public int getInputYear();
	public Date getInputDate();
	public String getInputText();
	public String getInputFileName();
	
	// Setters: 
	public void setDate(Date date);
	public void setStatusBar(String message);
	public void setInputFileName(String newFileName);
	public void setDayList(ArrayList<Integer> days);
	public void setReminderInputText(String newText);
	
	// GUI general operations:
	public void displayErrorMessage(String message);
	public void initGui();
	
	// Event listeners registration interface: 
	public void addDayListener(ItemListener dayItemListener);
	public void addMonthListener(ItemListener monthItemListener);
	public void addYearListener(ItemListener yearItemListener);
	public void addSaveListener(ActionListener saveHandler);
	public void addImportListener(ActionListener loadHandler);
	public void addSelectTextListener(ActionListener SelectTextHandler);
	public void addClearListener(ActionListener clearActionListener);
	public void addFileNameListener(ActionListener fileNameHandler);
}
