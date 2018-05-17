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
/*
 $("#productListSearch").submit(function (e){
 $.ajax({
 type: $(this).attr("method"),
 url: $(this).attr("action"),
 data: $(this).serialize(),
 success: function (data) {
 $("#ProductListContent_User").html(data);
 }
 });
 
 });
 */

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
                if (result.errName === "" && result.errQuantity === "" &&  result.errPrice === "" &&  result.errAnime === "" &&  result.errDescription === "" &&  result.errImage === "") {
                    $("#formSubmitProduct").submit();
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

function validateProductSearch_user() {

}
/* 
 * ************************************ADMIN*************************************
 */
function checkIfAnimeIsExist() {
    $.get($("#urlProject").val() + "/checkIfAnimeExist",
            {
                animeName: $("#anime_name").val()
            },
            function (result) {
                var exist = result.exist;
                if (exist === 'false') {
                    alert('');
                }
            }
    );
}

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





