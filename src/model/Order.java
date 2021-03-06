/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model;

import java.util.Calendar;
import java.util.Date;

public class Order {

    private String type = "Order";

    // -------------------------------------
    // Atributtes
    // -------------------------------------
    private String id;
    private String product;
    private Calendar date;
    private String description;

    // -------------------------------------
    // Constructors
    // -------------------------------------
    public Order(){

    }

    public Order(String id, String product, Calendar date, String description) {

        this.id = id;
        this.product = product;
        this.date = date;
        this.description = description;

    }

    // -------------------------------------
    // Getters and setters
    // -------------------------------------
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
