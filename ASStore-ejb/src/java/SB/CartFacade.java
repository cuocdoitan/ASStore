/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Cart;
import Models.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
      Query query = em.createNamedQuery("Cart.findByUserId");
      query.setParameter("id", new Users(id));
      try {
        return (Cart)query.getSingleResult();
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
}
