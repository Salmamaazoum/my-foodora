package appSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import user.Courier;
import user.Customer;
import user.Manager;
import user.User;
import user.UserType;

public class AppSystem {
    /**
     * Singleton instance of the Application
     */
    private static AppSystem instance = null;
    private static List<Manager> managers;
    private static List<Customer> customers;
    private static List<Courier> couriers;
    private static List<Restaurant> restaurants;
    private static Optional<User> currentUser;
    private static Optional<UserType> currentUserType;

    public List<Manager> getManagers() {
        return managers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public Optional<User> getCurrentUser() {
        return currentUser;
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    private AppSystem() {

        managers = new ArrayList<>();
        customers = new ArrayList<>();
        couriers = new ArrayList<>();

        // Add default managers
        Manager salma = new Manager("Salma", "Salma", "1234", "Salma");
        Manager hala = new Manager("Hala", "Hala", "1234", "Salma");

        managers.add(salma);
        managers.add(hala);
        currentUser = Optional.empty();
        currentUserType = Optional.empty();

    }

    public static AppSystem getInstance() {
        if (instance == null) {
            instance = new AppSystem();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        if (tryLogin(managers, username, password, UserType.MANAGER) ||
                tryLogin(customers, username, password, UserType.CUSTOMER) ||
                tryLogin(couriers, username, password, UserType.COURIER)) {
            return true; // Login successful
        }
        return false; // Login failed
    }

    public void logout() {
        currentUserType = Optional.empty();
        currentUserType = Optional.empty();
    }

    public boolean register(User newUser) {
        if (newUser instanceof Manager) {
            return addManager((Manager) newUser);

        } else if (newUser instanceof Customer) {
            return addCustomer((Customer) newUser);

        } else if (newUser instanceof Courier) {
            return addCourier((Courier) newUser);
        }
        return false; // If user is of an unknown type
    }

    public boolean addCustomer(Customer customer) {
        customers.add(customer);
        return true;
    }

    public boolean addManager(Manager manager) {
        managers.add(manager);
        return true;
    }

    public boolean addCourier(Courier courier) {
        couriers.add(courier);
        return true;
    }

    private <T extends User> boolean tryLogin(List<T> users, String username, String password, UserType typeofUser) {
        for (T user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = Optional.of(user);
                currentUserType = Optional.of(typeofUser);
                return true;
            }
        }
        return false;
    }
}
