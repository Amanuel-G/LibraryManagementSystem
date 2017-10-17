<?php
	set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});
		

	try{
		include_once("../model/classBook.php");
/* 		include_once("../model/classPatron.php");
 */		include_once("../control/search.controller.php");
		include_once("../control/Home.controller.php");
		include_once("../index.php");
		
	}catch(ErrorException $e){
		include_once("model/classBook.php");
		/* include_once("model/classPatron.php"); */
		include_once("control/search.controller.php");
		include_once("index.php");
		include_once("control/Home.controller.php");
	}
	
	ob_start(); 
	
	$books=executeSearch();
	
	$no_of_results=$books[2];
	$content="<div class='blockTag'>";
	$content.="<div id='latest-books' class='home-page' >";
	$content.="<div class = 'RScontainer'>$no_of_results results found</div>";
	$content.=$books[0];
	
	$content.="<div class = 'RScontainer'>suggestions</div>";
	
	$content.=$books[1];
	
	$content.="</div>";
	$content.="</div>";
	$content.="</div>";
	
	$cont=$content;
	
	ob_end_flush();
	
?>