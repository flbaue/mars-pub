/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se1p.t1;

import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author florianbauer
 */
public class Customer {

    private OneToMany<Order> orders;
    private String firstName;
    private String lastName;
    private final Date birthDate;
    
    private Customer() {this.birthDate = new Date();};
    
    public Customer(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.orders = new OneToMany();
    }
    
    public OneToManyInterface<OrderROI> getOrders() {
        OneToManyInterface<OrderROI> result = new OneToMany<OrderROI>();
        result.addAll(this.orders);
        return result;
        
    }
    
    public OneToManyInterface<Order> getOrdersUnsafe() {
        return orders;
    } 
    
    public boolean addOrder(Order order) {
        return orders.add(order);
    }
    
    public OrderROI getOrderById(int id) {
        Order result = null;
        Iterator<Order> it = orders.iterator();

        while (it.hasNext()) {
            Order order = it.next();

            if (order.getID() == id) {
                result = order;
                break;
            }
        }
        return result;
    }
}
