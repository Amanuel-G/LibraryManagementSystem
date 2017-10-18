<?php

//called from the boundary searchBook.php
//calls the executeQuery method from classSearchControl.php

ob_start();

include_once ('classSearchControl.php');
session_start(); 



//called in the view(searchBook.php) file to execute Search
function executeSearch(){
	$myControl = new SearchController();
	$currentPatronID = $_SESSION['currentPatronID'];
	$currentPatron = constructPatron($currentPatronID);
	if (isset($_GET['searchKey'])){
		$searchType = $_GET['searchType'];
		if ($searchType == 'Author'){
			$myControl->executeSearchByAuthor($currentPatron, $_GET['searchKey']);

		}
		else if ($searchType == 'Title' & trim($_GET['searchKey']) != ""){
			$myControl->executeSearchByTitle($currentPatron, $_GET['searchKey']);

		}
		else if ($searchType == 'iSBN'){
			$myControl->executeSearchByISBN($currentPatron, $_GET['searchKey']);
		}

	}

}


ob_end_flush();

?>