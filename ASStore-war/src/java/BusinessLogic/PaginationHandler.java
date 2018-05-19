/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.util.List;
import Models.Product;

/**
 *
 * @author Tien Phat
 */
public class PaginationHandler {

    public int countNumberOfPages(int totalObjects, int numberObjectsPerPage) {
        int numberOfPages = totalObjects / numberObjectsPerPage;
        if (numberOfPages == 0) {
            numberOfPages += 1;
        } else {
            int exceededObjects = totalObjects % numberObjectsPerPage;
            if (exceededObjects != 0) {
                numberOfPages += 1;
            }
        }
        return numberOfPages;
    }

    public int[] getObjectPositionInSelectedPage(int totalObjects, int numberObjectsPerPage, int selectedPage) {
        int lastPage = countNumberOfPages(totalObjects, numberObjectsPerPage);
        int firstIndex, lastIndex;
        if (selectedPage == lastPage) {
            int exceededObjects = totalObjects % numberObjectsPerPage;
            if (exceededObjects != 0) {
                lastIndex = totalObjects;
                firstIndex = lastIndex - exceededObjects + 1;
            } else {
                firstIndex = (numberObjectsPerPage * (selectedPage - 1)) + 1;
                lastIndex = numberObjectsPerPage * selectedPage;
            }
        } else {
            firstIndex = (numberObjectsPerPage * (selectedPage - 1)) + 1;
            lastIndex = numberObjectsPerPage * selectedPage;
        }
        int[] range = new int[]{firstIndex - 1, lastIndex};
        return range;
    }

    public int getSelectedPage(String page) {
        int selectedPage;
        if (page == null) {
            selectedPage = 1;
        } else {
            try {
                selectedPage = Integer.parseInt(page);
            } catch (Exception e) {
                selectedPage = 1;
            }
        }
        return selectedPage;
    }

    public int[] arrayOfPages(int numberOfPage) {
        int page = 1;
        int[] arrPages = new int[numberOfPage];
        for (int i = 0; i < numberOfPage; i++) {
            arrPages[i] = page;
            page++;
        }
        return arrPages;
    }
}
