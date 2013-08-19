package com.apodoba.testUi;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class MyWindow extends JFrame {

	private static final String MARRIED_MALE = "Женат";
	private static final String MARRIED_FEMALE = "Замужем";

	public MyWindow() {
		super();
		setTitle("Test Window");
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initControls();
	}

	JCheckBox marriedCheckbox;

	private void initControls() {
		JPanel panel = (JPanel) this.getContentPane();
		MigLayout layout = new MigLayout("fill", "[]15[]", "");
		panel.setLayout(layout);

		JLabel nameLabel = new JLabel("ФИО:");
		JTextField nameInput = new JTextField("", 15);
		nameLabel.setLabelFor(nameInput);
		panel.add(nameLabel, "cell 0 0, align right");
		panel.add(nameInput, "cell 1 0, w 50%:65%:70%");

		// SEX
		JLabel sexLabel = new JLabel("Пол:");
		JRadioButton maleRadio = new JRadioButton("Мужской");
		maleRadio.setSelected(true);

		maleRadio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				marriedCheckbox.setText(MARRIED_MALE);

			}
		});

		JRadioButton femaleRadio = new JRadioButton("Женский");
		femaleRadio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				marriedCheckbox.setText(MARRIED_FEMALE);

			}
		});
		panel.add(sexLabel, "cell 0 1, align right");
		panel.add(maleRadio, "cell 1 1");
		panel.add(femaleRadio, "cell 1 2");

		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(maleRadio);
		sexGroup.add(femaleRadio);

		// AGE
		JLabel birthLabel = new JLabel("Дата рождения:");
		Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();
		calendar.add(Calendar.YEAR, -100);
		Date earliestDate = calendar.getTime();
		calendar.add(Calendar.YEAR, 200);
		Date latestDate = calendar.getTime();
		SpinnerModel dateModel = new SpinnerDateModel(initDate, earliestDate,
				latestDate, Calendar.YEAR);// ignored for user input

		JSpinner birthSpinner = new JSpinner(dateModel);
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthSpinner,
				"dd/MM/yyyy");
		birthSpinner.setEditor(dateEditor);
		panel.add(birthLabel, "cell 0 3, align right");
		panel.add(birthSpinner, "cell 1 3");

		// Weight
		JLabel weightLabel = new JLabel("Вес:");
		SpinnerNumberModel numberModel = new SpinnerNumberModel(45.0, 2.0,
				300.0, 0.1);
		JSpinner weightSpinner = new JSpinner(numberModel);
		panel.add(weightLabel, "cell 0 4, align right");
		panel.add(weightSpinner, "cell 1 4");

		// Build
		JLabel buildLabel = new JLabel("Жирдяйство:");
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(1, new JLabel("дрыщь"));
		labelTable.put(2, new JLabel("жиробас"));
		labelTable.put(3, new JLabel("салотопный завод"));
		JSlider buildSlider = new JSlider(1, 3);
		buildSlider.setMinorTickSpacing(1);
		buildSlider.setMajorTickSpacing(1);
		buildSlider.setPaintTicks(true);
		buildSlider.setPaintLabels(true);
		buildSlider.setLabelTable(labelTable);
		panel.add(buildLabel, "cell 0 5, align right");
		panel.add(buildSlider, "cell 1 5, w 50%:70%:90%");

		// Married
		marriedCheckbox = new JCheckBox(MARRIED_MALE);
		panel.add(marriedCheckbox, "cell 1 6");
	}

	/*public static void main(String[] args) {
		MyWindow window = new MyWindow();
		window.setVisible(true);
	}
*/
	
}
