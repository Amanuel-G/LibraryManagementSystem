<?php
	set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});

	session_start();
	
	include_once "model/page_data.class.php";
	$pageData=new PageData();
	if(isset($_SESSION['Target'])){
		$pageData->changeTarget($_SESSION['Target']);
	}
	$pageData->title="ILMS";
	
	$pageData->addStyles("jRating.jquery.css");
	$pageData->addscript("script.js");
	$pageData->addScripts("jquery.js");
	$pageData->addScripts("jRating.jquery.js");
	$pageData->addStyle("main.css");
	$pageData->addStyle("sliderMenu.css");
	$pageData->addStyle("content.css");
	$pageData->addStyle("footer.css");
	$pageData->addStyle("contentForDetail.css");
	$pageData->addStyle("contentForSearch.css");
	
	$pageData->addStyle("style.css");
	$pageData->addStyle("styles.css");
	$pageData->addStyle("search.css");
	$pageData->header=include "view/navigation.php";
	$pageData->sideBar=include "view/sidebar.html.php";
	$pageData->footer=include "view/footer.html.php";
	
	//adding the scripts at the bottom of the code
	
	$pageData->addBottomScript("changeBack.js");
	$pageData->addBottomScript("my.js");
	$pageData->addBottomScript("ajaxAddComment.js");
	$pageData->addScript("ajaxDeleteComment.js");
	
	if (isset($_GET['page'])){
		$target=$_GET['page'];
		if ($target=="logout"){
			
			unset($_SESSION['Target']);
			unset($_SESSION['currentPatronID']);
			
			
			$target="login";
			
		/* 	$_SESSION['Target']=$target; */
		}
		
	}else{
		$target=$pageData->target;
	}
	
	$page=include "view/$target.html.php";
	$page=include "view/template.php";
	echo $page;
	

	
			
			?>
			
		
			

