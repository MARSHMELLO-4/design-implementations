package factories;

import java.util.List;

import models.DeliveryOrder;
import models.Order;
import models.PickupOrder;
import utils.TimeUtils;

public class NowOrderFactory implements OrderFactory {

    public models.Order createOrder(models.User user, models.Cart cart, models.Restaurant restaurant,
            List<models.MenuItem> menuItems, strategies.PaymentStrategy paymentStrategy, double totalCost,
            String orderType) {

        Order order = null;

        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else {
            PickupOrder pickupOrder = new PickupOrder();
            pickupOrder.setRestaurant(restaurant.getLocation());
            order = pickupOrder;
        }

        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setItems(menuItems);
        order.setPaymentStrategy(paymentStrategy);
        order.setScheduled(TimeUtils.getCurrentTime());
        order.setTotal(totalCost);
        return order;

    }

}
