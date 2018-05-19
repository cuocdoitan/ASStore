/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zerox
 */
@Entity
@Table(name = "BankCard")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "BankCard.findAll", query = "SELECT b FROM BankCard b")
  , @NamedQuery(name = "BankCard.findById", query = "SELECT b FROM BankCard b WHERE b.id = :id")
  , @NamedQuery(name = "BankCard.findByNumber", query = "SELECT b FROM BankCard b WHERE b.number = :number")
  , @NamedQuery(name = "BankCard.findBySecurityCode", query = "SELECT b FROM BankCard b WHERE b.securityCode = :securityCode")
  , @NamedQuery(name = "BankCard.findByExpiryDate", query = "SELECT b FROM BankCard b WHERE b.expiryDate = :expiryDate")
  , @NamedQuery(name = "BankCard.findByBalance", query = "SELECT b FROM BankCard b WHERE b.balance = :balance")})
public class BankCard implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "Id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "Number")
  private String number;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "SecurityCode")
  private String securityCode;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "ExpiryDate")
  private String expiryDate;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "Balance")
  private BigDecimal balance;
  @JoinColumn(name = "UserId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Users userId;

  public BankCard() {
  }

  public BankCard(Integer id) {
    this.id = id;
  }

  public BankCard(Integer id, String number, String securityCode, String expiryDate, BigDecimal balance) {
    this.id = id;
    this.number = number;
    this.securityCode = securityCode;
    this.expiryDate = expiryDate;
    this.balance = balance;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getSecurityCode() {
    return securityCode;
  }

  public void setSecurityCode(String securityCode) {
    this.securityCode = securityCode;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Users getUserId() {
    return userId;
  }

  public void setUserId(Users userId) {
    this.userId = userId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof BankCard)) {
      return false;
    }
    BankCard other = (BankCard) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Models.BankCard[ id=" + id + " ]";
  }
  
}
