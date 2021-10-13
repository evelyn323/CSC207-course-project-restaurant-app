
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Public class storing all dishes information using an ArrayList.
 *
 * @author Chan Yu & Naihe Xiao
 */

public class DishList {
    private HashMap<String, Dish> menu;
    /* one list may be better? */

    /**
     * This constructor is using the generateDishList method below which hardcoded the dishes in program.
     */
    public DishList() {
        this.menu = this.generateDishList();
    }

    public HashMap<String, Dish> getMenu() {
        return menu;
    }

    /**
     * This method is used to generate a list of dishes that used to display to customers/managers.
     * //TODO Later, should be removed since the data should come from DB/external source.
     */
    public HashMap<String, Dish> generateDishList(){
        HashMap<String, Dish> menu = new HashMap<>();
        menu.put("Quarter pound with cheese", new Dish("Quarter pound with cheese", 10.0, new String[]{""}, 200 ));
        menu.put("Quarter pound with 2 cheese", new Dish("Quarter pound with 2 cheese", 10.0, new String[]{""}, 400 ));
        menu.put("Quarter pound with 3 cheese", new Dish("Quarter pound with 3 cheese", 10.0, new String[]{""}, 500 ));
        menu.put("Quarter pound with 4 cheese", new Dish("Quarter pound with 4 cheese", 10.0, new String[]{""}, 600 ));
        menu.put("Quarter pound with 5 cheese", new Dish("Quarter pound with 5 cheese", 10.0, new String[]{""}, 700 ));
        menu.put("Small fries", new Dish("Small fries", 10.0, new String[]{""}, 200 ));
        menu.put("Median fries", new Dish("Median fries", 10.0, new String[]{""}, 250 ));
        menu.put("Large fries", new Dish("Large fries", 10.0, new String[]{""}, 350 ));
        menu.put("Coke", new Dish("Coke", 10.0, new String[]{""}, 180 ));
        menu.put("Coffee", new Dish("Coffee", 10.0, new String[]{""}, 0 ));
        menu.put("Tea", new Dish("Tea", 10.0, new String[]{""}, 0 ));
        menu.put("Milk Tea", new Dish("Milk Tea", 10.0, new String[]{""}, 300 ));
        menu.put("Wine", new Dish("Wine", 10.0, new String[]{""}, 100 ));
        return menu;
    }


}
