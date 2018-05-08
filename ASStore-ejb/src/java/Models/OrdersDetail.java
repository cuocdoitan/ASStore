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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tien Phat
 */
@Entity
@Table(name = "OrdersDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersDetail.findAll", query = "SELECT o FROM OrdersDetail o")
    , @NamedQuery(name = "OrdersDetail.findById", query = "SELECT o FROM OrdersDetail o WHERE o.id = :id")
    , @NamedQuery(name = "OrdersDetail.findByUnitPrice", query = "SELECT o FROM OrdersDetail o WHERE o.unitPrice = :unitPrice")
    , @NamedQuery(name = "OrdersDetail.findByQuantity", query = "SELECT o FROM OrdersDetail o WHERE o.quantity = :quantity")})
public class OrdersDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;
    @JoinColumn(name = "OrdersId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Orders ordersId;
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Product productId;

    public OrdersDetail() {
    }

    public OrdersDetail(Integer id) {
        this.id = id;
    }

    public OrdersDetail(Integer id, BigDecimal unitPrice, int quantity) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Orders ordersId) {
        this.ordersId = ordersId;
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
        if (!(object instanceof OrdersDetail)) {
            return false;
        }
        OrdersDetail other = (OrdersDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.OrdersDetail[ id=" + id + " ]";
    }
    
}
