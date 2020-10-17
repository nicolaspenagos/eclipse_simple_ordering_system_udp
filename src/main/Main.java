/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.google.gson.Gson;

import events.OnMessageListener;
import model.Confirmation;
import model.Generic;
import model.Order;
import model.PlacedOrder;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements OnMessageListener {

	// -------------------------------------
	// Global assets
	// -------------------------------------
	private UDPConnection udp;
	private ArrayList<PlacedOrder> placedOrders;
	private PImage background, background1, product1, product2, product3, product4;
	private int maxStack;

	// -------------------------------------
	// Main method
	// -------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");

	}

	// -------------------------------------
	// Processing methods
	// -------------------------------------
	public void settings() {

		size(500, 720);

	}

	public void setup() {

		udp = new UDPConnection();
		udp.setObserver(this);
		udp.start();

		placedOrders = new ArrayList<PlacedOrder>();
		maxStack = 4;

		background = loadImage("images/background.png");
		background1 = loadImage("images/burger_full.png");
		product1 = loadImage("images/burger.png");
		product2 = loadImage("images/coca_cola.png");
		product3 = loadImage("images/ice_cream.png");
		product4 = loadImage("images/sandwich.png");

	}

	public void draw() {

		image(background, 0, 0);
		image(background1, 170, 180);

		fill(0);

		for (int i = 0; i < placedOrders.size(); i++) {

			PlacedOrder current = placedOrders.get(i);
			current.updatePos(i);

			textSize(14);
			text("Pedido " + (i + 1) + " :", current.getX() + 90, current.getY() + 25);
			textSize(12);
			int min = current.getDate().get(Calendar.MINUTE);
			String minutes = (min < 10) ? "0" + min : min + "";
			text("  Hora: " + current.getDate().get(Calendar.HOUR_OF_DAY) + ":" + minutes, current.getX() + 90,
					current.getY() + 45);
			image(current.getImage(), current.getX(), current.getY());

		}

		text("- Once ready, click on the image of the product -", 100, 190);

	}

	public void mouseClicked() {

		int i = -1;
		
		if (mouseX >= 50 && mouseX <= 130) {

			if (mouseY >= 210 && mouseY <= 290) {
				i = 0;
			}

			if (mouseY >= 300 && mouseY <= 380) {
				i = 1;
			}

			if (mouseY >= 390 && mouseY <= 470) {
				i = 2;
			}
			
			if (mouseY >= 480 && mouseY <= 560) {
				i = 3;
			}
			
			if (mouseY >= 570 && mouseY <= 650) {
				i = 4;
			}
			
			if(i!=-1 && i<placedOrders.size()) {
				
				PlacedOrder placedOrder = placedOrders.get(i);
				String ip = placedOrder.getIp();
				int port = placedOrder.getPort();
			
				Gson gson = new Gson();
				
				Confirmation confirmation = new Confirmation(UUID.randomUUID().toString(), "DONE", "It represents the state of the order");
				String json = gson.toJson(confirmation)+"%"+i;
				
				udp.sendMessage(json, port, ip);
				
				placedOrders.remove(i);
				
			}
			


		}

	}

	// -------------------------------------
	// UVP methods
	// -------------------------------------
	@Override
	public void onMessage(String msg) {
		// TODO Auto-generated method stub

		Gson gson = new Gson();

		String[] parts = msg.split("%");
		String json = parts[0];
		String socketAddress = parts[1];
	
		Generic generic = gson.fromJson(json, Generic.class);
		
		switch (generic.type) {

		case "Order":

			parts = socketAddress.split(":");

			String ip = parts[0].substring(1);
			int port = Integer.parseInt(parts[1]);

			if (placedOrders.size() <= maxStack) {

				Order order = gson.fromJson(json, Order.class);
				PlacedOrder current = new PlacedOrder(order, port, ip);

				if (order.getProduct().equals("BURGER")) {
					current.setImage(product1);
				} else if (order.getProduct().equals("COCA_COLA")) {
					current.setImage(product2);
				} else if (order.getProduct().equals("ICE_CREAM")) {
					current.setImage(product3);
				} else {
					current.setImage(product4);
				}

				current.setDate((GregorianCalendar) order.getDate());

				placedOrders.add(current);

			} else {

				Confirmation confirmation = new Confirmation(UUID.randomUUID().toString(), "FULL_STACK",
						"Describes the order status");
				json = gson.toJson(confirmation);
				udp.sendMessage(json+"%-1", port, ip);

			}

			break;

		case "Confirmation":
			break;

		}

	}

}
