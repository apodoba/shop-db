package com.apodoba.testUi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyJlable extends JLabel{
	
	private static final long serialVersionUID = -2328550734907822758L;
	
	private long id;
	private String name;
	private boolean isCategory = false;
	private boolean isGoods = false;
	
	public MyJlable(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public void setText(String text) {
		setIcon();
		super.setText(text);
	}
	private void setIcon(){
		ImageIcon icon = null;
		if(isCategory){
			icon = new ImageIcon("icons/labelIcon.png");
		}else {
			icon = new ImageIcon("icons/goodsIcon.png");
		}
		super.setIcon(icon);
		
	} 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCategory() {
		return isCategory;
	}

	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}

	public boolean isGoods() {
		return isGoods;
	}

	public void setGoods(boolean isGoods) {
		this.isGoods = isGoods;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED);
		g.drawOval(0, 0, 20, 20);
	}
	
}
