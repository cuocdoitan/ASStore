/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author TRAN HO QUANG
 */
public class StatisticProductCategory {
    private String Name;
    private int totalProduct, totalQuantityProduct, soldQuantity;
    private Date dateFrom, dateTo;
    private int currentQuantityProducts;

    public int getCurrentQuantityProducts() {
        return currentQuantityProducts;
    }

    public void setCurrentQuantityProducts(int currentQuantityProducts) {
        this.currentQuantityProducts = currentQuantityProducts;
    }

  

    public String getDateFrom() {
        Format formatter = new SimpleDateFormat("dd / MM / yyyy");
        return formatter.format(dateFrom);
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        Format formatter = new SimpleDateFormat("dd / MM / yyyy");
        return formatter.format(dateTo);
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    
    public StatisticProductCategory(){
        
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public int getTotalQuantityProduct() {
        return totalQuantityProduct;
    }

    public void setTotalQuantityProduct(int totalQuantityProduct) {
        this.totalQuantityProduct = totalQuantityProduct;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    
    
    
}
