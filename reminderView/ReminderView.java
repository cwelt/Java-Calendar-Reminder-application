package reminderView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import date.Date;
import reminderAPI.IReminderModel;
import reminderAPI.IReminderView;

public class ReminderView extends JFrame implements IReminderView
{
	// Static variables: 
	private static final long serialVersionUID = 2526677148315437465L;
	
	// GUI Instance variables: 
	
	// Panels
	private final JPanel northPanel;
	private final JPanel southPanel;
	private final JPanel eastPanel;
	private final JPanel westPanel;
	private final JPanel centerPanel;
	private final JPanel controllsPanel;
	private final JPanel dateSelectorPanel;
	private final JPanel fileNamePanel;
	
	// ComboBoxes for selecting a date 
	private final JComboBox<Integer> dayComboBox;
	private final JComboBox<String> monthComboBox;
	private final JComboBox<Integer> yearComboBox;
	
	// Buttons & Controls:
	private JButton selectTextButton;
	private JButton saveButton;
	private JButton importButton;
	private JButton clearTextButton;
	private final JTextArea reminderText;
	private final JTextField fileName;
	
	// Labels:
	private final JLabel ApplicationHeaderLabel; 
	private final JLabel yearLabel;
	private final JLabel monthLabel;
	private final JLabel dayLabel;
	private final JLabel fileNameLabel;
	private final JLabel statusBar;
	
	// Icons:
	private final Icon calendarIcon;
	private final Icon selectIcon;
	private final Icon saveIcon;
	private final Icon importIcon;
	private final Icon clearTextIcon;
	
	// Constructor - Initialize all GUI components:
	public ReminderView (IReminderModel model)
	{
		super("Callendar Reminder Application");
		this.setSize(500, 500);
		
		// Create panels
		northPanel = new JPanel(new BorderLayout());
		southPanel = new JPanel(new BorderLayout());
		eastPanel = new JPanel(new BorderLayout());
		westPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new BorderLayout());	
		controllsPanel = new JPanel(new GridLayout(0,1,5,5));
		fileNamePanel = new JPanel(new GridLayout(1,2,1,1));
		
		// Set Panels locations:
		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(westPanel, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		
		// Application Title 
		calendarIcon = new ImageIcon(getClass().getResource("calendar.png"));
		ApplicationHeaderLabel = new JLabel("Calendar reminder application", calendarIcon, SwingConstants.LEFT);
		ApplicationHeaderLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		ApplicationHeaderLabel.setBackground(new Color (238,203,173));
		ApplicationHeaderLabel.setOpaque(true);
		northPanel.add(ApplicationHeaderLabel, BorderLayout.PAGE_START);
		
		// Date selector section: combo boxes, labels, select button etc.
		yearLabel = new JLabel("Year");
		yearLabel.setOpaque(true);
		yearLabel.setBackground(new Color(205,175,149));
		yearLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		yearLabel.setToolTipText("Select year from the dropdown list");
		monthLabel = new JLabel("Month");
		monthLabel.setOpaque(true);
		monthLabel.setBackground(new Color(222,184,135));
		monthLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		monthLabel.setToolTipText("Select month from the dropdown list");
		dayLabel = new JLabel("Day");
		dayLabel.setOpaque(true);
		dayLabel.setBackground(new Color(245,222,179));
		dayLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		dayLabel.setToolTipText("Select day from the dropdown list");
		
		Integer[] yearArray = new Integer[IReminderModel.maxYears];
		yearArray = model.getYears().toArray(yearArray);
		String[] monthArray = new String[IReminderModel.maxMonths];
		model.getMonths().toArray(monthArray);
		Integer[] dayArray = new Integer[IReminderModel.maxDays]; 
		dayArray = model.getValidDays(new Date(1,1,2018)).toArray(dayArray);
	
		yearComboBox = new JComboBox<Integer>(yearArray);
		yearComboBox.setBackground(new Color(205,175,149));
		yearComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		monthComboBox = new JComboBox<String>(monthArray);
		monthComboBox.setBackground(new Color(222,184,135));
		monthComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		dayComboBox = new JComboBox<Integer>(dayArray);
		dayComboBox.setBackground(new Color(245,222,179));
		dayComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		
		dateSelectorPanel = new JPanel(new GridLayout(0, 2, 2, 2));
		dateSelectorPanel.setOpaque(true);
		dateSelectorPanel.setBackground(Color.BLACK);
		dateSelectorPanel.add(yearLabel);
		dateSelectorPanel.add(yearComboBox);
		dateSelectorPanel.add(monthLabel);
		dateSelectorPanel.add(monthComboBox);
		dateSelectorPanel.add(dayLabel);
		dateSelectorPanel.add(dayComboBox);
		
		selectIcon = new ImageIcon(getClass().getResource("search.png"));
		selectTextButton = new JButton("Search reminder text for selected date", selectIcon);
		selectTextButton.setAlignmentY(LEFT_ALIGNMENT);
		selectTextButton.setBackground(new Color(255,215,0));
		selectTextButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		selectTextButton.setToolTipText("Get text saved for current selected date");
		
		northPanel.add(dateSelectorPanel, BorderLayout.CENTER);
		northPanel.add(selectTextButton, BorderLayout.PAGE_END);
		
		// Text Area section
		String defaultText = "Enter your reminder text here...";
		reminderText = new JTextArea(defaultText, 15, 20);
		reminderText.setBackground(new Color(255,255,225));
		reminderText.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		centerPanel.add(new JScrollPane(reminderText), BorderLayout.CENTER);
		
		// Filename section
		fileName = new JTextField("Calendar.ser");
		fileName.setFont(new Font("David", Font.PLAIN, 20));
		fileNameLabel = new JLabel("File name:");
		fileNameLabel.setFont(new Font("David", Font.BOLD, 20));
		fileNameLabel.setToolTipText("Name of file to be saved on disk (you could use default");
		fileNamePanel.add(fileNameLabel);
		fileNamePanel.add(fileName);
		centerPanel.add(fileNamePanel, BorderLayout.SOUTH);
		
		// Controls section - save, import, clear, etc. 
		saveIcon = new ImageIcon(getClass().getResource("save.png"));
		saveButton = new JButton("Save", saveIcon); 
		saveButton.setToolTipText("Download calendar data to file in serialized format");
		controllsPanel.add(saveButton);
		
		importIcon = new ImageIcon(getClass().getResource("import.png"));
		importButton = new JButton("Import", importIcon); 
		importButton.setToolTipText("Import calendar data from serialzied file");
		controllsPanel.add(importButton);
		
		clearTextIcon = new ImageIcon(getClass().getResource("clearText.png"));
		clearTextButton = new JButton("Clear text", clearTextIcon);
		clearTextButton.setToolTipText("Clear text for current date");
		controllsPanel.add(clearTextButton);
		
		eastPanel.add(controllsPanel);
		this.add(centerPanel, BorderLayout.CENTER);
		
		// Status bar section:
		statusBar = new JLabel("@Chanan Welt: Maman14 - Question 2");
		statusBar.setFont(new Font("David", Font.PLAIN, 16));
		statusBar.setOpaque(true);
		statusBar.setBackground(Color.LIGHT_GRAY);
		southPanel.add(statusBar, BorderLayout.SOUTH);
	}
	
