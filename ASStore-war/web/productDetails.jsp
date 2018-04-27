<%-- 
    Document   : productDetails
    Created on : Apr 26, 2018, 5:03:59 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <div class="container bgwhite p-t-35 p-b-80">
    <a href="javascript:history.back()" class="s-text2">&lt; Back</a>
    <div class="flex-w flex-sb">
      <div class="w-size13 p-t-30 respon5">
        <div class="wrap-slick3 flex-sb flex-w">
          <div class="wrap-slick3-dots"></div>

          <div class="slick3">
            <div class="item-slick3" data-thumb="assets/img/products/kakashi_cup.jpg">
              <div class="wrap-pic-w">
                <img src="assets/img/products/kakashi_cup.jpg" alt="IMG-PRODUCT">
              </div>
            </div>

            <div class="item-slick3" data-thumb="assets/img/products/kakashi_cup2.jpg">
              <div class="wrap-pic-w">
                <img src="assets/img/products/kakashi_cup2.jpg" alt="IMG-PRODUCT">
              </div>
            </div>

            <div class="item-slick3" data-thumb="assets/img/products/kakashi_cup3.jpg">
              <div class="wrap-pic-w">
                <img src="assets/img/products/kakashi_cup3.jpg" alt="IMG-PRODUCT">
              </div>
            </div>
          </div>
        </div>
      </div>


      <div class="w-size14 p-t-30 respon5">
        <h4 class="product-detail-name m-text16 p-b-13">
          5 x Kakashi Hatake coffee cup
        </h4>

        <span class="m-text17">
          $22
        </span>
        <br>
        <!-- Rating -->
        <div class="product-rating p-t-10">
          <span class="fa fa-star"></span>
          <span class="fa fa-star"></span>
          <span class="fa fa-star"></span>
          <span class="fa fa-star"></span>
          <span class="fa fa-star"></span>
        </div>
        <!--  -->
        <div class="p-t-10 p-b-10">
          <div class="flex-r-m flex-w p-t-10">
            <div class="w-size16 flex-m flex-w">
              <div class="flex-w bo5 of-hidden m-r-22 m-t-10 m-b-10">
                <button class="btn-num-product-down color1 flex-c-m size7 bg8 eff2">
                  <i class="fs-12 fa fa-minus" aria-hidden="true"></i>
                </button>

                <input class="size8 m-text18 t-center num-product" type="number" name="num-product" value="1">

                <button class="btn-num-product-up color1 flex-c-m size7 bg8 eff2">
                  <i class="fs-12 fa fa-plus" aria-hidden="true"></i>
                </button>
              </div>

              <div class="btn-addcart-product-detail size9 trans-0-4 m-t-10 m-b-10">
                <!-- Button -->
                <button class="flex-c-m sizefull bg1 bo-rad-23 hov1 s-text1 trans-0-4">
                  Add to Cart
                </button>
              </div>
            </div>
          </div>
        </div>

        <p class="s-text3 p-t-10">
          The mother fucking best cup evarrrrrrrrrr !!!!!!!!! They even have Kakashi image on !!!!!!!! Buy this shit !!!!! Shut up and give me your money
        </p>

        
      </div>
    </div>
  </div>
</t:layout>