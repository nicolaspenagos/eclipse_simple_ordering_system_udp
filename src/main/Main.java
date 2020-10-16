/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package main;

import java.util.ArrayList;

import com.google.gson.Gson;

import events.OnMessageListener;
import model.Generic;
import model.Order;
import model.PlacedOrder;
import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener {

	// -------------------------------------
	// Global assets
	// -------------------------------------
	private UDPConnection udp;
	private ArrayList<PlacedOrder> placedOrders;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");
		
	}

	public void settings() {

		size(800, 500);

	}

	public void setup() {

		udp = new UDPConnection();
		udp.setObserver(this);
		udp.start();
		
		placedOrders = new ArrayList<PlacedOrder>();

	}

	public void draw() {
		
		background(255);
		fill(0);
		textSize(30);
		text("Orders:", 350, 100);
		
		fill(0);
		rect(50,120,80, 60);
		

	}

	@Override
	public void onMessage(String msg) {
		// TODO Auto-generated method stub
		
		Gson gson = new Gson();
		
		String[] parts = msg.split("%");
		String json = parts[0];
		String socketAddress = parts[1];
		System.out.println(json);
		
		Generic generic = gson.fromJson(json, Generic.class);
		System.out.println(generic.type);
		
		switch(generic.type){
			
			case "Order":
				
				Order order = gson.fromJson(json, Order.class);
				parts = socketAddress.split(":");
				
				String ip = parts[0].substring(1);
				int port = Integer.parseInt(parts[1]);
				
				PlacedOrder current = new PlacedOrder(order, port, ip);
				placedOrders.add(current);
				
				System.out.println(current.getOrder().getProduct());
				System.out.println(current.getIp());
				System.out.println(current.getIp());
				System.out.println();
				
				break;
				
			case "Confirmation":
				break;
		
		}
		

	}

}
