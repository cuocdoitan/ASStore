<%-- 
    Document   : statistical-category
    Created on : May 16, 2018, 4:34:06 PM
    Author     : TRAN HO QUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<t:pureLayout>
    <div class="container">
        <div class="row">
            <div class="col-12 m-t-30">
                <h1 class="m-b-30 text-center">Thống Kê Số Lượng Đăng Bán, Mua Product Từng Category</h1>
                <div class="flex-sb-m flex-w p-b-35 p-t-20">
                    <div class="flex-w">
                        <div class="">
                            <p>Năm</p>
                            <select class="" name="year">
                                <option>2018</option>           
                            </select>
                        </div>

                        <div class="">
                            <p>Tháng</p>
                            <select class="" name="month">
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
                            <p>Ngày</p>
                            <select class="" name="day">
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
                </div>
                <div class="flex-sb-m flex-w p-b-35 p-t-20">

                </div>
                <div class="flex-sb-m flex-w p-b-35 p-t-20">

                </div>

                <table border="1" style="width: 100%" class="">
                    <thead>
                        <tr>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Category</th>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Post Product</th>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Quantity Product</th>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Số lượng product được bán</th>
                            <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Ngày</th>
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
                                    50
                                </td>
                                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                    ${statistic.date}
                                </td> 
                            </tr>   
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div id="Chart"></div>

    <script>
        $(function () {
            Highcharts.chart('Chart', {
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Product\' number of Category'
                },
                xAxis: {
                    type: 'category',
                    labels: {
                        rotation: -45,
                        style: {
                            fontSize: '18px',
                            fontFamily: 'Verdana, sans-serif'
                        }
                    }
                },
                yAxis: {
                    title: {
                        text: 'Number of products'
                    }
                },
                series: [
                    {
                        name: 'number of products',
                        data: [],
                        dataLabels: {
                            enabled: true,
                            rotation: 0,
                            color: '#FFFFFF',
                            align: 'right',
                            format: '{point.y:.1f}', // one decimal
                            y: 20, // 10 pixels down from the top
                            style: {
                                fontSize: '20px',
                                fontFamily: 'Verdana, sans-serif'
                            }
                        }
                    }
                ]
            });
        });
        //document.getElementById("Chart").value = myChart;
    </script>
</t:pureLayout>
