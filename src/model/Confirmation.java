/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package model;

public class Confirmation {

    private String type = "Confirmation";

    // -------------------------------------
    // Atributtes
    // -------------------------------------
    private String id;
    private String confirmationMessage;
    private String description;

    // -------------------------------------
    // Constructors
    // -------------------------------------
    public Confirmation(){

    }

    public Confirmation(String id, String confirmationMessage, String description) {

        this.id = id;
        this.confirmationMessage = confirmationMessage;
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

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
