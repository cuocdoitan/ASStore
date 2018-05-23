/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.CartDetail;
import Models.Coupons;
import Models.OrdersDetail;
import Models.Product;
import Models.Users;
import SB.BankCardFacadeLocal;
import SB.CartDetailFacadeLocal;
import SB.CartFacadeLocal;
import SB.CouponsFacadeLocal;
import SB.MediaFacadeLocal;
import SB.OrdersDetailFacadeLocal;
import SB.OrdersFacadeLocal;
import SB.ProductFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zerox
 */
@WebServlet(name = "Cart", urlPatterns = {"/cart/*"})
public class Cart extends HttpServlet {

  @EJB
  private UsersFacadeLocal userFacade;
  @EJB
  private MediaFacadeLocal mediaFacade;
  @EJB
  private CartDetailFacadeLocal cartDetailFacade;
  @EJB
  private CartFacadeLocal cartFacade;
  @EJB
  private OrdersFacadeLocal orderFacade;
  @EJB
  private OrdersDetailFacadeLocal orderDetailFacade;
  @EJB
  private BankCardFacadeLocal bankCardFacade;
  @EJB
  private CouponsFacadeLocal couponsFacade;
  @EJB
  private ProductFacadeLocal productFacade;

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String clientRequest = request.getPathInfo();
    HttpSession sess = request.getSession();
    switch (clientRequest) {
      case "/list":
        Users user = userFacade.getGuestUser();
        java.util.List<Models.CartDetail> details = null;
        if (sess.getAttribute("userId") != null) {
          int userId = (int)sess.getAttribute("userId");
          user = userFacade.find(userId);
          int id = cartFacade.findByUserId(userId).getId();
          details = cartDetailFacade.findByCartId(id);
        }
        else {
          if (sess.getAttribute("cart") == null) {
            sess.setAttribute("cart", new ArrayList<>());
          }
          details = (java.util.List<Models.CartDetail>)sess.getAttribute("cart");
        }
        
        HashMap images = new HashMap();
        HashMap stotals = new HashMap();
        HashMap coupons = new HashMap();
        BigDecimal total = new BigDecimal(0);
        for (Models.CartDetail detail : details) {
          BigDecimal dtotal = detail.getProductId().getPrice().multiply(new BigDecimal(detail.getQuantity()));
          if (detail.getCoupon() != null && !detail.getCoupon().trim().equals("")) {
            Coupons co = couponsFacade.findByCoupon(detail.getCoupon());
            BigDecimal percentage = new BigDecimal(co.getPercentage());
            dtotal = dtotal.subtract(dtotal.multiply(percentage).divide(new BigDecimal(100)));
            coupons.put(detail.getCoupon(), co.getPercentage());
          }
          
          stotals.put(detail.getId(), dtotal);
          total = total.add(dtotal);
          images.put(detail.getProductId().getId(), mediaFacade.getFirstImageFromProduct(detail.getProductId()));
        }
        request.setAttribute("coupons", coupons);
        request.setAttribute("stotals", stotals);
        request.setAttribute("cartTotal", total);
        request.setAttribute("images", images);
        request.setAttribute("details", details);
        request.setAttribute("userId", sess.getAttribute("userId") != null ? sess.getAttribute("userId").toString() : "guest");
        request.setAttribute("address", sess.getAttribute("userId") != null ? user.getAddress() : "");
        request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
        break;
      case "/getPrice":
        int detailsId = Integer.parseInt(request.getParameter("detailid"));
        CartDetail detaild = cartDetailFacade.find(detailsId);
        response.setContentType("application/json");
        response.getWriter().print("{\"price\": \"" + detaild.getUnitPrice().multiply(new BigDecimal(detaild.getQuantity())) + "\"}");
        break;
      case "/getTotal":
        response.setContentType("application/json");
        int userId = userFacade.getGuestUser().getId();
        if (sess.getAttribute("userId") != null) {
          userId = (int)sess.getAttribute("userId");
        }
        int id = cartFacade.findByUserId(userId).getId();
        details = cartDetailFacade.findByCartId(id);
        total = new BigDecimal(0);
        for (Models.CartDetail detail : details) {
          BigDecimal dtotal = detail.getProductId().getPrice().multiply(new BigDecimal(detail.getQuantity()));
          if (detail.getCoupon() != null && !detail.getCoupon().trim().equals("")) {
            Coupons co = couponsFacade.findByCoupon(detail.getCoupon());
            BigDecimal percentage = new BigDecimal(co.getPercentage());
            dtotal = dtotal.subtract(dtotal.multiply(percentage).divide(new BigDecimal(100)));
          }
          total = total.add(dtotal);
        }
        response.getWriter().print("{\"price\": \"" + total + "\"}");
        break;
      case "/remove":
        int detailId = Integer.parseInt(request.getParameter("id"));
        int amount = 0;
        if (request.getParameter("amount") != null) {
          amount = Integer.parseInt(request.getParameter("amount"));
        }
        int productId = -1;
        int quantity = 0;
        if (sess.getAttribute("userId") == null) {
          ArrayList<CartDetail> cart = (ArrayList<CartDetail>)sess.getAttribute("cart");
          for (CartDetail c : cart) {
            if (c.getId().equals(detailId)) {
              if (amount > 0) {
                c.setQuantity(c.getQuantity() - amount);
                if (c.getQuantity() == 0) {
                  cart.remove(c);
                }
                productId = c.getProductId().getId();
                quantity = amount;
              }
              else {
                cart.remove(c);
                productId = c.getProductId().getId();
                quantity = c.getQuantity();
              }
              
              break;
            }
          }
        }
        else {
          if (amount > 0) {
            CartDetail c = cartDetailFacade.find(detailId);
            c.setQuantity(c.getQuantity() - amount);
            productId = cartDetailFacade.find(detailId).getProductId().getId();
            quantity = amount;
            if (c.getQuantity() == 0) {
              cartDetailFacade.remove(c);
            }
            else {
              cartDetailFacade.edit(c);
            }
          }
          else {
            cartDetailFacade.remove(cartDetailFacade.find(detailId));
            productId = cartDetailFacade.find(detailId).getProductId().getId();
            quantity = cartDetailFacade.find(detailId).getQuantity();
          }
        }
        Product ps = productFacade.find(productId);
        ps.setQuantity(ps.getQuantity() + quantity);
        productFacade.edit(ps);
        response.sendRedirect(request.getContextPath() + "/cart/list");
    }
  }

  public String genPassCode() {
    Random r = new Random(System.currentTimeMillis());
    return Integer.toString((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String clientRequest = request.getPathInfo();
    HttpSession sess = request.getSession();
    switch (clientRequest) {
      case "/addCoupon":
        String detailId = request.getParameter("detailid");
        String coupon = request.getParameter("coupon");
        response.setContentType("application/json");
        
        if (sess.getAttribute("userId") != null) {
          CartDetail detail = cartDetailFacade.find(Integer.parseInt(detailId));
          if (coupon.trim().equals("")) {
            detail.setCoupon("");
            cartDetailFacade.edit(detail);
            return;
          }
          // TODO: validate coupon
          
          Coupons couponDetail = couponsFacade.findByCoupon(coupon);
          if (couponDetail != null) {
            Date current = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
              Date expire = format.parse(couponDetail.getExpireDate());
              if (expire.before(current)) {
                response.getWriter().print("{ \"status\": \"expired\", \"mess\":\"Coupon expired!\" }");
              }
              else {
                if (detail.getProductId().getId().equals(couponDetail.getProductId().getId())) {
                  detail.setCoupon(coupon);
                  cartDetailFacade.edit(detail);
                  response.getWriter().print("{ \"status\": \"success\", \"mess\":\"Used coupon!\", \"percentage\" : \"" + couponDetail.getPercentage() + " \" }");
                }
                else {
                  response.getWriter().print("{\"status\": \"noapply\"}");
                }
                
              }
            }catch(Exception e) {
              response.getWriter().print("{ \"status\": \"failed\", \"mess\":\"Coupon not found!\" }");
              e.printStackTrace();
            }
            
          }
          else {
            response.getWriter().print("{ \"status\": \"failed\", \"mess\":\"Coupon not found!\" }");
          }
          
        }
        break;
      case "/addToCart":
        int productId = Integer.parseInt(request.getParameter("productid"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal unitPrice = new BigDecimal(request.getParameter("unitprice"));
        if (sess.getAttribute("userId") == null) {
          if (sess.getAttribute("cart") == null) {
            sess.setAttribute("cart", new ArrayList<>());
          }
          ArrayList<CartDetail> cart = (ArrayList<CartDetail>) sess.getAttribute("cart");
          CartDetail de = new CartDetail();
          boolean isInCart = false;
          int cartPos = -1;
          for (int i = 0; i < cart.size(); i++) {
            CartDetail det = cart.get(i);
            if (det.getProductId().getId() == productId) {
              isInCart = true;
              de = det;
              cartPos = i;
            }
          }
          if (!isInCart) {
            Product p = productFacade.find(productId);
            de.setId(0);
            de.setCoupon("");
            de.setProductId(p);
            de.setQuantity(quantity);
            de.setUnitPrice(unitPrice);
            cart.add(de);
          }
          else {
            de.setQuantity(de.getQuantity() + quantity);
            cart.set(cartPos, de);
          }
        }
        else {
          Models.Cart ca = cartFacade.findByUserId((int)sess.getAttribute("userId"));
          CartDetail de = new CartDetail();
          boolean isInCart = false;
          int cartPos = -1;
          
          for (int i = 0; i < ca.getCartDetails().size(); i++) {
            CartDetail det = ca.getCartDetails().get(i);
            if (det.getProductId().getId() == productId) {
              isInCart = true;
              de = det;
              cartPos = i;
            }
          }
          if (!isInCart) {
            de.setCartId(ca);
            de.setId(0);
            de.setCoupon("");
            de.setProductId(new Product(productId));
            de.setQuantity(quantity);
            de.setUnitPrice(unitPrice);
            try {
              cartDetailFacade.create(de);
            } catch (Exception e) {
              e.printStackTrace();
              response.setContentType("application/json");
              response.getWriter().print("{ \"status\": \"failed\" }");
              return;
            }
          }
          else {
            de.setQuantity(de.getQuantity() + quantity);
            try {
            cartDetailFacade.edit(de);
            } catch (Exception e) {
              e.printStackTrace();
              response.setContentType("application/json");
              response.getWriter().print("{ \"status\": \"failed\" }");
              return;
            }
          }
          
          
        }
        Product ps = productFacade.find(productId);
        ps.setQuantity(ps.getQuantity() - quantity);
        productFacade.edit(ps);
        response.setContentType("application/json");
        response.getWriter().print("{ \"status\": \"success\" }");
        break;
        
      case "/checkout":
        String method = request.getParameter("method");
        String address = request.getParameter("address");
        String cardNumber = request.getParameter("cardNumber");
        String cardSecurity = request.getParameter("cardSecurity");
        String expiryDate = request.getParameter("expiryDate");
        String phone = request.getParameter("phone");
        String error = "";
        boolean hasError = false;
        java.util.List<Models.CartDetail> details = null;
        Models.Users useri = userFacade.getGuestUser();
        // show product
        if (sess.getAttribute("userId") != null){
          useri = userFacade.find(sess.getAttribute("userId"));
          details = cartDetailFacade.findByCartId(cartFacade.findByUserId((int)sess.getAttribute("userId")).getId());
        }
        else {
          details = (List<CartDetail>)sess.getAttribute("cart");
        }
        HashMap images = new HashMap();
        HashMap stotals = new HashMap();
        HashMap coupons = new HashMap();
        BigDecimal total = new BigDecimal(0);
        
        for (Models.CartDetail detail : details) {
          BigDecimal dtotal = detail.getProductId().getPrice().multiply(new BigDecimal(detail.getQuantity()));
          if (detail.getCoupon() != null && !detail.getCoupon().trim().equals("")) {
            Coupons co = couponsFacade.findByCoupon(detail.getCoupon());
            BigDecimal percentage = new BigDecimal(co.getPercentage());
            dtotal = dtotal.subtract(dtotal.multiply(percentage).divide(new BigDecimal(100)));
            coupons.put(detail.getCoupon(), co.getPercentage());
          }
          
          stotals.put(detail.getId(), dtotal);
          total = total.add(dtotal);
          images.put(detail.getProductId().getId(), mediaFacade.getFirstImageFromProduct(detail.getProductId()));
        }
        request.setAttribute("coupons", coupons);
        request.setAttribute("stotals", stotals);
        request.setAttribute("cartTotal", total);
        request.setAttribute("images", images);
        request.setAttribute("details", details);
        request.setAttribute("userId", sess.getAttribute("userId") != null ? sess.getAttribute("userId").toString() : "guest");
        request.setAttribute("address", sess.getAttribute("userId") != null ? useri.getAddress() : "");

        if (phone.trim().equals("")) {
          error = error.equals("") ? "Phone can't be blank!" : error;
          hasError = true;
        } else {
          String p = "^0(1|8|9)\\d{8,9}$";
          Pattern c = Pattern.compile(p);
          if (!c.matcher(phone).matches()) {
            error = error.equals("") ? "Phone must contains from 10 to 11 digits and begins with 0 and follow by 1, 8 or 9!" : error;
            hasError = true;
          } else {
            request.setAttribute("phone", phone);
          }
        }
        if (address.trim().equals("")) {
          error = error.equals("") ? "Address can't be blank!" : error;
          hasError = true;
        } else {
          request.setAttribute("address", address);
        }
        if (method.equals("cash")) {
          if (!hasError) {
            if (sess.getAttribute("userId") == null) {
              Models.Users user = userFacade.getGuestUser();
              HashMap cartDetails = new HashMap();
              java.util.List<Models.CartDetail> cartDetailSess = (java.util.List<Models.CartDetail>) sess.getAttribute("cart");

              for (Models.CartDetail detail : cartDetailSess) {
                cartDetails.put(detail.getId(), detail);
              }
              String passcode = genPassCode();
              newOrder(user.getId(), passcode, address, phone, cartDetails);
              sess.setAttribute("cart", new ArrayList<>());
              request.setAttribute("isGuest", true);
              request.setAttribute("orderCode", passcode);
              request.getRequestDispatcher("/user/checkout-success.jsp").forward(request, response);
            } else {
              Models.Users user = userFacade.find(sess.getAttribute("userId"));
              HashMap cartDetails = new HashMap();
              java.util.List<CartDetail> cardDetails = cartDetailFacade.findByCartId(cartFacade.findByUserId(user.getId()).getId());
              for (CartDetail detail : cardDetails) {
                cartDetails.put(detail.getId(), detail);
              }
              String passcode = genPassCode();
              newOrder(user.getId(), passcode, address, phone, cartDetails);
              for (CartDetail detail : cardDetails) {
                cartDetailFacade.remove(detail);
              }
              request.setAttribute("isGuest", false);
              request.setAttribute("orderCode", passcode);
              request.getRequestDispatcher("/user/checkout-success.jsp").forward(request, response);
            }
          }
        } else {
          request.setAttribute("isCard", true);

          if (cardNumber.trim().equals("")) {
            error = error.equals("") ? "Please enter card number" : error;
            hasError = true;
          } else {
            request.setAttribute("cardNumber", cardNumber);
          }
          if (cardSecurity.trim().equals("")) {
            error = error.equals("") ? "Please enter card security number" : error;
            hasError = true;
          } else {
            request.setAttribute("cardSecurity", cardSecurity);
          }
          if (expiryDate.trim().equals("")) {
            error = error.equals("") ? "Please enter your expiry date" : error;
            hasError = true;
          } else {
            request.setAttribute("expiryDate", expiryDate);
            Date current = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
              Date expire = format.parse(expiryDate);
              if (!bankCardFacade.isCorrectCardNumber(cardNumber.replaceAll(" ",""), cardSecurity, expiryDate)) {
                error = error.equals("") ? "Card details is invalid !" : error;
                hasError = true;
              }
              if (expire.before(current)) {
                error = error.equals("") ? "Your card is expired!" : error;
                hasError = true;
              }
              if (!bankCardFacade.hasEnoughMoney(cardNumber.replaceAll(" ",""), total)) {
                error = error.equals("") ? "Your card doesn't have enough money!" : error;
                hasError = true;
              }
              if (!hasError) {
                if (sess.getAttribute("userId") == null) {
                  Models.Users user = userFacade.getGuestUser();
                  HashMap cartDetails = new HashMap();
                  java.util.List<Models.CartDetail> cartDetailSess = (java.util.List<Models.CartDetail>) sess.getAttribute("cart");

                  for (Models.CartDetail detail : cartDetailSess) {
                    cartDetails.put(detail.getId(), detail);
                  }
                  String passcode = genPassCode();
                  newOrder(user.getId(), passcode, address, phone, cartDetails);
                  sess.setAttribute("cart", new ArrayList<>());
                  bankCardFacade.pay(cardNumber.replaceAll(" ",""), total);
                  request.setAttribute("isGuest", true);
                  request.setAttribute("orderCode", passcode);
                  request.getRequestDispatcher("/user/checkout-success.jsp").forward(request, response);
                } else {
                  Models.Users user = userFacade.find(sess.getAttribute("userId"));
                  HashMap cartDetails = new HashMap();
                  java.util.List<CartDetail> cardDetails = cartDetailFacade.findByCartId(cartFacade.findByUserId(user.getId()).getId());
                  for (CartDetail detail : cardDetails) {
                    cartDetails.put(detail.getId(), detail);
                  }
                  String passcode = genPassCode();
                  newOrder(user.getId(), passcode, address, phone, cartDetails);
                  for (CartDetail detail : cardDetails) {
                    cartDetailFacade.remove(detail);
                  }
                  bankCardFacade.pay(cardNumber.replaceAll(" ",""), total);
                  request.setAttribute("isGuest", false);
                  request.setAttribute("orderCode", passcode);
                  request.getRequestDispatcher("/user/checkout-success.jsp").forward(request, response);
                }
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
            // TODO: CHECK CARD

          }
        }

        if (hasError) {
          request.setAttribute("error", error);
          request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
        }
    }
  }

  private void newOrder(int userId, String passcode, String address, String phone, HashMap cartDetails) {
    Models.Orders order = new Models.Orders();
    Models.Users user = new Users(userId);
    order.setId(0);
    order.setUsersId(user);
    order.setAddress(address);
    order.setPhone(phone);
    order.setPassCode(passcode);
    order.setEnabled(true);
    order.setStatus(false);
    order.setCreateAt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    Models.Orders newOrder = orderFacade.createOrder(order);
    Iterator entries = cartDetails.entrySet().iterator();
    while (entries.hasNext()) {
      Map.Entry entry = (Map.Entry) entries.next();
      CartDetail detail = (CartDetail) entry.getValue();
      Models.OrdersDetail details = new OrdersDetail();
      details.setId(0);
      details.setOrdersId(newOrder);
      details.setProductId(detail.getProductId());
      details.setQuantity(detail.getQuantity());
      BigDecimal price = (BigDecimal) detail.getUnitPrice();
      // apply coupon
      if (detail.getCoupon() != null && !detail.getCoupon().trim().equals("")) {
        Coupons coupon = couponsFacade.findByCoupon(detail.getCoupon());
        BigDecimal percentage = new BigDecimal(coupon.getPercentage());
        price = price.subtract(price.multiply(percentage).divide(new BigDecimal(100)));
      }
      details.setUnitPrice(price);
      orderDetailFacade.create(details);
    }

  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
