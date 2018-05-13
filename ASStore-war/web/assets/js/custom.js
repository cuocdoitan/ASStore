var pathArray = window.location.pathname.split('/');  
var urlBase = window.location.protocol + "//" + window.location.host + "/" + pathArray[1];

function approveProductAndReloadPage_admin(id){
    $.ajax({
        url: urlBase + '/admin/products/approve',
        type: 'post',
        data: {
            id : id
        },
        success: function (data, textStatus, jqXHR) {
            $("#listApprovingProductContent_admin").html(data);
        }
    });
}


function searchProductAndReloadPage_admin(){
    $.ajax({
        url: urlBase + '/admin/products/list',
        type: 'get',
        data: {
            search : $("input[name=search]").val(),
            SearchBy : $("select[name=SearchBy]").val()
        },
        success: function (data, textStatus, jqXHR) {
            $("#listProductContent_admin").html(data);
        }
    });
}


    /*
 * [ AUTO complete anime ]
 */


  $("#anime_name").autocomplete({
    source: function (request, response) {
      $.ajax({
        url: urlBase + "/animeApi",
        dataType: "json",
        data: {
          name: $("#anime_name").val()
        },
        success: function (data) {
          response(data);
        }
      });
    },
    select: function (event, ui){
        event.target.value = "dasdasdasdsda"
    }
  }).data("ui-autocomplete")._renderItem = function (ul, item) {
    var li = $('<li>'),
            img = $('<img>');

    img.attr({
      src: urlBase + '/assets/img/anime/' + item.picture,
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
    
    var old = $("#anime_name").val();
    var newa = "";
    var pickNew = false;
    
    a.hover(function () {
      a.css("border", "none");
      $("#anime_name").val(item.name);
      newa = item.name;
    }, function(){
        if(pickNew) {
            $("#anime_name").val(item.name);
        }
        else {
            $("#anime_name").val(old);
        }
    });
    
    a.click(function() {
        pickNew = true;
    });

    a.append(img).append("<span style='margin-left: 20px'>" + item.name + "</span>");
    return li.appendTo(ul);
  };




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
    var imageIndex = $(file.previewElement).index();
    var hiddenTagName = "image" + imageIndex;
    console.log(hiddenTagName)
    $("input[name="+hiddenTagName+"]").val('');
    
    var imgs = $(".image");
    var links = [];
    for(var i = 0; i < imgs.length; i++) {
        if (imgs[i].value) {
            links.push(imgs[i].value);
        }
    }
    
    console.log(links)
    
    for(var i = 0; i < imgs.length; i++) {
        var hiddenTagName = "image" + (i + 1);
        $("input[name="+hiddenTagName+"]").val(links[i]);
    }
    
//    var deletedIndex = imageIndex - 1;
//    for(;deletedIndex <= 4;deletedIndex++){
//        var nextIndex = deletedIndex + 1;
//        var hiddenTagName_next = "image" + nextIndex;
//        var hiddenTagName_current = "image" + deletedIndex;
//        var nextImageName = $("input[name="+hiddenTagName_next+"]").val();
//        $("input[name="+hiddenTagName_current+"]").val(nextImageName);
//    }
    var _ref;
    return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
  },
  init: function () {
    var imgs = $(".image");
    for(var i = 0; i < imgs.length; i++) {
        if (imgs[i].value) {
            var thisDropzone = this;
            var mockFile = { name: imgs[i].value.split('/').pop(), size: 12345, type: 'image/jpeg' };
            thisDropzone.emit("addedfile", mockFile);
            thisDropzone.emit("complete", mockFile);
            thisDropzone.emit("thumbnail", mockFile, imgs[i].value);
        }
    }
    
    this.on("success", function (file, response) {
      console.log(response.filename);
      var imageIndex = $(file.previewElement).index();
      var hiddenTagName = "image" + imageIndex;
      $("input[name="+hiddenTagName+"]").val(response.filename);
    });
    this.on("maxfilesexceeded", function (file) {
      alert("Cannot upload more image");
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
