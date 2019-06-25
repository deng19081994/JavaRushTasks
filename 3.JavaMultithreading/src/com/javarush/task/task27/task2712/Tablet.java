package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        Order order = null;
        try {
            order = new Order(this);

            if (order.isEmpty()) {
                return null;
            }

            createOrderHelper(order);

        } catch (IOException logged) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        return order;
    }

    public void createTestOrder() {
        TestOrder testOrder = null;
        try {
            testOrder = new TestOrder(this);

            if (testOrder.isEmpty()) {
                return;
            }

            createOrderHelper(testOrder);

        } catch (IOException logged) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the testOrder " + testOrder);
        }
    }

    private void createOrderHelper(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        setChanged();
        notifyObservers(order);

        AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime() * 60);
        manager.processVideos();
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number +
                '}';
    }
}
