<%-- 
    Document   : header
    Created on : Apr 25, 2018, 9:49:10 AM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Header -->
<header class="header1">
    <input type="hidden" id="urlProject" value="<c:url value="/" />"/>
  <!-- Header desktop -->
  <div class="container-menu-header">
    <div class="wrap_header">
      <!-- Logo -->
      <a href="<c:url value="/index"/>" class="logo">
          <img src="<c:url value='/assets/img/asstore-logo.png'/>" alt="IMG-LOGO">
      </a>

      <!-- Menu -->
      <div class="wrap_menu">
        <nav class="menu">
          <ul class="main_menu">
            <li>
                <a href="<c:url value="/index"/>">Home</a>
            </li>

            <li>
                <a href="<c:url value="/products/list"/>">Products</a>
            </li>
            
            <li>
              <a href="<c:url value="/category/list"/>">Categories</a>
            </li>

            <li>
                <a href="<c:url value="/anime"/>">Anime</a>
            </li>

            <li>
                <a href="<c:url value="/feedbacks/list"/>">Feedback</a>
            </li>
            <li>
                <a href="<c:url value="/statisticals/listcate"/>">Statistical</a>
            </li>
          </ul>
        </nav>
      </div>

      <!-- Header Icon -->
      <div class="header-icons">
        <a href="<c:url value="/products/new-product"/>" style="margin-right: 20px;" onmouseover="this.style.color = 'red'">
            <button>NEW POST</button>   
        </a>
        <a href="#" class="header-wrapicon1 dis-block">
            <img src="<c:url value='/assets/img/icons/icon-header-01.png'/>" class="header-icon1" alt="ICON">
        </a>

        <span class="linedivide1"></span>

        <div class="header-wrapicon2">
          <img src="<c:url value='/assets/img/icons/icon-header-02.png'/>" class="header-icon1 js-show-header-dropdown" alt="ICON">
          <span class="header-icons-noti">0</span>

          <!-- Header cart noti -->
          <div class="header-cart header-dropdown">
            <ul class="header-cart-wrapitem">
              <li class="header-cart-item">
                <div class="header-cart-item-img">
                  <img src="<c:url value='/assets/img/item-cart-01.jpg'/>" alt="IMG">
                </div>

                <div class="header-cart-item-txt">
                  <a href="#" class="header-cart-item-name">
                    White Shirt With Pleat Detail Back
                  </a>

                  <span class="header-cart-item-info">
                    1 x $19.00
                  </span>
                </div>
              </li>
            </ul>

            <div class="header-cart-total">
              Total: $75.00
            </div>

            <div class="header-cart-buttons">
              <div class="header-cart-wrapbtn">
                <!-- Button -->
                <a href="cart.html" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                  View Cart
                </a>
              </div>

              <div class="header-cart-wrapbtn">
                <!-- Button -->
                <a href="#" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                  Check Out
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Header Mobile -->
  <div class="wrap_header_mobile">
    <!-- Logo moblie -->
    <a href="index" class="logo-mobile">
      <img src="<c:url value='/assets/img/asstore-logo.png'/>" alt="IMG-LOGO">
    </a>

    <!-- Button show menu -->
    <div class="btn-show-menu">
      <!-- Header Icon mobile -->
      <div class="header-icons-mobile">
        <a href="#" class="header-wrapicon1 dis-block">
          <img src="<c:url value='/assets/img/icons/icon-header-01.png'/>" class="header-icon1" alt="ICON">
        </a>

        <span class="linedivide2"></span>

        <div class="header-wrapicon2">
          <img src="<c:url value='/assets/img/icons/icon-header-02.png'/>" class="header-icon1 js-show-header-dropdown" alt="ICON">
          <span class="header-icons-noti">0</span>

          <!-- Header cart noti -->
          <div class="header-cart header-dropdown">
            <ul class="header-cart-wrapitem">
              <li class="header-cart-item">
                <div class="header-cart-item-img">
                  <img src="<c:url value='/assets/img/item-cart-01.jpg'/>" alt="IMG">
                </div>

                <div class="header-cart-item-txt">
                  <a href="#" class="header-cart-item-name">
                    White Shirt With Pleat Detail Back
                  </a>

                  <span class="header-cart-item-info">
                    1 x $19.00
                  </span>
                </div>
              </li>

            </ul>

            <div class="header-cart-total">
              Total: $75.00
            </div>

            <div class="header-cart-buttons">
              <div class="header-cart-wrapbtn">
                <!-- Button -->
                <a href="cart.html" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                  View Cart
                </a>
              </div>

              <div class="header-cart-wrapbtn">
                <!-- Button -->
                <a href="#" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                  Check Out
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
        <span class="hamburger-box">
          <span class="hamburger-inner"></span>
        </span>
      </div>
    </div>
  </div>

  <!-- Menu Mobile -->
  <div class="wrap-side-menu" >
    <nav class="side-menu">
      <ul class="main-menu">

        <li class="item-menu-mobile">
          <a href="index">Home</a>
        </li>

        <li class="item-menu-mobile">
          <a href="products">Products</a>
        </li>
        
        <li class="item-menu-mobile">
          <a href="category">Categories</a>
        </li>

        <li class="item-menu-mobile">
          <a href="anime">Anime</a>
        </li>

        <li class="item-menu-mobile">
          <a href="feedback">Feedback</a>
        </li>
      </ul>
    </nav>
  </div>
</header>