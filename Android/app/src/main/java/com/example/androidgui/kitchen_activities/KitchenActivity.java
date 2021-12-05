package com.example.androidgui.kitchen_activities;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.androidgui.R;
import constant.order_system.OrderType;
import entity.inventory.HasExpiryDate;
import entity.inventory.HasFreshness;
import entity.inventory.Inventory;
import entity.order_list.Dish;
import entity.order_list.Order;
import presenter.kitchen_system.KitchenFacade;
import presenter.kitchen_system.KitchenPresenter;
import presenter.kitchen_system.KitchenView;
import use_case.dish_list.DishList;
import use_case.kitchen.InventoryList;
import use_case.kitchen.Kitchen;
import use_case.kitchen.OrderQueue;
import use_case.placeorder.PlaceOrder;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Kitchen activity class.
 */
public class KitchenActivity extends AppCompatActivity implements KitchenView, PropertyChangeListener {

    // TODO: improve the design here, try to make it not public.
    public static final KitchenFacade kf = new KitchenFacade();
    private KitchenPresenter kp;
    private KitchenAdapter adapter;

    /**
     * Activity basic function.
     *
     * @param savedInstanceState onCreate fixed parameter.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
//        ListView list = findViewById(R.id.dishToCook);
//
//
//        // Initialization below, to be deleted when everything works
//        PlaceOrder po = new PlaceOrder();
//        new DishList("menu.ser", this);
//        new InventoryList("inventory.ser", this);
//        try {
//            po.placeOrder(OrderType.DINE_IN, new String[]{"Donut sandwich", "Cheetos sandwich"}, "3");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        kp = new KitchenPresenter(this);
//        kp.checkOrderAvailable();
//
//        ArrayList<String[]> displayDishes = kp.exportDishes();
//        adapter = new KitchenAdapter(this, R.layout.cook_dish_layout, displayDishes, kp);
//        list.setAdapter(adapter);


    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        kp.checkOrderAvailable();
        System.out.println("Notication: New order has arrived!");
        // TODO: add Toast that display notification.
    }
}