/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tien Phat
 */
@Local
public interface ProductFacadeLocal {

    void create(Product product);

    void edit(Product product);

    void remove(Product product);

    Product find(Object id);

    List<Models.Product> findAll();

    List<Models.Product> findRange(int[] range);

    int count();
    
    public List<Models.Product> getListAvailableProduct_User(Models.Users user);
    
    public List<Models.Product> getListCheckingProduct_User(Models.Users user);
    
    public List<Models.Product> getListUnavailableProduct_User(Models.Users user);
    
    public List<Models.Product> getListApprovingProduct();
    
}
