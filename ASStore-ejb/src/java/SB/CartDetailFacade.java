/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Cart;
import Models.CartDetail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tien Phat
 */
@Stateless
public class CartDetailFacade extends AbstractFacade<CartDetail> implements CartDetailFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartDetailFacade() {
        super(CartDetail.class);
    }
    
    public List<CartDetail> findByCartId(int id) {
      Query query = em.createNamedQuery("CartDetail.findByCartId");
      query.setParameter("id", new Cart(id));
      return query.getResultList();
    }
}
