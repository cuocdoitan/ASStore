/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.BankCard;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zerox
 */
@Stateless
public class BankCardFacade extends AbstractFacade<BankCard> implements BankCardFacadeLocal {

  @PersistenceContext(unitName = "ASStore-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public BankCardFacade() {
    super(BankCard.class);
  }
  
  public boolean isCorrectCardNumber(String number, String security, String expiryDate) {
    Query query = em.createNamedQuery("BankCard.findByNumber");
    query.setParameter("number", number);
    try {
      BankCard card = (BankCard)query.getSingleResult();
      if (card.getSecurityCode().equals(security) && card.getExpiryDate().equals(expiryDate)) {
        return true;
      }
      else {
        System.out.println(card.getExpiryDate());
        System.out.println(expiryDate);
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  
  public boolean hasEnoughMoney(String number, BigDecimal money) {
    Query query = em.createNamedQuery("BankCard.findByNumber");
    query.setParameter("number", number);
    try {
      BankCard card = (BankCard)query.getSingleResult();
      if (card.getBalance().compareTo(money) < 0) {
        return false;
      }
      else {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  
  public boolean pay(String number, BigDecimal money) {
    Query query = em.createNamedQuery("BankCard.findByNumber");
    query.setParameter("number", number);
    try {
      BankCard card = (BankCard)query.getSingleResult();
      card.setBalance(card.getBalance().subtract(money));
      em.merge(card);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
