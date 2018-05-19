/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.CartDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tien Phat
 */
@Local
public interface CartDetailFacadeLocal {

    void create(CartDetail cartDetail);

    void edit(CartDetail cartDetail);

    void remove(CartDetail cartDetail);

    CartDetail find(Object id);

    List<CartDetail> findAll();

    List<CartDetail> findRange(int[] range);
    
    List<CartDetail> findByCartId(int id);

    int count();
    
}
