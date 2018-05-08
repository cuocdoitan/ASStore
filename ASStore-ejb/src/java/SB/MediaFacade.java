/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Media;
import Models.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tien Phat
 */
@Stateless
public class MediaFacade extends AbstractFacade<Media> implements MediaFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MediaFacade() {
        super(Media.class);
    }
    
    public HashMap<Integer,List<String>> getImagesFromListProduct(List<Product> listProduct){
        HashMap<Integer,List<String>> mapImages = new HashMap<>();
        for(Product product : listProduct){
            List<String> images = getImagesFromProduct(product);
            mapImages.put(product.getId(), images);
        }
        return mapImages;
    }
    
    public List<String> getImagesFromProduct(Product product){
        List<String> images = new ArrayList<>();
        for(Media media : this.findAll()){
            if(product.getId()==media.getProductId().getId()){
                    images.add(media.getUrlImage());
                }
        }
        return images;
    }
    

    @Override
    public List<Media> getFirstImageFromListProduct(List<Product> listProduct){
        List<Media> images = new ArrayList<>();
        for(Product product : listProduct){
            for(Media media : this.findAll()){
                if(product.getId() == media.getProductId().getId()){
                    images.add(media);
                    break;
                }
            }
        }
        return images;

    public String getFirstImageFromProduct(Product product) {
      for(Media media : this.findAll()){
        if (product.getId() == media.getProductId().getId()) {
          return media.getUrlImage();
        }
      }
      return null;

    }
}
