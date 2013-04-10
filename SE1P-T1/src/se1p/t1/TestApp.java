/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se1p.t1;

import java.util.Date;

/**
 *
 * @author florianbauer
 */
public class TestApp {
    
    public static void main (String[] args) {
        
        
        Customer bob = new Customer("Silent", "Bob", new Date());
        
        Order orderOne = new Order(bob, new OrderData());
        Order orderTwo = new Order(bob, new OrderData());
        Order orderThree = new Order(bob, new OrderData());
        
        bob.addOrder(orderOne);
        bob.addOrder(orderTwo);
        
        
        //-------------- SAFE ----------------
        
        OneToManyInterface<OrderROI> orders = bob.getOrders();
        orders.add(orderThree);
        
        System.out.println("getOrders safe:");
        System.out.println(bob.getOrderById(orderTwo.getID()));
        System.out.println(bob.getOrderById(orderThree.getID()));
        
        //Order orderUnsafe = orders.iterator().next(); //Compile Error!
        OrderROI orderSafe = orders.iterator().next();      
        //orderSafe.changeStatus(OrderStatus.STATUS2); //Compile Error!
        
        
        
        //-------------- UNSAFE ----------------
        
        OneToManyInterface<Order> ordersUnsafe = bob.getOrdersUnsafe();
        ordersUnsafe.add(orderThree);
        
        System.out.println("\ngetOrders unsafe:");
        System.out.println(bob.getOrderById(orderTwo.getID()));
        System.out.println(bob.getOrderById(orderThree.getID()));
        
        Order orderUnsafe = ordersUnsafe.iterator().next(); 
        //orderSafe = ordersUnsafe.iterator().next(); 
        orderUnsafe.changeStatus(OrderStatus.STATUS2); 
        
        
    }
}
