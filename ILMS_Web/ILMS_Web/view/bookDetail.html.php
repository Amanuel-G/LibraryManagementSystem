<?php
	set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});
	
	try{
			//a php used to execute the detail of a book(including rate)
		include_once ('../include/detail.php');
		//a php used to execute the reviews of a book(comments)
		include_once ('../include/review.php');

	}catch(ErrorException $e){
			//a php used to execute the detail of a book(including rate)
		include_once ('include/detail.php');
		//a php used to execute the reviews of a book(comments)
		include_once ('include/review.php');
	}
	
	
	$cont="<div class='details'>";
	$cont.="<div class='blockTag'>";
	$cont.="<div id='latest-books' class='home-page' >";
	ob_start(); 
	$cont.=executeDetail(); 
	ob_end_flush();
	
	ob_start();
	$cont.=executeReview(); 
	ob_end_flush();
	$cont.="</div>";
	

	
	
	
	$cont.="</div>";
	$cont.="</div>";
	
	
	$pageData->content=$cont;
	$pageData->script.="<script type='text/javascript'>
		 $(function(){
			$('.rating').jRating({
				decimalLength : 1,
				rateMax:5 ,
				length:5,
 				phpPath: 'php/jRating.php' 
				
			});
			
		 })
	
	</script>";
	
	
?>