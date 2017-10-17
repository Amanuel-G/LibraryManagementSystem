<?php


	//change Warning to errors inorder to deal with them as exceptions
	set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});

	if (!(isset($_SESSION['currentPatronID']))){
		header("Location: index.php");
	}
	try{
		include "control/Home.controller.php";
	}catch(ErrorException $e){
		include "../control/Home.controller.php";}
	
	
	
	if (isset($_POST['new-image'])){
	
		include "utility/connection.php";

		upload($_SESSION['currentPatronID']);
	}

	$names=getAverage(11111);
	//get the latest books
	$books=getLatestBooks();
	
	
	//create an array to recieve the list of books
	$booksimg=$books[1];
	$booksTitle=$books[0];
	$bookRate=$books[2];
	$bookID=$books[3];
	
	$cont="
	<div class='blockTag'>
		<div id='latest-books' class='home-page' >
			<h1>Latest Books</h1>";
			
			  $i=0;
			  while($i<count($booksimg)){
				  
				  $cont.="
				<div class='sameBook'> 
					  <div class='cover'>
						<div id='bookcvr'>
							$booksimg[$i] 
						</div> 
					  </div>
					
			
					<div class='detail' id='bookstitle'>
						<p ><a href='index.php?page=bookDetail&bookID=$bookID[$i]'>$booksTitle[$i] </a></p>
					</div>
				</div>
				";
				$i+=1;  
			  }
			  //end the latest books div
			  $cont.="</div>";
			  $cont.="</div>";
			  
		
	if (isset($_GET['search'])){
		try{
			include_once "../view/searchResults.html.php";
		}catch (ErrorException $e){
			include_once "view/searchResults.html.php";
		}
	
	}
	if (isset($_POST['reserve'])){
		include "view/bookReserve.html.php";
	}
	if (isset($_POST['changeMyPass'])){
		include "view/changePassword.html.php";
	}
	
	$pageData->content=$cont;
	


	$pageData->script.="<script type='text/javascript'>
		 $(function(){
			$('.rating').jRating({
				decimalLength : 1,
				rateMax:5 ,
				length:5,
 				isDisabled:true, 
 				phpPath: 'php/jRating.php' 
				
			});
			
		 })
	
	</script>";
?>

