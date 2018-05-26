/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#cardSecurity").keydown(function (e) {
  var cardS = $("#cardSecurity").val().replace(/ /g, "");
  var charCode = (typeof e.which === "undefined") ? e.keyCode : e.which;
  var charStr = String.fromCharCode(charCode);
  var exclude = [37, 38, 39, 40, 8];
  if (!(/\d/.test(charStr)) && exclude.indexOf(charCode) === -1 || cardS.length === 3 && exclude.indexOf(charCode) === -1) {
    e.preventDefault();
  }
});

$("#cardNumber").keydown(function (e) {
  var cardN = $("#cardNumber").val().replace(/ /g, "");
  var charCode = (typeof e.which === "undefined") ? e.keyCode : e.which;
  var charStr = String.fromCharCode(charCode);
  var exclude = [37, 38, 39, 40, 8];
  if (!(/\d/.test(charStr)) && exclude.indexOf(charCode) === -1 || cardN.length === 16 && exclude.indexOf(charCode) === -1) {
    e.preventDefault();
  }
});

$("#cardNumber").keyup(function (e) {
  var cardN = $("#cardNumber").val().replace(/ /g, "");
  var newN = "";
  var counter = 0;
  for (var i = 0; i < cardN.length; i++) {

    if (counter !== 4) {
      newN += cardN[i];
    } else {
      counter = 0;
      newN += " " + cardN[i];
    }

    counter++;
  }
  $("#cardNumber").val(newN);
});

$("#phone").keydown(function (e) {
  var cardS = $("#phone").val().replace(/ /g, "");
  var charCode = (typeof e.which === "undefined") ? e.keyCode : e.which;
  var charStr = String.fromCharCode(charCode);
  var exclude = [37, 38, 39, 40, 8];
  if (!(/\d/.test(charStr)) && exclude.indexOf(charCode) === -1 || cardS.length === 11 && exclude.indexOf(charCode) === -1) {
    e.preventDefault();
  }
});

function addCoupon(url, detailId) {
  swal({
    text: 'Enter coupon for this product',
    content: {
      element: "input",
      attributes: {
        placeholder: "Type your coupon",
        value: $("#coupon-p-" + detailId).attr("value"),
      }
    }
  }).then(function(coupon) {
    if (coupon !== null) {
      $.post(url, { detailid: detailId, coupon: coupon }).done(function(data) {
        if (data.status === "success") {
          swal("Success !", "Used coupon: " + coupon, "success");
          $("#coupon-p-" + detailId).attr("value", coupon);
          applyCoupon(detailId, data.percentage);
        }
        else if (data.status === "expired") {
          swal("Error !", "Coupon: " + coupon + " has expired !", "error");
        }
        else if (data.status === "noapply") {
          swal("Error !", "Coupon: " + coupon + " can't apply for this product !", "error");
        }
        else if (data.status === "sameowner") {
          swal("Error !", "You can't use your own coupon !", "error");
        }
        else {
          swal("Error !", "Coupon: " + coupon + " not found !", "error");
        }
      }).fail(function() {
        swal("Removed coupon");
        unapplyCoupon(detailId);
      });
    }
  });
  //$('.sweet-alert input[type=text]:first' ).val($("#coupon-p-" + detailId).attr("value"));
}

function unapplyCoupon (id) {
  var url = $("input[name='getPriceURL']").val();
  $.get(url, { detailid: id }).done(function(data) {
    $("#ps-" + id).html(data.price);
    $("#ds-" + id).html("0");
  });
  $("#coupon-pe-" + id).attr("value", "");
  updateTotal();
}

function applyCoupon (id, percentage) {
  var price = parseInt($("#ps-" + id).html());
  var newPrice = price - (price * percentage / 100);
  $("#ps-" + id).html(newPrice);
  $("#ds-" + id).html(percentage);
  updateTotal();
}

function updateTotal () {
  var url = $("input[name='getTotalURL']").val();
  $.get(url, {}).done(function(data){
    $("#ctotal").html(data.price);
  });
}