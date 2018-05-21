/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Coupons")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Coupons.findAll", query = "SELECT c FROM Coupons c")
  , @NamedQuery(name = "Coupons.findById", query = "SELECT c FROM Coupons c WHERE c.id = :id")
  , @NamedQuery(name = "Coupons.findByUserId", query = "SELECT c FROM Coupons c WHERE c.userId = :userId")
  , @NamedQuery(name = "Coupons.findByUserIdAndEnabled", query = "SELECT c FROM Coupons c WHERE c.userId = :userId AND c.enabled = :enabled")
  , @NamedQuery(name = "Coupons.findByCoupon", query = "SELECT c FROM Coupons c WHERE c.coupon = :coupon AND c.enabled = :enabled")
  , @NamedQuery(name = "Coupons.findByExpireDate", query = "SELECT c FROM Coupons c WHERE c.expireDate = :expireDate")
  , @NamedQuery(name = "Coupons.findByProduct", query = "SELECT c FROM Coupons c WHERE c.productId = :product AND c.enabled = :enabled")
  , @NamedQuery(name = "Coupons.findByPercentage", query = "SELECT c FROM Coupons c WHERE c.percentage = :percentage")
  , @NamedQuery(name = "Coupons.findByEnabled", query = "SELECT c FROM Coupons c WHERE c.enabled = :enabled")})
public class Coupons implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "UserId")
  private int userId;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "Coupon")
  private String coupon;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "ExpireDate")
  private String expireDate;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Percentage")
  private int percentage;
  @Column(name = "Enabled")
  private Boolean enabled;
  @JoinColumn(name = "ProductId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Product productId;

  public Coupons() {
  }

  public Coupons(Integer id) {
    this.id = id;
  }

  public Coupons(Integer id, int userId, String coupon, String expireDate, int percentage) {
    this.id = id;
    this.userId = userId;
    this.coupon = coupon;
    this.expireDate = expireDate;
    this.percentage = percentage;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getCoupon() {
    return coupon;
  }

  public void setCoupon(String coupon) {
    this.coupon = coupon;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }

  public int getPercentage() {
    return percentage;
  }

  public void setPercentage(int percentage) {
    this.percentage = percentage;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Product getProductId() {
    return productId;
  }

  public void setProductId(Product productId) {
    this.productId = productId;
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
    if (!(object instanceof Coupons)) {
      return false;
    }
    Coupons other = (Coupons) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Models.Coupons[ id=" + id + " ]";
  }
  
}
