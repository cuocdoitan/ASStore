/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Orders;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }
    
    public void delete(int id) {
      Orders order = em.find(Orders.class, id);
      order.setEnabled(false);
      em.merge(order);
    }
    
    public void validate(int id) {
      Orders order = em.find(Orders.class, id);
      order.setStatus(true);
      em.merge(order);
    }
    
    public boolean isCancelAble(int id) {
      Orders order = em.find(Orders.class, id);
      Format formatter = new SimpleDateFormat("dd / MM / yyyy");
      try {
        return !formatter.format(new Date()).equals(order.getCreateAt());
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
    }
    @Override
    public List<Orders> findAll() {
      Query query = em.createNamedQuery("Orders.findAll");
      return query.getResultList();
    }
    
    public Orders createOrder(Orders order) {
      try {
        em.persist(order);
        return order;
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
      
    }
}
