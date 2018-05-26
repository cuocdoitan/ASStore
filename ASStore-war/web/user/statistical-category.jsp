<%-- 
    Document   : statistical-category
    Created on : May 16, 2018, 4:34:06 PM
    Author     : TRAN HO QUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <div class="container">
        <div class="row">
            <div class="col-12 m-t-30">
                <h1 class="m-b-30 text-center">Statistical Total Post, Current Quantity, Total Quantity Sold Product Of Category</h1>
                <div class="flex-sb-m flex-w p-b-35 p-t-20">
                    <form method="post" action="<c:url value='/statisticals/listcate'/>">
                        <div class="flex-w">
                            <p style="color: red">${error}</p>
                            <div class="">
                                <p>Year</p>
                                <select class="m-r-10" name="yearFrom">
                                    <c:forEach begin="2000" end="2018" var="y">
                                        <option <c:if test="${y == yearFrom}">selected</c:if>>${y}</option> 
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="m-r-20">
                                <p>Month From</p>
                                <select class="" name="monthFrom">
                                    <c:forEach begin="1" end="12" var="mf">
                                        <option <c:if test="${mf == monthFrom}">selected</c:if>>${mf}</option> 
                                    </c:forEach>  
                                </select>
                            </div>
                            <div class="m-r-20">
                                <p>Day From</p>
                                <select class="" name="dayFrom">
                                    <c:forEach begin="1" end="31" var="df">
                                        <option <c:if test="${df == dayFrom}">selected</c:if>>${df}</option> 
                                    </c:forEach> 
                                </select>
                            </div>
                            <br/>
                            <div class="m-r-20">
                                <p>Month To</p>
                                <select class="" name="monthTo">
                                    <c:forEach begin="1" end="12" var="mt">
                                        <option <c:if test="${mt == monthTo}">selected</c:if>>${mt}</option> 
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="">
                                <p>Day To</p>
                                <select class="m-r-20" name="dayTo">
                                    <c:forEach begin="1" end="31" var="dt">
                                        <option <c:if test="${dt == dayTo}">selected</c:if>>${dt}</option> 
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <button style="width: 200px;" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
                            Statistical
                        </button>
                        <br/>

                    </form>
                    <c:if test="${sessionScope.role == roleId}">
                        <button style="width: 200px;" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" id="buttonPrintReport">Print Report</button>
                    </c:if>
                </div>
                <div class="flex-sb-m flex-w p-b-35 p-t-20">

                </div>
                <div class="flex-sb-m flex-w p-b-35 p-t-20">

                </div>

                <table border="1" style="width: 100%" class="">
                    <thead>
                        <tr>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Category</th>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Total Post Product</th>
                            <!--                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Total Quantity Product</th>-->
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Current Quantity Product</th>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Total Quantity Sold Product</th>             
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Date</th>
                        </tr>
                    </thead>
                    <c:forEach items="${listStatisticDetails}" var="statistic">
                        <tbody>
                            <tr>
                                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                    ${statistic.name}
                                </td>
                                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                    ${statistic.totalProduct}
                                </td>
                                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                    ${statistic.currentQuantityProducts}
                                </td>
                                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                    ${statistic.soldQuantity}
                                </td>
                                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                    ${statistic.dateFrom} - ${statistic.dateTo}
                                </td>
                            </tr>   
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <br/>
    <br/>
    <br/>
    <div id="Chart"></div>

    <script>
        Highcharts.chart('Chart', {
            chart: {
                type: 'column'
            },
            title: {
                text: 'Statistical Product of Category'
            },
            xAxis: {
                categories: [${categoryListChart}]
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Total Quantity Product'
                }
            },
            tooltip: {
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
                shared: true
            },
            plotOptions: {
                column: {
                    stacking: 'percent'
                }
            },
            series: [{
                    name: 'Current Quantity Product',
                    data: [${totalCurrentChart}]
                }, {
                    name: 'Total Quantity Sold Product',
                    data: [${totalQuantitySoldProductChart}]
                }]
        });
    </script>
</t:layout>
