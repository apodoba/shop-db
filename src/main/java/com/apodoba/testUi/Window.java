package com.apodoba.testUi;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.apodoba.Connect;
import com.apodoba.dao.ShopDao;
import com.apodoba.domain.CategoryEntity;
import com.apodoba.domain.GoodsEntity;

public class Window extends JFrame {

	private static final long serialVersionUID = 2332860039357743575L;

	private JEditorPane breadcrumbPanel;
	private List<Breadcrumb> breadcrumbsList = new ArrayList<Breadcrumb>();
	private JPanel listPane;

	private JDialog dialog;

	public Window() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize((int) screenSize.getWidth() / 3,
				(int) ((int) screenSize.getHeight() / 1.2));
		this.setLocation((int) (screenSize.getWidth() / 3), 0);
		Breadcrumb breadcrumb = new Breadcrumb(-1, "Home");
		breadcrumbsList.add(breadcrumb);
		init();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void init() {

		JPanel panel = (JPanel) this.getContentPane();
		MigLayout layout = new MigLayout();
		panel.setLayout(layout);

		// breadcrumb panel
		breadcrumbPanel = new JEditorPane();
		breadcrumbPanel.setContentType("text/html");
		breadcrumbPanel.setText(breadcrumbsList.get(0).url);

		breadcrumbPanel.setEditable(false);
		breadcrumbPanel.addHyperlinkListener(new BreadecrumbsListener());

		// Lay out the label and scroll pane from top to bottom.
		listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane listScroller = new JScrollPane(listPane);
		listScroller.setAlignmentX(LEFT_ALIGNMENT);

		createCategoriesLabels(listPane);

		breadcrumbPanel.setBackground(listScroller.getBackground());

		panel.add(breadcrumbPanel, "cell 0 0");
		panel.add(listPane, "cell 0 1, w 70%:100%:100%, h 70%:100%:100%");

	}

	private class BreadecrumbsListener implements HyperlinkListener {

		public void hyperlinkUpdate(HyperlinkEvent e) {
			HyperlinkEvent.EventType type = e.getEventType();
			if (type == HyperlinkEvent.EventType.ACTIVATED) {
				int id = Integer.valueOf(e.getDescription());

				Breadcrumb breadcrumb = new Breadcrumb(id, null);

				int selectCatIndex = breadcrumbsList.indexOf(breadcrumb);
				int listSize = breadcrumbsList.size();

				for (int i = listSize - 1; i > selectCatIndex; i--) {
					breadcrumbsList.remove(i);
				}
				listPane.removeAll();
				setToBreadcrumbsFromList();
				if (id != -1) {
					createLabelsForCategory(id);
					createLabelsForGoods(id);
				} else {
					System.out.println("id = -1");
					createCategoriesLabels(listPane);
				}

			}
		}
	}

	private void createCategoriesLabels(JPanel panel) {

		ShopDao shopDao = Connect.getDao();
		List<CategoryEntity> categoriesWithoutParent = shopDao
				.getCategoriesWithoutParent();

		for (CategoryEntity categoryEntity : categoriesWithoutParent) {
			MyJlable myJlable = new MyJlable(categoryEntity.getId(),
					categoryEntity.getName());
			myJlable.setCategory(true);
			myJlable.setText(categoryEntity.getName());
			myJlable.addMouseListener(new LabelListener());

			panel.add(myJlable);
		}
		Connect.closeSession();
	}

	private void createLabelsForCategory(long id) {

		ShopDao shopDao = Connect.getDao();
		List<CategoryEntity> categoriesWithParent = shopDao
				.getCategoriesWithParent(id);

		for (CategoryEntity subCategory : categoriesWithParent) {
			MyJlable myJlable = new MyJlable(subCategory.getId(),
					subCategory.getName());
			myJlable.setCategory(true);
			myJlable.setText(subCategory.getName());
			myJlable.addMouseListener(new LabelListener());
			listPane.add(myJlable);
		}

		Connect.closeSession();
	}

	private void createLabelsForGoods(long id) {

		ShopDao shopDao = Connect.getDao();
		Set<GoodsEntity> goodsFromCategory = shopDao.getCategoryById(id)
				.getGoods();

		for (GoodsEntity goods : goodsFromCategory) {
			MyJlable myJlable = new MyJlable(goods.getId(), goods.getName());
			myJlable.setGoods(true);
			myJlable.setText(goods.getName());
			myJlable.addMouseListener(new LabelListener());
			listPane.add(myJlable);
		}

		Connect.closeSession();
	}

	private class Breadcrumb {
		long id;
		String name;
		String url;

		Breadcrumb(long id, String name) {
			this.id = id;
			this.name = name;

			this.url = "<a href=\"" + id + "\">" + "<font color=\"000000\">"
					+ name + "</font></a>";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Breadcrumb)) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			Breadcrumb breadcrumb = (Breadcrumb) obj;
			return new EqualsBuilder().append(id, breadcrumb.id).isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(this.id).hashCode();
		}

	}

	private class LabelListener implements MouseListener {

		public void mouseReleased(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent e) {
			MyJlable myLabel = (MyJlable) e.getComponent();
			myLabel.setText(myLabel.getName());

		}

		public void mouseEntered(MouseEvent e) {
			MyJlable myLabel = (MyJlable) e.getComponent();
			myLabel.setText(myLabel.getName());
			myLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

			myLabel.setText("<HTML><U>" + myLabel.getName() + "<U><HTML>");
		}

		public void mouseClicked(MouseEvent e) {

			MyJlable myLabel = (MyJlable) e.getComponent();
			if (myLabel.isCategory()) {
				addToBreadcrumbs(myLabel.getId(), myLabel.getName());
				listPane.removeAll();
				createLabelsForCategory(myLabel.getId());
				createLabelsForGoods(myLabel.getId());

			} else {

				if (dialog == null) {
					dialog = new JDialog(Window.this);
				}
				dialog.setContentPane(createPanelForGoods(myLabel.getId()));
				dialog.pack();
				dialog.setVisible(true);
			}
		}
	}

	private JPanel createPanelForGoods(long id) {

		ShopDao shopDao = Connect.getDao();
		GoodsEntity goodsEntity = shopDao.getGoodsById(id);
		Connect.closeSession();
		JPanel goodsPanel = new GoodsPanel(dialog, goodsEntity);
		
		return goodsPanel;
	}

	

	private void addToBreadcrumbs(long id, String categoryName) {
		StringBuilder stringBuilder = new StringBuilder();
		breadcrumbsList.add(new Breadcrumb(id, categoryName));

		for (Breadcrumb breadcrumb : breadcrumbsList) {
			stringBuilder.append(" ");
			stringBuilder.append(breadcrumb.url);
		}

		breadcrumbPanel.setText(stringBuilder.toString());
	}

	private void setToBreadcrumbsFromList() {
		StringBuilder stringBuilder = new StringBuilder();
		breadcrumbPanel.removeAll();

		for (Breadcrumb breadcrumb : breadcrumbsList) {
			stringBuilder.append(" ");
			stringBuilder.append(breadcrumb.url);
		}

		breadcrumbPanel.setText(stringBuilder.toString());
	}
}
