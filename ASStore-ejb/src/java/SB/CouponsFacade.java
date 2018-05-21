/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Coupons;
import Models.Product;
import Models.Users;
import java.util.List;
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
    query.setParameter("enabled", true);
    try {
      return (Coupons)query.getSingleResult();
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<Coupons> findEnableCoupons() {
    Query query = em.createNamedQuery("Coupons.findByEnabled");
    query.setParameter("enabled", true);
    try {
      return (List<Coupons>)query.getResultList();
    } catch (Exception e) {
      return null;
    }
  }
  
  public List<Coupons> findEnableCouponsOfUser(int user) {
    Query query = em.createNamedQuery("Coupons.findByUserIdAndEnabled");
    query.setParameter("userId", user);
    query.setParameter("enabled", true);
    try {
      return (List<Coupons>)query.getResultList();
    } catch (Exception e) {
      return null;
    }
  }
  
  public Coupons findByProduct(Product product) {
    Query query = em.createNamedQuery("Coupons.findByProduct");
    query.setParameter("product", product);
    query.setParameter("enabled", true);
    try {
      return (Coupons)query.getSingleResult();
    } catch (Exception e) {
      return null;
    }
  }
}
