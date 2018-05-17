/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tien Phat
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public int createNewProduct(Product newProduct){
        em.persist(newProduct);
        em.flush();
        return newProduct.getId();
    }
    
    public List<Product> getListApprovingProduct(){
        List<Product> listApprovinProduct = new ArrayList<>();
        for(Product product : this.getListExistingProduct()){
            if(product.getStatus()==0){
                listApprovinProduct.add(product);
            }
        }
        return listApprovinProduct;
    }
    
    public List<Product> getListExistingProduct(){
        TypedQuery query = em.createNamedQuery("Product.findByEnabled", Product.class);
        query.setParameter("enabled", true);
        return query.getResultList();
    }
    
    
    public List<Product> getListProductSortedDesc(){
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1 and p.status = ?2 ORDER BY p.id DESC", Product.class);
        query.setParameter(1, true);
        query.setParameter(2, 1);
        return query.getResultList();
    }
    
    public List<Product> searchProductByName(String name){
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1 and p.name like ?2", Product.class);
        query.setParameter(1, true);
        query.setParameter(2, "%"+name+"%");
        return query.getResultList();
    }
    
    public List<Product> searchProductByUserPhoneNumber(String phoneNumber){
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1 and p.usersId.phoneNumber = ?2", Product.class);
        query.setParameter(1, true);
        query.setParameter(2, phoneNumber);
        return query.getResultList();
    }
    
    public List<Product> searchProduct(String productName, Models.Anime animeId, Models.Category categoryId, BigDecimal minPrice, BigDecimal maxPrice){
        String condition = "";
        String and = " and ";
        if(productName  != null){
            condition += and + "p.name like ?2";
        }
        if(animeId != null){
            condition += and + "p.animeId = ?3";
        }
        if(categoryId != null){
            condition += and + "p.categoryId = ?4";
        }
        if(minPrice != null){
            condition += and + "p.price >= ?5";
        }
        if(maxPrice != null){
            condition += and + "p.price <= ?6";
        }
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1"+condition, Product.class);
        query.setParameter(1, true);
        if(productName  != null){
            query.setParameter(2, "%"+productName+"%");
        }
        if(animeId != null){
            query.setParameter(3, animeId);
        }
        if(categoryId != null){
            query.setParameter(4, categoryId);
        }
        if(minPrice != null){
            query.setParameter(5, minPrice);
        }
        if(maxPrice != null){
            query.setParameter(6, maxPrice);
        }
        List<Product> list = query.getResultList();
        return list;
    }
    
    @Override
    public BigDecimal getHighestProductPrice(){
        TypedQuery query = em.createQuery("SELECT MAX(p.price) FROM Product p WHERE p.enabled = ?1", BigDecimal.class);
        query.setParameter(1, true);
        return (BigDecimal) query.getSingleResult();
    }
    
    @Override
    public List<Product> getRandomProductSameAnime(Product product){
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.enabled = ?1 and p.animeId = ?2", Product.class);
        query.setParameter(1, true);
        query.setParameter(2, product.getAnimeId());
        List<Product> list = query.getResultList();
        if(list.size() < 5){
            return list;
        }else{
            List<Product> randomResult = new ArrayList<>();
            for(int i = 0; i<4;i++){
                Product p = list.get(new Random().nextInt(list.size()));
                randomResult.add(p);
            }
            return randomResult;
        }
    }
    
}
