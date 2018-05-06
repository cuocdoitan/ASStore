/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SB;

import Models.Anime;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tien Phat
 */
@Local
public interface AnimeFacadeLocal {

    void create(Anime anime);

    void edit(Anime anime);

    void remove(Anime anime);

    Anime find(Object id);

    List<Anime> findAll();

    List<Anime> findRange(int[] range);

    int count();
    List<Anime> getAll();
    
}
