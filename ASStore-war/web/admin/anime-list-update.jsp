<%-- 
    Document   : anime-list-update
    Created on : May 4, 2018, 8:27:09 PM
    Author     : leminhtung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:adminLayout>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h1 class="text-center m-t-20 m-b-20">Update anime</h1>
        <form action="">
          <label>Anime name:</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" value="Trigun">
          </div>
          <label>Anime description:</label>
          <div class="bo4 of-hidden m-b-20">
            <textarea style="width: 100%; border: none; padding: 20px" rows="10" placeholder="anime description">
Vash the Stampede is the man with a $$60,000,000,000 bounty on his head. The reason: he&#039;s a merciless villain who lays waste to all those that oppose him and flattens entire cities for fun, garnering him the title "The Humanoid Typhoon." He leaves a trail of death and destruction wherever he goes, and anyone can count themselves dead if they so much as make eye contact—or so the rumors say. In actuality, Vash is a huge softie who claims to have never taken a life and avoids violence at all costs. With his crazy doughnut obsession and buffoonish attitude in tow, Vash traverses the wasteland of the planet Gunsmoke, all the while followed by two insurance agents, Meryl Stryfe and Milly Thompson, who attempt to minimize his impact on the public. But soon, their misadventures evolve into life-or-death situations as a group of legendary assassins are summoned to bring about suffering to the trio. Vash&#039;s agonizing past will be unraveled and his morality and principles pushed to the breaking point. [Written by MAL Rewrite]
            </textarea>
          </div>
          <label>Anime image</label>
          <div class="m-t-20 m-b-20">
            <img src="<c:url value='/assets/img/anime/20310.jpg'/>" alt="IMG-PRODUCT" width="150" height="180" />
          </div>
          <label>New category image</label>
          <div class="m-b-50">
            <input type="file" name="image" accept="image/*"/>
          </div>
          <button style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4 m-b-20">
            Edit
          </button>
        </form>
      </div>
    </div>
  </div>
</t:adminLayout>
