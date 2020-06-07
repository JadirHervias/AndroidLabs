package pe.edu.tecsup.motorizapp;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import pe.edu.tecsup.motorizapp.model.Order;

// Modificar el Android Manifest para que se use esta clase
public class MotorizApp extends Application {

    public ArrayList<Order> orderList = new ArrayList<>();
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    public void addOrder(Order order) {
        orderList.add(order);
        Log.i("MotorizApp", "Ahora hay " + orderList.size() + "elementos.");
    }

}
