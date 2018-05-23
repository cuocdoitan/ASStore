/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addToCart(productid, unitprice) {
  var url = $("input[name='addCartURL']").val();
  var quantity = $("input[name='num-product']").val();
  $.post(url, {productid: productid, quantity: quantity, unitprice: unitprice}).done(function (data) {
    if (data.status === "success") {
      swal("Success !", "Product added to cart !", "success");
    } else {
      swal("Oops !", "Can't add product to cart !", "error");
    }
  });
}

function removeFromCart(url, productId, quantity) {
  swal("Are you sure to remove this product?", {
    buttons: {
      cancel: "No",
      amount: {
        text: "Remove amount",
        value: "amount",
      },
      all: true,
    },
  }).then(function (value) {
    switch (value) {

      case "all":
        window.location.href = url + productId;
        break;

      case "amount":
        swal("How many do you want to remove?", {
          content: {
            element: "input",
            attributes: {
              placeholder: "Type your amount"
            }
          }}).then(function (amount) {
          if (amount) {
            if (amount > quantity) {
              swal("The amount can't be greater than the current quantity in cart")
            }
            else {
              window.location.href = url + productId + "&amount=" + amount;
            }
          }
        });
        break;

      default:
        break;
    }
  });
}