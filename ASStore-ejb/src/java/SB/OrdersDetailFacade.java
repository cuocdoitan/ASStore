/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Orders;
import Models.OrdersDetail;
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
public class OrdersDetailFacade extends AbstractFacade<OrdersDetail> implements OrdersDetailFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersDetailFacade() {
        super(OrdersDetail.class);
    }
    
    public List<OrdersDetail> findByOrder(int id) {
      Query query = em.createNamedQuery("OrdersDetail.findByOrder");
      query.setParameter("id", new Orders(id));
      return query.getResultList();
    }
    
}
