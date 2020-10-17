/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package main;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import events.OnMessageListener;

public class UDPConnection extends Thread {

    // -------------------------------------
    // Global assets
    // -------------------------------------
    private DatagramSocket socket;
    private OnMessageListener observer;
    private boolean kill;

    // -------------------------------------
    // UDP Methods
    // -------------------------------------
    @Override
    public void run(){

        new Thread(

                ()->{

                    try {

                        socket = new DatagramSocket(5000);

                        kill = false;
                        while(!kill){

                            byte[] buffer = new byte[1024];

                            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                            socket.receive(packet);
                            
                            String socketAddress = packet.getSocketAddress().toString();
                            String json = new String(packet.getData()).trim();
                            
                            observer.onMessage(json+"%"+socketAddress);

                        }


                    } catch (SocketException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        ).start();

    }

    public void sendMessage(String msg, int port, String ip){

        new Thread(

                ()->{

                    try {

                        InetAddress iAIp = InetAddress.getByName(ip);
                        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, iAIp, port);
                        socket.send(packet);

                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

        ).start();
    }

    // -------------------------------------
    // Getters and setters
    // -------------------------------------
    public boolean isKill() {
        return kill;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    public OnMessageListener getObserver() {
        return observer;
    }

    public void setObserver(OnMessageListener observer) {
        this.observer = observer;
    }

}
