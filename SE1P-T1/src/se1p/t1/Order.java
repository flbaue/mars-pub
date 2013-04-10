/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se1p.t1;

/**
 *
 * @author florianbauer
 */
public class Order implements OrderROI {
    
    private static int orderCounter = 0;
    
    private final int id;
    private final Customer customer;
    private OrderStatus status;
    private OrderData orderData;
    
    private Order() {
        this.id = orderCounter++;
        this.customer = null;
        this.status = OrderStatus.STATUS1;
        this.orderData = null;
        
    }
    
    public Order(Customer customer, OrderData orderData) {
        this.id = orderCounter++;
        this.customer = customer;
        this.status = OrderStatus.STATUS1;
        this.orderData = orderData;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public OrderStatus getStatus() {
        return this.status;
    }

    @Override
    public OrderData getOrderData() {
        return this.orderData;
    }
    
    public void changeStatus(OrderStatus status) {
        
        this.status = status;
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
