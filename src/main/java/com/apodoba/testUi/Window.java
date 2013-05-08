package com.apodoba.testUi;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.apodoba.Connect;
import com.apodoba.dao.ShopDao;
import com.apodoba.domain.CategoryEntity;
import com.apodoba.domain.GoodsEntity;

public class Window extends JFrame {

	private static final long serialVersionUID = 2332860039357743575L;

	private JTree tree;

	public Window() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		init();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void init() {

		Box topLayout = Box.createVerticalBox();
		topLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel logotype = new JLabel("Shop");
		logotype.setFont(new Font("Serif", Font.PLAIN, 24));
		logotype.setAlignmentX(CENTER_ALIGNMENT);
		topLayout.add(logotype);

		Box bottomLayout = Box.createVerticalBox();
		bottomLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		bottomLayout.add(Box.createVerticalGlue());

		JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		verticalSplitPane.setLeftComponent(topLayout);
		verticalSplitPane.setRightComponent(bottomLayout);

		Box leftLayout = Box.createVerticalBox();
		leftLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Categories");
		createNodes(top);
		tree = new JTree(top);
		tree.setFont(new Font("Serif", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tree);
		Dimension panelSize = this.getSize();
		Dimension scrollPanelSize = scrollPane.getPreferredSize();

		scrollPanelSize.width = (int) panelSize.width / 4;
		scrollPane.setPreferredSize(scrollPanelSize);
		leftLayout.add(scrollPane);

		Box rightLayout = Box.createVerticalBox();
		rightLayout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		rightLayout.add(Box.createVerticalGlue());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(leftLayout);
		splitPane.setRightComponent(rightLayout);

		bottomLayout.add(splitPane);

		this.add(verticalSplitPane);

	}

	private void createNodes(DefaultMutableTreeNode top) {		
		
		ShopDao shopDao = Connect.getDao();
		List<CategoryEntity> categoriesWithoutParent = shopDao.getCategoriesWithoutParent();
			
		for(CategoryEntity categoryEntity: categoriesWithoutParent){
			setCategoryNodes(top, categoryEntity);
		}
		Connect.closeSession();
	}

	private void setCategoryNodes(DefaultMutableTreeNode parentNode, CategoryEntity categoryEntity) {
		DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(categoryEntity.getName());
		parentNode.add(categoryNode);
		
		ShopDao shopDao = Connect.getDao();
		List<CategoryEntity> categoriesWithoutParent = shopDao.getCategoriesWithParent(categoryEntity.getId());
		List<GoodsEntity> categoryGoods = new ArrayList<GoodsEntity>();
		categoryGoods.addAll(categoryEntity.getGoods());
		
		for(CategoryEntity subCategory: categoriesWithoutParent){
			setCategoryNodes(categoryNode, subCategory);
		}

		for(GoodsEntity goods: categoryGoods){
			setGoodsInCategory(categoryNode, goods);
		}
		
	}

	private void setGoodsInCategory(DefaultMutableTreeNode categoryNode, GoodsEntity goodsEntity) {
		DefaultMutableTreeNode goodsNode = new DefaultMutableTreeNode(goodsEntity.getName());
		categoryNode.add(goodsNode);
	}

}
