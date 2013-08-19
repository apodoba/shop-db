package com.apodoba.testUi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.apodoba.domain.GoodsEntity;

public class GoodsPanel extends JPanel{
	
	private JDialog dialog;
	private GoodsEntity goodsEntity;
	
	public GoodsPanel(JDialog dialog, GoodsEntity goodsEntity) {
		super();
		this.dialog = dialog;
		this.goodsEntity = goodsEntity;
		init();
	}
	
	public void init(){
		MigLayout layout = new MigLayout("", "[]10[]", "[]10[]");
		setLayout(layout);

		JLabel nameLabel = new JLabel("Name: " + goodsEntity.getName());
		JLabel nameValueLabel = new JLabel(goodsEntity.getName());
		
		JLabel descriptionLabel = new JLabel("Description: " + goodsEntity.getDescription());
		JLabel descriptionValueLabel = new JLabel(goodsEntity.getDescription());
		
		JLabel manufacturerLabel = new JLabel("Manufacturer: ");
		JLabel manufacturerValueLabel = new JLabel(goodsEntity.getManufacturer());

		JButton buy = new JButton("Buy");
		
		JButton close = new JButton("Close");
		close.addActionListener(new ClosePopupListener());

		add(nameLabel, "cell 0 0, span 2");
		add(descriptionLabel, "cell 0 1, span 2");
		add(buy, "cell 0 2, w 40%:45%:50%");
		add(close, "cell 1 2");
	}
	
	private class ClosePopupListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dialog.setVisible(false);
		}
	}
}
