/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.BankCard;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zerox
 */
@Local
public interface BankCardFacadeLocal {

  void create(BankCard bankCard);

  void edit(BankCard bankCard);

  void remove(BankCard bankCard);

  BankCard find(Object id);

  List<BankCard> findAll();

  List<BankCard> findRange(int[] range);

  int count();
  
  boolean isCorrectCardNumber(String number, String security, String expiryDate);
  
  boolean hasEnoughMoney(String number, BigDecimal money);
  
  boolean pay(String number, BigDecimal money);
}
