/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Users;
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
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "ASStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public Users getUsersByPhone(String phone){
        TypedQuery query = em.createQuery("SELECT u FROM Users u WHERE u.phoneNumber = ?1 and u.enabled = ?2", Users.class);
        query.setParameter(1, phone);
        query.setParameter(2, true);
        List<Users> list = query.getResultList();
        System.out.println("=====================================");
        System.out.println("list = "+list.toString());
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public List<Users> getList() {
        TypedQuery query = em.createNamedQuery("Users.findByEnabled", Users.class);
        query.setParameter("enabled", true);
        return query.getResultList();
    }

    

}
