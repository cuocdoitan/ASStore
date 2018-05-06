/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public boolean checkLogin(String id, String password) {
        Users e = find(id);
        if (e != null) {
            if (e.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
