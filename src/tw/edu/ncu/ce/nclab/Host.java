/* 
 * Copyright 2016 NCLab.
 * Released under GPLv3. See LICENSE.txt for details. 
 */

package tw.edu.ncu.ce.nclab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Host extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8860249944965920521L;
	private static Color rangeColor = Color.RED;
	private static Color hostColor = Color.BLUE;
	private int id;
	private Coord locationInWorld;

	private double range;
	private MapAreaPanel panel;

	public Host(int id, Coord location, double range, MapAreaPanel panel) {
		this.locationInWorld = location;
		this.id = id;
		this.range = range;
		this.panel = panel;
	}

	public int getID() {
		return id;
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double coverage;
		coverage = new Ellipse2D.Double(0, 0, panel.toPanelX(range * 2),
				panel.toPanelY(range * 2));

		// draw the "range" circle
		g2.setColor(rangeColor);
		g2.draw(coverage);

		g2.setColor(hostColor);
		g2.drawRect(panel.toPanelX(range - 1), panel.toPanelY(range - 1), 2, 2);
		g2.drawString("N" + id, panel.toPanelX(range), panel.toPanelY(range));

	}

	public double getRange() {
		return this.range;
	}

	public Coord getLocationInWorld() {
		return locationInWorld;
	}

	public void setLocationInWorld(Coord newLocation) {
		this.locationInWorld = newLocation;
	}

	@Override
	public String toString() {
		return this.id + " " + locationInWorld.getX() + " "
				+ locationInWorld.getY();
	}

}
