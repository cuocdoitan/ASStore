/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("#coupon_product").autocomplete({
  source: function (request, response) {
    $.ajax({
      url: $("#couponProductApi").val(),
      dataType: "json",
      data: {
        name: $("#coupon_product").val()
      },
      success: function (data) {
        response(data);
      }
    });
  }
}).data("ui-autocomplete")._renderItem = function (ul, item) {
  var li = $('<li>'),
          img = $('<img>');

  img.attr({
    src: $("#productImageURL").val() + item.picture,
    alt: item.name
  });

  li.css({
    "height": "100px",
    "width": "100%"
  });

  img.css({
    "width": "80px",
    "height": "100px"
  });

  li.data("ui-autocomplete-item", item.id);
  li.append('<p>');

  var a = li.find('p');
  a.css({
    "width": "100%",
    "height": "100px",
    "display": "block",
    "padding": "0",
    "margin": "0"
  });

  var old = $("#coupon_product").val();
  var newa = "";
  var pickNew = false;

  a.hover(function () {
    a.css("border", "none");
    $("#coupon_product").val(item.name);
    newa = item.name;
  }, function () {
    if (pickNew) {
      $("#coupon_product").val(item.name);
      $("input[name='productId']").val(item.id);
    } else {
      $("#coupon_product").val(old);
    }
  });

  a.click(function () {
    pickNew = true;
  });

  a.append(img).append("<span style='margin-left: 20px'>" + item.name + "</span>");
  return li.appendTo(ul);
};