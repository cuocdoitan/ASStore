/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zerox
 */
@Entity
@Table(name = "Orders")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
  , @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id")
  , @NamedQuery(name = "Orders.findByPassCode", query = "SELECT o FROM Orders o WHERE o.passCode = :passCode")
  , @NamedQuery(name = "Orders.findByCreateAt", query = "SELECT o FROM Orders o WHERE o.createAt = :createAt")
  , @NamedQuery(name = "Orders.findByEnabled", query = "SELECT o FROM Orders o WHERE o.enabled = :enabled")
  , @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status")
  , @NamedQuery(name = "Orders.findByAddress", query = "SELECT o FROM Orders o WHERE o.address = :address")})
public class Orders implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "Id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "PassCode")
  private String passCode;
  @Basic(optional = false)
  @NotNull
  @Temporal(TemporalType.DATE)
  @Column(name = "CreateAt")
  private Date createAt;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Enabled")
  private boolean enabled;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Status")
  private boolean status;
  @Size(max = 600)
  @Column(name = "Address")
  private String address;
  @JoinColumn(name = "UsersId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Users usersId;

  public Orders() {
  }

  public Orders(Integer id) {
    this.id = id;
  }

  public Orders(Integer id, String passCode, Date createAt, boolean enabled, boolean status) {
    this.id = id;
    this.passCode = passCode;
    this.createAt = createAt;
    this.enabled = enabled;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPassCode() {
    return passCode;
  }

  public void setPassCode(String passCode) {
    this.passCode = passCode;
  }

  public String getCreateAt() {
    Format formatter = new SimpleDateFormat("dd / MM / yyyy");
    return formatter.format(createAt);
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Users getUsersId() {
    return usersId;
  }

  public void setUsersId(Users usersId) {
    this.usersId = usersId;
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
    if (!(object instanceof Orders)) {
      return false;
    }
    Orders other = (Orders) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Models.Orders[ id=" + id + " ]";
  }

}
