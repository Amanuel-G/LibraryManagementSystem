<?php

//called from the boundary bookDetail.php
//calls the executeQuery method from classReviewControl.php
try{
		include_once ('../model/classReviewControl.php');
	}catch(ErrorException $e){
		include_once ('model/classReviewControl.php');
	}


function executeReview(){
	$reviewControl = new ReviewController();
	/* if (isset($_GET['bookID'])){
		$reviewControl->executeQuery($_GET['bookID']);
	} */
	$bk=$_SESSION['currentBookID'];
	if (isset($bk)){

		$result=$reviewControl->executeQuery($bk);
		return $result;
	}
	
	
}

?>
