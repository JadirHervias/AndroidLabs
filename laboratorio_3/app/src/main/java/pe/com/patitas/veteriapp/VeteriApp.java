package pe.com.patitas.veteriapp;

import android.app.Application;

import java.util.ArrayList;

import pe.com.patitas.veteriapp.models.Order;
import pe.com.patitas.veteriapp.models.Pet;

public class VeteriApp extends Application {
    public ArrayList<Pet> petList = new ArrayList<>();
    public ArrayList<Order> orderList = new ArrayList<>();
    public ArrayList<Pet> getPetList() {
        return petList;
    }
    public void addPet(Pet pet) {
        petList.add(pet);
    }
    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    public void addOrder(Order order) {
        orderList.add(order);
    }
}
