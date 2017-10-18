<?php
//called from the boundary bookDetail.php
//calls the executeQuery method from classDetailControl.php
try{
		include_once ('../model/classDetailControl.php');
	}catch(ErrorException $e){
		include_once ('model/classDetailControl.php');
	}


function executeDetail(){
	$detailControl = new DetailController();

	if (isset($_GET['bookID'])){
		
		$_SESSION['currentBookID'] = $_GET['bookID'];
		$book=$_SESSION['currentBookID'];
		$result=$detailControl->executeQuery($book);
		return $result;
	}
	
}

	



?>