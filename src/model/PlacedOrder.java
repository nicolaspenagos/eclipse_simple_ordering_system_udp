/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model;

import java.util.GregorianCalendar;

import processing.core.PImage;

public class PlacedOrder {
	
	// -------------------------------------
    // Atributtes
    // -------------------------------------
	private Order order;
	private int port;
	private String ip;
	private PImage image;
	private GregorianCalendar date;
	private int x;
	private int y;
	
	// -------------------------------------
    // Constructors
    // -------------------------------------
	public PlacedOrder(Order order, int port, String ip) {
		
		this.order = order;
		this.port = port;
		this.ip = ip;
		this.x = 50;
		
	}
	
	// -------------------------------------
    // Methods 
    // -------------------------------------
	public void updatePos(int i) {
		this.y = 210 + (90*i);
	}
	
	// -------------------------------------
    // Getters and setters
    // -------------------------------------
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public PImage getImage() {
		return image;
	}

	public void setImage(PImage image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
}
