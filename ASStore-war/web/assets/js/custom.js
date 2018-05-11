/*
 * [ IMAGE UPLOAD ]
 */
Dropzone.autoDiscover = false;
$("#imageUpload").dropzone({
  maxFiles: 4,
  addRemoveLinks: true,
  acceptedFiles: ".png,.jpg,.jpeg",
  removedfile: function (file) {
    console.log($(file.previewElement).index());
    var _ref;
    return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
  },
  init: function () {
    this.on("success", function (file, response) {
      console.log(response);
    });
    this.on("maxfilesexceeded", function (file) {
      alert("No more images please!");
      this.removeFile(file);
    });
  }
});

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

    a.hover(function () {
      a.css("border", "none");
    })

    a.append(img).append("<span style='margin-left: 20px'>" + item.name + "</span>");

    return li.appendTo(ul);
  };

});

/*
 * [ ANIME LIST ]
 */
function expandAnime(e) {
  if ($(e).siblings(".anime-description").hasClass("less")) {
    $(e).siblings(".anime-description").removeClass("less");
    $(e).text("Read less");
  } else {
    $(e).siblings(".anime-description").addClass("less");
    $(e).text("Read more");
  }
}

function showCreditCard(e) {
  if ($(e).siblings(".card-details").css("height") == "0px") {
    $(e).siblings(".card-details").css("height", "auto");
    $("input[name='method']").val('card');
    $(e).text("Pay in cash");
  } else {
    $(e).siblings(".card-details").css("height", "0px");
    $("input[name='method']").val('cash');
    $(e).text("Pay by credit card");
  }
}