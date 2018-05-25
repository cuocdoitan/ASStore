/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Cart;
import Models.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tien Phat
 */
@Stateless
public class CartFacade extends AbstractFacade<Cart> implements CartFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartFacade() {
        super(Cart.class);
    }
    
    public Cart findByUserId(int id) {
      TypedQuery query = em.createQuery("select c from Cart c where c.usersId.id = ?1 and c.usersId.enabled = ?2", Cart.class);
      query.setParameter(1, id);
      query.setParameter(2, true);
      List<Cart> list = query.getResultList();
      if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }
}
