<?php
	set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});
		/* include "../index.php"; */

	try{
		include_once("../model/classBook.php");
		include_once("../model/classPatron.php");
		include_once("../control/search.controller.php");
		include_once("../control/Home.control.php");
		include_once("../index.php");
		
	}catch(ErrorException $e){
		include_once("model/classBook.php");
		include_once("model/classPatron.php");
		include_once("control/search.controller.php");
		include_once("index.php");
		include_once("control/Home.controller.php");
	}
	
	ob_start(); 
	
	$books=executeSearch();
	$result_books=$books[0];
	$suggested_books=$books[1];
	$no_of_results=count($result_books);
	$no_of_suggestion=count($suggested_books);
	//get the rating for the bookContainer
	
	
	
	//create the blockTag 
	
	$content="<div class='blockTag'>
	<div class = 'RScontainer'>$no_of_results results</div>";
	
	
	//loop through the result for books
	for ($i=0;$i<$no_of_results;$i++){
		$content.="<div class='bookContainer'>
		<div class='bookImage'>";
		$bookID=$result_books[$i]->getBookID();
		$bookDet=getLatestBooks($bookID);
		$bookCover=$result_books[$i]->getBookCover();
		
		
		if ($bookCover!=null){
			$content.="<img src = 'img/defaultCover.jpg' width = 75 height = 100/>";
		}else{
			$src="data:image/jpg;base64,'".base64_encode($bookCover);
			$img="<img src =".$src." width = 75 height = 100/>";
			
			$content.=$img;
		}
		
		$content.="</div>
		<div class ='bookContent'>";
		
		$titleString=$result_books[$i]->getTitle();
		if (strlen($titleString) > 17){
				$titleString = substr($titleString,0,17)."..";
			}
		
		$content.="<p class='Title'><b><a href='index.php?page=bookDetail&bookID=".$result_books[$i]->getBookId()."'class = 'titleLink'>".$titleString."</a></b></p>";
		
		
		
		$content.="<p class='Edition'>".$result_books[$i]->getEdition()."Edition</p>";
		
		//to insert the rating
		$bookId=$result_books[$i]->getBookID();
		$bookRate=getAverage($bookId);
		$content.="<div class='rating' data-average=$bookRate data-id=$bookId>
		</div>
		
		
		</div>
		</div>";
		
	}
	
	
	
	//create a box for suggestion
	$content.="<div class = 'RScontainer'>$no_of_suggestion suggestions</div>";
	
	
	
		for ($i=0;$i<$no_of_suggestion;$i++){
		$content.="<div class='bookContainer'>
		<div class='bookImage'>";
		
		$bookCover=$suggested_books[$i]->getBookCover();
		
		$bookID=$result_books[$i]->getBookID();
		$bookDet=getLatestBooks($bookID);
		/* print_r($bookID); */
		
		
		if ($bookCover!=null){
			$content.="<img src = 'img/defaultCover.jpg' width = 75 height = 100/>";
		}else{
			$src="data:image/jpg;base64,'".base64_encode($bookCover);
			$img="<img src =".$src." width = 75 height = 100/>";
			
			$content.=$img;
		}
		
		$content.="</div>
		<div class ='bookContent'>";
		
		$titleString=$suggested_books[$i]->getTitle();
		if (strlen($titleString) > 17){
				$titleString = substr($titleString,0,17)."..";
			}
		
		$content.="<p class='Title'><b><a href='bookDetail.php?bookID=".$suggested_books[$i]->getBookId()."'class = 'titleLink'>".$titleString."</a></b></p>";
		$bookId=$suggested_books[$i]->getBookID();
		$bookRate=getAverage($bookId);
		
		$content.="<p class='Edition'>".$suggested_books[$i]->getEdition()."Edition</p>
		
		
		
		<div class='rating' data-average=$bookRate
		data-id=$bookId></div>
		
		
		<div class='datasSent'>
			Datas sent to the server :
			<p></p>
		</div>
		<div class='serverResponse'>
			Server response :
			<p></p>
		</div>
		
		</div>
		</div>";
		
	}
	$content.="</div>";
	
	
	$cont=$content;
	
	ob_end_flush();
	
?>