	// Utility initialization for setting GUI first time:
	public void initGui() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	// Register event handlers for GUI components: 
	public void addSaveListener(ActionListener saveActionListener) 
	{saveButton.addActionListener(saveActionListener);}

	public void addYearListener(ItemListener handler)
	{yearComboBox.addItemListener(handler);}
	
	public void addMonthListener(ItemListener handler) 
	{monthComboBox.addItemListener(handler);}
	
	public void addDayListener(ItemListener handler) 
	{dayComboBox.addItemListener(handler);}

	public void addSelectTextListener(ActionListener SelectTextHandler) 
	{selectTextButton.addActionListener(SelectTextHandler);}

	public void addImportListener(ActionListener importHandler) 
	{importButton.addActionListener(importHandler);}
	
	public void addClearListener(ActionListener clearActionListener) 
	{clearTextButton.addActionListener(clearActionListener);}

	public void addFileNameListener(ActionListener fileNameHandler)
	{fileName.addActionListener(fileNameHandler);}
	
	// Getters: 
	public Date getInputDate() {return new Date(getInputDay(), getInputMonth(), getInputYear());}
	public int getInputYear(){return (Integer)yearComboBox.getSelectedItem();}
	public int getInputMonth() {return 1 + monthComboBox.getSelectedIndex();}
	public int getInputDay() {return (Integer)dayComboBox.getSelectedItem();}
	public String getInputText() {return reminderText.getText();}
	public String getInputFileName() {return fileName.getText();}
	
	// Setters: 
	public void setReminderInputText(String newText) {reminderText.setText(newText);}
	public void setInputFileName(String newFileName) {fileName.setText(newFileName);}
	
	public void setDayList(ArrayList<Integer> days)
	{
		dayComboBox.removeAllItems();
		for (int day: days)
			dayComboBox.addItem(day);
	}
	
	public void setDate(Date date)
	{
		yearComboBox.setSelectedItem(date.getYear());	
		monthComboBox.setSelectedIndex(date.getMonth()-1);
		dayComboBox.setSelectedItem(date.getDay());
	}
	
	public void setStatusBar(String message)
	{
		SecureRandom randomNumbers = new SecureRandom();
		int randomRedColor = randomNumbers.nextInt(256);
		int randomBlueColor = randomNumbers.nextInt(256);
		statusBar.setOpaque(true);
		statusBar.setText(message);
		statusBar.setBackground(new Color(randomRedColor, 255, randomBlueColor));
	}
	
	// update data according to observed model & changed object:
	public void update(Observable model, Object changedObject) 
	{
		statusBar.setText(String.format("File %s has been imported successfully", changedObject));
	}
	
	// Display Error Message:
	public void displayErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(this, message, "Reminder Application", JOptionPane.ERROR_MESSAGE);
	}
}
