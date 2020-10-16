/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model;

public class PlacedOrder {
	
	// -------------------------------------
    // Atributtes
    // -------------------------------------
	private Order order;
	private int port;
	private String ip;
	
	// -------------------------------------
    // Constructors
    // -------------------------------------
	public PlacedOrder(Order order, int port, String ip) {
		
		this.order = order;
		this.port = port;
		this.ip = ip;
		
	}
	
	// -------------------------------------
    // Constructors
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
	
}
