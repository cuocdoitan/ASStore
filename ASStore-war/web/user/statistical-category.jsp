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
                <h1 class="m-b-30 text-center">Statistical Total Post Product, Total Quantity Product, Current Quantity Product, Total Quantity Sold Product Of Category</h1>
                <div class="flex-sb-m flex-w p-b-35 p-t-20">
                    <form method="post" action="<c:url value='/statisticals/listcate'/>">
                        <div class="flex-w">

                            <div class="">
                                <p>Year</p>
                                <select class="" name="yearFrom">
                                    <option>2018</option>           
                                </select>
                            </div>
                            <div class="">
                                <p>Month From</p>
                                <select class="" name="monthFrom">
                                    <option>1</option>
                                    <option>2</option> 
                                    <option>3</option> 
                                    <option>4</option> 
                                    <option>5</option> 
                                    <option>6</option> 
                                    <option>7</option> 
                                    <option>8</option> 
                                    <option>9</option> 
                                    <option>10</option> 
                                    <option>11</option> 
                                    <option>12</option> 
                                </select>
                            </div>
                            <div class="">
                                <p>Day From</p>
                                <select class="" name="dayFrom">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option>12</option>
                                    <option>13</option>
                                    <option>14</option>
                                    <option>15</option>
                                    <option>16</option>
                                    <option>17</option>
                                    <option>18</option>
                                    <option>19</option>
                                    <option>20</option>
                                    <option>21</option>
                                    <option>22</option>
                                    <option>23</option>
                                    <option>24</option>
                                    <option>25</option>
                                    <option>26</option>
                                    <option>27</option>
                                    <option>28</option>
                                    <option>29</option>
                                    <option>30</option>
                                    <option>31</option>
                                </select>
                            </div>
                            
                            <div class="">
                                <p>Month To</p>
                                <select class="" name="monthTo">
                                    <option>1</option>
                                    <option>2</option> 
                                    <option>3</option> 
                                    <option>4</option> 
                                    <option>5</option> 
                                    <option>6</option> 
                                    <option>7</option> 
                                    <option>8</option> 
                                    <option>9</option> 
                                    <option>10</option> 
                                    <option>11</option> 
                                    <option>12</option> 
                                </select>
                            </div>
                            
                            <div class="">
                                <p>Day To</p>
                                <select class="" name="dayTo">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option>12</option>
                                    <option>13</option>
                                    <option>14</option>
                                    <option>15</option>
                                    <option>16</option>
                                    <option>17</option>
                                    <option>18</option>
                                    <option>19</option>
                                    <option>20</option>
                                    <option>21</option>
                                    <option>22</option>
                                    <option>23</option>
                                    <option>24</option>
                                    <option>25</option>
                                    <option>26</option>
                                    <option>27</option>
                                    <option>28</option>
                                    <option>29</option>
                                    <option>30</option>
                                    <option>31</option>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <button style="width: 200px;" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
                            Statistical
                        </button>
                    </form>
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
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Total Quantity Product</th>
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
                                    ${statistic.totalQuantityProduct}
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
