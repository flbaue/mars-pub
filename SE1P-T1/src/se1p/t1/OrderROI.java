/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se1p.t1;

/**
 *
 * @author florianbauer
 */
public interface OrderROI {
    
    int getID();
    Customer getCustomer();
    OrderStatus getStatus();
    OrderData getOrderData();
}
