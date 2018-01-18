/* 
 * Copyright 2016 NCLab.
 * Released under GPLv3. See LICENSE.txt for details. 
 */
package tw.edu.ncu.ce.nclab;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import core.Coord;

public class MapAreaPanel extends JPanel implements MouseListener,
		MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -406994921827558450L;
	public static final double world_width = 1000;
	public static final double world_height = 1000;

	private StaticNodeGeneratorGUI mainFrame;

	public static final Color DIVIDER_COLOR = new Color(182, 182, 182);
	
	public static final int BOX = 54;

	private Host m_selectedComponent = null;

	int x0;
	int y0;

	public MapAreaPanel(StaticNodeGeneratorGUI mainFrame) {

		this.mainFrame = mainFrame;
		setBorder(BorderFactory.createLineBorder(DIVIDER_COLOR));

		addMouseListener(this);

	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	@Override
	public void mouseDragged(MouseEvent evt) {

		m_selectedComponent.setBounds(evt.getX() + x0, evt.getY() + y0,
				toPanelX(m_selectedComponent.getRange()) * 2,
				toPanelY(m_selectedComponent.getRange()) * 2);

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent evt) {

		Host mSeletced = mainFrame.getSeletedHost();
		if (mSeletced == null) {

			return;
		}

		if (contains(evt.getX(), evt.getY())) {

			mSeletced.setBounds(evt.getX() - toPanelX(mSeletced.getRange()),
					evt.getY() - toPanelY(mSeletced.getRange()),
					toPanelX(mSeletced.getRange()) * 2,
					toPanelY(mSeletced.getRange()) * 2);
			
			
			double wX = toRealWorldX(evt.getX());
			double wY = toRealWorldY(evt.getY());

			mSeletced.setLocationInWorld(new Coord(wX, wY));


			this.add(mSeletced, -1);
			this.validate();
			this.repaint(mSeletced.getBounds());

		}
		mainFrame.setSeletedHost(null);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent evt) {
		Component c = getComponentAt(evt.getPoint());
		if (c instanceof Host) {
			m_selectedComponent = (Host) c;
			x0 = m_selectedComponent.getX() - evt.getX();
			y0 = m_selectedComponent.getY() - evt.getY();

			setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			addMouseMotionListener(this);
			m_selectedComponent.repaint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		if (m_selectedComponent != null) {
			removeMouseMotionListener(this);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			m_selectedComponent.setBounds(evt.getX() + x0, evt.getY() + y0,
					toPanelX(m_selectedComponent.getRange()) * 2,
					toPanelY(m_selectedComponent.getRange()) * 2);

			double wX = toRealWorldX(evt.getX() + x0)
					+ m_selectedComponent.getRange();
			double wY = toRealWorldY(evt.getY() + y0)
					+ m_selectedComponent.getRange();

			m_selectedComponent.setLocationInWorld(new Coord(wX, wY));
			//System.out.println(m_selectedComponent);

			m_selectedComponent.repaint();
			m_selectedComponent = null;
		}

	}

	public int toRealWorldX(double value) {
		double width = getSize().getWidth();

		double scale = world_width / width;
		return (int) Math.round(scale * value);
	}

	public int toRealWorldY(double value) {
		double height = getSize().getHeight();

		double scale = world_height / height;
		return (int) Math.round(scale * value);
	}

	public int toPanelX(double value) {
		double width = getSize().getWidth();

		double scale = width / world_width;
		return (int) Math.round(scale * value);
	}

	public int toPanelY(double value) {
		double height = getSize().getHeight();

		double scale = height / world_height;
		return (int) Math.round(scale * value);
	}

}
