package use_case;

import boundary.PlaceOrderInputBoundary;
import entity.Dish;
import entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class PlaceOrder implements PlaceOrderInputBoundary {

    /**
     * Places an order by creating copies of the dishes in the menu then adding them to a new order, then adding the
     * order to the OrderQueue
      * @param dineIn true if the dish is dineIn, false otherwise
     * @param dishNames string list of the names of dishes ordered
     * @param location the table number or delivery information of the order
     * @throws Exception
     */
    public void placeOrder(boolean dineIn, String[] dishNames, String location) throws Exception{
        HashMap<String, List<Dish>> dishes = new HashMap<String, List<Dish>>();


        for (String dishName: dishNames) {
            Dish dishCopy;
            dishCopy = generateDishCopy(dishName, location);


            List<Dish> dishCopyAsList;
            if (!dishes.containsKey(dishName)) {
                dishCopyAsList = new ArrayList<>(Arrays.asList(dishCopy));
                dishes.put(dishName, dishCopyAsList);
            }
            else {
                dishCopyAsList = dishes.get(dishName);
                dishCopyAsList.add(dishCopy);
                dishes.replace(dishName, dishCopyAsList);
            }

        }

        Order order = new Order(location, dishes);
        OrderQueue.addOrder(order);
    }

    // Lookup the dish in the DishList/Menu then create a copy of that dish
    public Dish generateDishCopy(String dishName, String location) {
        double price = DishList.getDishPrice(dishName);
        HashMap<String, Double> ingredients = DishList.getDishIngredients(dishName);
        double calories = DishList.getDishCalories(dishName);
        String category = DishList.getDishCategory(dishName);


        Dish dishCopy = new Dish(dishName, price, ingredients, calories, category);

        try{
            int tableNum = Integer.valueOf(location);
            dishCopy.setTableNum(tableNum);
        }
        catch (NumberFormatException ignored){
        }
        return dishCopy;
    }

}
