$(window).scroll(function() {
    var windowY = $(window).height();
    var scrolledY = $(window).scrollTop();
    var image_url = '';

    if (scrolledY > 600px) {
    image_url ='../img/408916.jpg';
   	 backgroundChange(image_url);
    }
});


function backgroundChange(img_url){
  $("body").css("background-image", "url(" + img_url + ")");
}
