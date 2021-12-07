package use_case.user_list;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import constant.file_system.FileName;
import constant.manager_system.UserType;
import entity.customer.Customer;
import entity.delivery.DeliveryStaff;
import entity.delivery.ServingStaff;
import entity.inventory.InventoryStaff;
import entity.kitchen.KitchenStaff;
import entity.manager.Manager;
import entity.user.User;
import gateway.GCloudReadWriter;
import gateway.ReadWriter;
import presenter.manager_system.UserOutputBoundary;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * Public class storing information for all users using a Hashmap.
 */

public class UserList implements Serializable {

    /**
     * Private instances used in the class.
     */
    public static HashMap<String, User> users;
    private static final long serialVersionUID = 1L;
    private static ReadWriter readWriter;
    private static String filename;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private UserOutputBoundary userOutputBoundary;


    /**
     * Constructor
     */
    public UserList() {
        if (users == null) {
            users = new HashMap<>();
        }
    }

    /**
     * Resets the user list for testing
     */
    public void reset() {
        users = new HashMap<>();
    }

    /**
     * Return number of items in the user list
     *
     * @return the length of the user list.
     */
    public int length() {
        return users.size();
    }


    /**
     * Add user to this user list.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        users.put(user.getId(), user);
        saveToFile();
    }


    /**
     * Return user by its id
     *
     * @return a Hashmap with users' id mapping with users
     */
    public static User getUserByUserId(String id) {
        return users.get(id);
    }

    /**
     * Return user type by its id
     *
     * @return a Hashmap with users' id mapping with users
     */
    public static UserType getUserTypeById(String id) {
        User currentUser = getUserByUserId(id);
        if (currentUser instanceof Customer)
            return UserType.CUSTOMER;
        else if (currentUser instanceof Manager)
            return UserType.MANAGER;
        else if (currentUser instanceof ServingStaff)
            return UserType.SERVING_STAFF;
        else if (currentUser instanceof DeliveryStaff)
            return UserType.DELIVERY_STAFF;
        else if (currentUser instanceof InventoryStaff)
            return UserType.INVENTORY_STAFF;
        else if (currentUser instanceof KitchenStaff)
            return UserType.KITCHEN;
        return null;
    }

    /**
     * Return all users as a map.
     *
     * @return a UserList contains
     */
    public HashMap<String, User> getUsers() {
        return users;
    }

    /**
     * String representation for user list.
     *
     * @return a string representation including all users.
     */
    @Override
    @NonNull
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String userId : users.keySet()) {
            builder.append(UserList.getUserByUserId(userId));
        }
        return builder.toString();
    }

    /**
     * Save the user list to server
     */
    public void saveToFile() {
        if (filename != null) {
            readWriter.saveToFile(context, filename, users);
        }
    }

    /**
     * method to add staffs.
     *
     * @param id       id of the new staff.
     * @param name     name  of the new staff.
     * @param password password of the new staff.
     * @param userType type of the new staff.
     */
    public void addStaff(String id, String name, String password, UserType userType) {
        switch (userType) {
            case KITCHEN:
                users.put(id, new KitchenStaff(id, name, password));
                break;
            case SERVING_STAFF:
                users.put(id, new ServingStaff(id, name, password));
                break;
            case DELIVERY_STAFF:
                users.put(id, new DeliveryStaff(id, name, password));
                break;
            case INVENTORY_STAFF:
                users.put(id, new InventoryStaff(id, name, password));
                break;
        }
        saveToFile();
    }


    /**
     * Setting context
     *
     * @param context context
     */
    public static void setContext(Context context) {
        UserList.context = context;
    }


    /**
     * Set initial data for user list
     *
     * @param filename file name of the user list
     */
    @SuppressWarnings("unchecked")
    public static void setData(String filename) {
        UserList.filename = filename;

        if (users == null || users.isEmpty()) {
            readWriter = new GCloudReadWriter();
            users = (HashMap<String, User>) readWriter.readFromFile(FileName.USER_FILE);
        }
    }

    /**
     * Update users in the users view
     */
    public void usersString() {
        userOutputBoundary.updateUserItemsDisplay(this.nonCustomerUsersToString());
    }

    /**
     * String representation for user with id, password, name, and type.
     *
     * @return string representation
     */
    private String nonCustomerUsersToString() {
        StringBuilder builder = new StringBuilder();
        if (users != null) {
            for (String userId : users.keySet()) {
                if (UserList.getUserTypeById(userId) != UserType.CUSTOMER) {
                    User user = UserList.getUserByUserId(userId);
                    builder.append("ID: ").append(user.getId())
                            .append("\nPassword: ").append(user.getPassword())
                            .append("\nName: ").append(user.getName())
                            .append("\nType: ").append(Objects.requireNonNull(UserList.getUserTypeById(userId)))
                            .append(("\n\n"));
                }
            }
        }
        return builder.toString();
    }

    /**
     * Set the userOutputBoundary of this class
     *
     * @param outputBoundary the user output boundary
     */
    public void setUserOutputBoundary(UserOutputBoundary outputBoundary) {
        this.userOutputBoundary = outputBoundary;
    }
}