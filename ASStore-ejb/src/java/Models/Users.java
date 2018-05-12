/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tien Phat
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
  , @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
  , @NamedQuery(name = "Users.findByPhoneNumber", query = "SELECT u FROM Users u WHERE u.phoneNumber = :phoneNumber")
  , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
  , @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName")
  , @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName")
  , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
  , @NamedQuery(name = "Users.findByAddress", query = "SELECT u FROM Users u WHERE u.address = :address")
  , @NamedQuery(name = "Users.findByNumberOfNotification", query = "SELECT u FROM Users u WHERE u.numberOfNotification = :numberOfNotification")
  , @NamedQuery(name = "Users.findByCreateAt", query = "SELECT u FROM Users u WHERE u.createAt = :createAt")
  , @NamedQuery(name = "Users.findByUpdateAt", query = "SELECT u FROM Users u WHERE u.updateAt = :updateAt")
  , @NamedQuery(name = "Users.findByEnabled", query = "SELECT u FROM Users u WHERE u.enabled = :enabled")})
public class Users implements Serializable {
  @Basic(optional = false)
  @NotNull
  @Temporal(TemporalType.DATE)
  @Column(name = "CreateAt")
  private Date createAt;
  @Temporal(TemporalType.DATE)
  @Column(name = "UpdateAt")
  private Date updateAt;

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Size(max = 15)
  @Column(name = "PhoneNumber")
  private String phoneNumber;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 30)
  @Column(name = "Password")
  private String password;
  @Size(max = 50)
  @Column(name = "FirstName")
  private String firstName;
  @Size(max = 50)
  @Column(name = "LastName")
  private String lastName;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Size(max = 50)
  @Column(name = "Email")
  private String email;
  @Size(max = 70)
  @Column(name = "Address")
  private String address;
  @Column(name = "NumberOfNotification")
  private Integer numberOfNotification;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Enabled")
  private boolean enabled;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
  private Collection<Orders> ordersCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
  private Collection<ProductRating> productRatingCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
  private Collection<Product> productCollection;
  @JoinColumn(name = "RolesId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Roles rolesId;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
  private Collection<FeedbackComment> feedbackCommentCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
  private Collection<Feedback> feedbackCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
  private Collection<Cart> cartCollection;

  public Users() {
  }

  public Users(Integer id) {
    this.id = id;
  }

  public Users(Integer id, String password, Date createAt, boolean enabled) {
    this.id = id;
    this.password = password;
    this.createAt = createAt;
    this.enabled = enabled;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getNumberOfNotification() {
    return numberOfNotification;
  }

  public void setNumberOfNotification(Integer numberOfNotification) {
    this.numberOfNotification = numberOfNotification;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @XmlTransient
  public Collection<Orders> getOrdersCollection() {
    return ordersCollection;
  }

  public void setOrdersCollection(Collection<Orders> ordersCollection) {
    this.ordersCollection = ordersCollection;
  }

  @XmlTransient
  public Collection<ProductRating> getProductRatingCollection() {
    return productRatingCollection;
  }

  public void setProductRatingCollection(Collection<ProductRating> productRatingCollection) {
    this.productRatingCollection = productRatingCollection;
  }

  @XmlTransient
  public Collection<Product> getProductCollection() {
    return productCollection;
  }

  public void setProductCollection(Collection<Product> productCollection) {
    this.productCollection = productCollection;
  }

  public Roles getRolesId() {
    return rolesId;
  }

  public void setRolesId(Roles rolesId) {
    this.rolesId = rolesId;
  }

  @XmlTransient
  public Collection<FeedbackComment> getFeedbackCommentCollection() {
    return feedbackCommentCollection;
  }

  public void setFeedbackCommentCollection(Collection<FeedbackComment> feedbackCommentCollection) {
    this.feedbackCommentCollection = feedbackCommentCollection;
  }

  @XmlTransient
  public Collection<Feedback> getFeedbackCollection() {
    return feedbackCollection;
  }

  public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
    this.feedbackCollection = feedbackCollection;
  }

  @XmlTransient
  public Collection<Cart> getCartCollection() {
    return cartCollection;
  }

  public void setCartCollection(Collection<Cart> cartCollection) {
    this.cartCollection = cartCollection;
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
    if (!(object instanceof Users)) {
      return false;
    }
    Users other = (Users) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Models.Users[ id=" + id + " ]";
  }

  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public Date getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(Date updateAt) {
    this.updateAt = updateAt;
  }
}
