/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Coupons;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zerox
 */
@Stateless
public class CouponsFacade extends AbstractFacade<Coupons> implements CouponsFacadeLocal {

  @PersistenceContext(unitName = "ASStore-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CouponsFacade() {
    super(Coupons.class);
  }
  
  public Coupons findByCoupon(String coupon) {
    Query query = em.createNamedQuery("Coupons.findByCoupon");
    query.setParameter("coupon", coupon);
    try {
      return (Coupons)query.getSingleResult();
    } catch (Exception e) {
      return null;
    }
  }
}
