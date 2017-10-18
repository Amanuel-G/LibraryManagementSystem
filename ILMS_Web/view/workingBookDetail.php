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
	
	?>
	
	
	<div class='blockTag'>
	<div id = 'resultContainer'><?php /*calls method to show detail of a book after a search result */ob_start(); executeDetail(); ob_end_flush();?></div>
	
	<div id = "reviewContainer"><?php ob_start(); executeReview(); ob_end_flush();?></div>
	