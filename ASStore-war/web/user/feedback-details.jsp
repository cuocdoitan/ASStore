<%-- 
    Document   : feedback-details
    Created on : May 2, 2018, 3:04:27 PM
    Author     : TRAN HO QUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <t:layout>
        <section class="bgwhite p-t-70 p-b-100">
            <div class="container">
                <h2>Title Feedback user abcccc wqewew admin</h2><br><br>
                <div>
                    <input value="2/5/2018">
                </div>
                <br/>
<!--                <label>Feedback Content</label>-->
                <div class="bo4 of-hidden m-b-20">
                    <textarea style="width: 100%; border: none; padding: 20px" rows="5"></textarea>
                </div>
<!--                if user edit-->
<!--            <button>
                    Edit Feedback
                </button>-->
                <br/>
                <br/>
                <button>
                    Comments
                </button>
                <div class="bo4 of-hidden size15 m-b-20" style="position: relative">
                    <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="title" >
                </div>
                <br/>
<!--              if admin delete-->
                <button onclick="return confirm('Are you sure to delete feedback?')">
                    Delete Feedback
                </button>
            </div>
        </section>
    </t:layout>
</html>
