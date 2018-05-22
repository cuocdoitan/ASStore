/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addToCart(productid, unitprice) {
  var url = $("input[name='addCartURL']").val();
  var quantity = $("input[name='num-product']").val();
  $.post(url, {productid: productid, quantity: quantity, unitprice: unitprice}).done(function(data) {
    if (data.status === "success") {
      swal("Success !", "Product added to cart !", "success");
    }
    else {
      swal("Oops !", "Can't add product to cart !", "error");
    }
  });
}
