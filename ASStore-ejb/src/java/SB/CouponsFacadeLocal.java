/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Coupons;
import Models.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zerox
 */
@Local
public interface CouponsFacadeLocal {

  void create(Coupons coupons);

  void edit(Coupons coupons);

  void remove(Coupons coupons);

  Coupons find(Object id);
  
  Coupons findByCoupon(String coupon);
  
  Coupons findByProduct(Product product);
  
  List<Coupons> findEnableCoupons();
  
  List<Coupons> findEnableCouponsOfUser(int user);

  List<Coupons> findAll();

  List<Coupons> findRange(int[] range);

  int count();
  
}
