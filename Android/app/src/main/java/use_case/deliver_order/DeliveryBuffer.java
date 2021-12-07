package use_case.deliver_order;

import entity.order.Order;

import java.util.ArrayDeque;
import java.util.Queue;

public class DeliveryBuffer {
    /**
     * The buffer queue of completed orders for delivery staff to access.
     */
    public static Queue<Order> deliveryBuffer = new ArrayDeque<>();

    /**
     * Reset the delivery buffer
     */
    public static void reset() {
        deliveryBuffer = new ArrayDeque<>();
    }

    /**
     * The getter method for UseCase.com.example.androidgui.DeliveryStaff to call to get the next order to delivery.
     *
     * @return an entities.Order instance to be delivered.
     */
    public static Order getDeliveryOrder() throws Exception {
        Order deliveryOrder;
        deliveryOrder = deliveryBuffer.poll();
        if (deliveryOrder == null) {
            throw new Exception("No delivery order to be delivered");
        }
        return deliveryOrder;
    }

    /**
     * Add an order to the delivery queue.
     *
     * @param order the order to be added to the queue.
     */
    public static void addDeliveryOrder(Order order) {
        deliveryBuffer.add(order);
    }
}
