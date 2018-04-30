<%-- 
    Document   : login
    Created on : Apr 29, 2018, 12:06:07 AM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:pureLayout>
  <section class="p-t-150 p-b-60" style="background: url(assets/img/login_banner.jpg); height: 100vh; background-size: cover;">
    <div class="container">
      <div class="row">
        <div class="col-md-6 p-b-30 p-t-30 p-l-30 p-r-30 bgwhite">
          <form>
            <h4 class="m-text26 p-b-36 p-t-15">
              Login to AS Store
            </h4>

            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="phone" name="phone" placeholder="Phone number">
            </div>

            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="password" placeholder="Password">
            </div>

            <div class="w-size25">
              <!-- Button -->
              <button class="flex-c-m size2 bg1 bo-rad-23 hov1 m-text3 trans-0-4">
                Login
              </button>
            </div>
            
            <div class="m-t-20">
              <a href="#">Don't have an account? Create one now!</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>

</t:pureLayout>