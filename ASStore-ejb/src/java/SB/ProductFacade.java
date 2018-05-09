/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Product;
import java.util.ArrayList;
import java.util.List;
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

    
}
