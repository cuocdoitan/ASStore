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
    private int totalProduct, totalQuantityProduct;
    private Date date;
    
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

    public String getDate() {
        Format formatter = new SimpleDateFormat("dd / MM / yyyy");
        return formatter.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
