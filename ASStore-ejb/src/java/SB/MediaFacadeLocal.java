/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Media;
import Models.Product;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tien Phat
 */
@Local
public interface MediaFacadeLocal {

    void create(Media media);

    void edit(Media media);

    void remove(Media media);

    Media find(Object id);

    List<Media> findAll();

    List<Media> findRange(int[] range);

    int count();
    
    public HashMap<Integer,List<String>> getImagesFromListProduct(List<Product> listProduct);
    public List<String> getImagesFromProduct(Product product);
    public String getFirstImageFromProduct(Product product);
}
