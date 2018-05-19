/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tien Phat
 */
@Local
public interface OrdersFacadeLocal {

    Orders createOrder(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);
    
    void delete(int id);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);
    
    void validate(int id);

    boolean isCancelAble(int id);
    
    int count();
    
}
