/* 
 * ************************************USER*************************************
 */


$("#formInsertProduct").submit(function (e) {

});

$(function () {
    $('#categoryRadioField input[type=radio]').change(function () {
        $("input[name=category]").val($(this).val());
    });
});


$(function () {
    $('#newRating input[type=radio]').change(function () {
        var rating = $(this).val();
        $.ajax({
            url: $("#urlProject").val() + '/products/createNewRating',
            type: 'post',
            data: {
                productId: $("input[name=productId]").val(),
                userId: $("input[name=userId]").val(),
                rating: rating
            },
            success: function (data, textStatus, jqXHR) {
                $("#RatingProductListContent_User").html(data);
            }
        });
    });

    $('#editRating input[type=radio]').change(function () {
        var rating = $(this).val();
        $.ajax({
            url: $("#urlProject").val() + '/products/editRating',
            type: 'post',
            data: {
                ratingId: $("input[name=ratingId]").val(),
                rating: rating
            },
            success: function (data, textStatus, jqXHR) {
                $("#RatingProductListContent_User").html(data);
            }
        });
    });

    $('#cancelRating').click(function () {
        $.ajax({
            url: $("#urlProject").val() + '/products/cancelRating',
            type: 'post',
            data: {
                ratingId: $("input[name=ratingId]").val()
            },
            success: function (data, textStatus, jqXHR) {
                $("#RatingProductListContent_User").html(data);
            }
        });
    });
});



function validateProductSubmit_user() {
    clearErrorTexts();
    var name = $("input[name=name]").val();
    var quantity = $("input[name=quantity]").val();
    var price = $("input[name=price]").val();
    var anime = $("input[name=anime]").val();
    var description = $("textarea[name=description]").val();
    var image1 = $("input[name=image1]").val();
    $.get($("#urlProject").val() + "/products/validateProductSubmit",
            {
                name: name,
                quantity: quantity,
                price: price,
                anime: anime,
                description: description,
                image1: image1
            },
            function (result) {
                $("#errName").text(result.errName);
                $("#errQuantity").text(result.errQuantity);
                $("#errPrice").text(result.errPrice);
                $("#errAnime").text(result.errAnime);
                $("#errDescription").text(result.errDescription);
                $("#errImage").text(result.errImage);
                if (result.errName === "" && result.errQuantity === "" && result.errPrice === "" && result.errAnime === "" && result.errDescription === "" && result.errImage === "") {
                    if (confirm("Last check !")) {
                        $("#formSubmitProduct").submit();
                    }
                }
            }

    );
}


function clearErrorTexts() {
    $("#errName").text("");
    $("#errQuantity").text("");
    $("#errPrice").text("");
    $("#errAnime").text("");
    $("#errDescription").text("");
    $("#errImage").text("");
}


//$("#buttonSearchListProduct").click(function () {
//    $("#errAnime").text("");
//    var anime_name = $("input[name=anime_name]").val();
//    $.get($("#urlProject").val() + "/checkIfAnimeExist",
//            {
//                animeName: anime_name
//            },
//            function (result) {
//                var exist = result.exist;
//                if (exist === "false") {
//                    $("#errAnime").text("This anime isn't exist");
//                } else {
//                    //search product
//                    getProductList();
//                }
//            }
//    );
//});
//
//$("#pagination li").click(function (){
//    getProductList();
//});
//
//
//function getProductList() {
//    var productName = $("input[name=productName]").val();
//    var anime_name = $("input[name=anime_name]").val();
//    var minPrice = $("input[name=minPrice]").val();
//    var maxPrice = $("input[name=maxPrice]").val();
//    var category = $("input[name=category]").val();
//    var sorting = $("select[name=sorting]").val();
//    var page = $("#pagination li").text();
//    $.ajax({
//        url: $("#urlProject").val() + '/products_Search',
//        type: 'post',
//        data: {
//            productName: productName,
//            anime_name: anime_name,
//            minPrice: minPrice,
//            maxPrice: maxPrice,
//            category: category,
//            sorting: sorting,
//            page : page
//        },
//        success: function (data, textStatus, jqXHR) {
//            $("#ProductListContent_User").html(data);
//        }
//    });
//}


/* 
 * ************************************ADMIN*************************************
 */

$("#input_searchProduct_admin").keydown(function (e) {
    var key = e.which;
    if (key === 13) {
        searchProductAndReloadPage_admin();
    }
});


function approveProductAndReloadPage_admin(id) {
    $.ajax({
        url: $("#urlProject").val() + '/admin/products/approve',
        type: 'post',
        data: {
            id: id
        },
        success: function (data, textStatus, jqXHR) {
            $("#listApprovingProductContent_admin").html(data);
        }
    });
}


function searchProductAndReloadPage_admin() {
    $.ajax({
        url: $("#urlProject").val() + '/admin/products/list',
        type: 'get',
        data: {
            search: $("input[name=search]").val(),
            SearchBy: $("select[name=SearchBy]").val()
        },
        success: function (data, textStatus, jqXHR) {
            $("#listProductContent_admin").html(data);
        }
    });
}





