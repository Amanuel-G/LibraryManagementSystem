<link rel="stylesheet" type="text/css" href="engine0/style.css" />
	<script type="text/javascript" src="engine0/jquery.js"></script>
<?php

		$var="
		<div id='slider'>
		<div class='blockTag'<div id='wowslider-container0'>
	<div class='ws_images'><ul>
		<li><img src='data0/images/AAit-Slider3.jpg' alt='image-slider-3' title='image-slider-3' id='wows0_0'/></li>
		<li><img src='data0/images/AAit-Slider4.jpg' alt='image-slider-4' title='image-slider-4' id='wows0_1'/></li>
		<li><img src='data0/images/AAit-Slider5.jpg' alt='image-slider-5' title='image-slider-5' id='wows0_2'/></li>
		<li><img src='data0/images/AAit-Slider2.jpg' alt='image-slider-2' title='image-slider-2' id='wows0_4'/></li>
		<li><a><img src='data0/images/AAit-Slider1.jpg' title='image-slider-1' id='wows0_3'/></a></li>
	</ul></div>
	<div class='ws_bullets'><div>
		<a href='#' title='image-slider-3'><span><img src='data0/tooltips/AAit-Slider3.jpg' alt='image-slider-3'/>1</span></a>
		<a href='#' title='tmEleven'><span><img src='data0/tooltips/AAit-Slider4.jpg' alt='image-slider-4'/>2</span></a>
		<a href='#' title='image-slider-5'><span><img src='data0/tooltips/AAit-Slider5.jpg' alt='image-slider-5'/>3</span></a>
		<a href='#' title='image-slider-1'><span><img src='data0/tooltips/AAit-Slider1.jpg' alt='image-slider-1'/>4</span></a>
		<a href='#' title='image-slider-2'><span><img src='data0/tooltips/AAit-Slider2.jpg' alt='image-slider-2'/>5</span></a>
	</div></div><div class='ws_script' style='position:absolute;left:-99%'><a>wowslider</a> by WOWSlider.com v8.7</div>
	<div class='ws_shadow'></div>
	</div>
	</div>
	</div>";	
		
	$pageData->addSliderScript('jquery.js');	
	$pageData->addBottomSliderScript('wowslider.js');
	$pageData->addBottomSliderScript('script.js');

$pageData->sideBar="";
$pageData->content=$var;
?>