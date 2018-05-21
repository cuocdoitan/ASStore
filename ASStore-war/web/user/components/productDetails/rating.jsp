<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Average Rating</h3>
<style>
    /*---------- star rating ----------*/
.star-rating {
    display: flex;
    align-items: center;
    font-size: 2em;
    justify-content: left;
    margin-top: 10px;
    margin-bottom: 10px;
}
.back-stars {
    display: flex;
    color: #bb5252;
    position: relative;
    text-shadow: 4px 4px 10px #843a3a;
}
.front-stars {
    display: flex;
    color: #FFBC0B;
    overflow: hidden;
    position: absolute;
    text-shadow: 2px 2px 5px #d29b09;
    top: 0;
}
</style>
<div class="star-rating" title="75%">
    <div class="back-stars">
        <i class="fa fa-star" aria-hidden="true"></i>
        <i class="fa fa-star" aria-hidden="true"></i>
        <i class="fa fa-star" aria-hidden="true"></i>
        <i class="fa fa-star" aria-hidden="true"></i>
        <i class="fa fa-star" aria-hidden="true"></i>

        <div class="front-stars">
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star" aria-hidden="true"></i>
            <i class="fa fa-star" aria-hidden="true"></i>
        </div>
    </div>
</div> 
<p>${product.productRatingCollection.size()} voters </p>
<p class="totalRating">${product.averageStars()}/5 stars</p>
<hr/>
<c:choose>
    <c:when test="${sessionScope.userid == null}">
        <h3>Login to rating this product.</h3>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${ratedStars == null}">
                <h3>You haven't rated this product yet</h3>
                <fieldset class="rating" id="newRating">
                    <input type="radio" id="star5" name="rating" value="5"/><label class = "full" for="star5" title="Awesome - 5 stars"></label>
                    <input type="radio" id="star4half" name="rating" value="4.5"/><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                    <input type="radio" id="star4" name="rating" value="4"  /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                    <input type="radio" id="star3half" name="rating" value="3.5"/><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                    <input type="radio" id="star3" name="rating" value="3"  /><label class = "full" for="star3" title="Meh - 3 stars"></label>
                    <input type="radio" id="star2half" name="rating" value="2.5"  /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                    <input type="radio" id="star2" name="rating" value="2"/><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                    <input type="radio" id="star1half" name="rating" value="1.5" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                    <input type="radio" id="star1" name="rating" value="1"/><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                    <input type="radio" id="starhalf" name="rating" value="0.5"/><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                </fieldset>
                <input type="hidden" name="productId" value="${product.id}"/>
                <input type="hidden" name="userId" value="${sessionScope.userid}"/>
            </c:when>
            <c:otherwise>
                <h3>You already rated this product</h3>
                <fieldset class="rating" id="editRating">
                    <input type="radio" id="star5" name="rating" value="5" <c:if test="${ratedStars.rating == 5}">checked</c:if>/>
                        <label class = "full" for="star5" title="Awesome - 5 stars"></label>
                        <input type="radio" id="star4half" name="rating" value="4.5" <c:if test="${ratedStars.rating == 4.5}">checked</c:if>/>
                        <label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                        <input type="radio" id="star4" name="rating" value="4" <c:if test="${ratedStars.rating == 4}">checked</c:if>/>
                        <label class = "full" for="star4" title="Pretty good - 4 stars"></label>
                        <input type="radio" id="star3half" name="rating" value="3.5"   <c:if test="${ratedStars.rating == 3.5}">checked</c:if>/>
                        <label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                        <input type="radio" id="star3" name="rating" value="3" <c:if test="${ratedStars.rating == 3}">checked</c:if>/>
                        <label class = "full" for="star3" title="Meh - 3 stars"></label>
                        <input type="radio" id="star2half" name="rating" value="2.5" <c:if test="${ratedStars.rating == 2.5}">checked</c:if>/>
                        <label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                        <input type="radio" id="star2" name="rating" value="2"   <c:if test="${ratedStars.rating == 2}">checked</c:if>/>
                        <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
                        <input type="radio" id="star1half" name="rating" value="1.5"  <c:if test="${ratedStars.rating == 1.5}">checked</c:if>/>
                        <label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                        <input type="radio" id="star1" name="rating" value="1"   <c:if test="${ratedStars.rating == 1}">checked</c:if>/>
                        <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                        <input type="radio" id="starhalf" name="rating" value="0.5" <c:if test="${ratedStars.rating == 0.5}">checked</c:if>/>
                        <label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                    </fieldset>
                    <input type="hidden" name="ratingId" value="${ratedStars.id}"/>
                Your rating : ${ratedStars.rating}/5 stars
                <br/>
                <button id="cancelRating">Cancel</button>
            </c:otherwise>
        </c:choose>

    </c:otherwise>
</c:choose>
<script src="<c:url value="/assets/js/product.js"/>"></script>