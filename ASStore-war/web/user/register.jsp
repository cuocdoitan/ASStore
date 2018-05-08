<%-- 
    Document   : login
    Created on : Apr 29, 2018, 12:06:07 AM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pureLayout>
  <section class="p-t-20 p-b-60" style="background: url(<c:url value='/assets/img/login_banner.jpg' />); height: 100vh; background-size: cover;">
    <div class="container">
      <div class="row">
        <div class="col-md-6 p-b-30 p-t-30 p-l-30 p-r-30 bgwhite">
            <form method="post" action="<c:url value='/User/create' />">
            <h4 class="m-text26 p-b-36 p-t-15">
              Sign up to AS Store
            </h4>

            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="phone" name="phone" placeholder="Phone number">
            </div>
            
            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="first_name" placeholder="First name">
            </div>
            
            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="last_name" placeholder="Last name">
            </div>

            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="password" placeholder="Password">
            </div>
            
            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="confirm_password" placeholder="Confirm password">
            </div>
            
            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="address" placeholder="Address">
            </div>
            
            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="email" name="email" placeholder="Email">
            </div>

            <div class="w-size25">
              <!-- Button -->
              <button class="flex-c-m size2 bg1 bo-rad-23 hov1 m-text3 trans-0-4">
                Sign up
              </button>
            </div>
            
            <div class="m-t-20">
              <a href="#">Already have an account? Login now!</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>

</t:pureLayout>