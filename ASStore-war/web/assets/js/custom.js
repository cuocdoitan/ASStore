/*
 * [ Product rating ]
 */

$(".product-rating span").hover(function () {
  var rateMax = $(this).index() + 2;
  //console.log("asdasd")
  for (var i = 0; i < rateMax; i++) {
    if (!$(".product-rating span:nth-child(" + i + ")").hasClass("checked")) {
      $(".product-rating span:nth-child(" + i + ")").addClass("checked")
    }
  }
}, function () {
  var rateMax = $(this).index() + 2;
  //console.log("asdasd")
  for (var i = 0; i < rateMax; i++) {
    if ($(".product-rating span:nth-child(" + i + ")").hasClass("checked")) {
      $(".product-rating span:nth-child(" + i + ")").removeClass("checked")
    }
  }
});

/*
 * Price slider
 */

var filterBar = document.getElementById('price-bar');

noUiSlider.create(filterBar, {
  start: [50, 200],
  connect: true,
  range: {
    'min': 50,
    'max': 200
  }
});

var skipValues = [
  document.getElementById('value-lower'),
  document.getElementById('value-upper')
];

filterBar.noUiSlider.on('update', function (values, handle) {
  skipValues[handle].innerHTML = Math.round(values[handle]);
});

/*
 * [ AUTO complete anime ]
 */
$.getJSON('animeApi', function (data) {
  $("#anime_name").autocomplete({
    source: function (request, response) {
      $.ajax({
        url: "animeApi",
        dataType: "json",
        data: {
          name: $("#anime_name").val()
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
      src: 'assets/img/anime/' + item.picture,
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

    li.data("ui-autocomplete-item", item.id)
    li.append('<a href="#">');
    
    
    var a = li.find('a');
    a.css({
      "width": "100%",
      "height": "100px",
      "display": "block",
      "padding": "0",
      "margin": "0"
    });
    
    a.append(img).append("<span style='margin-left: 20px'>" + item.name + "</span>");

    return li.appendTo(ul);
  };

});
