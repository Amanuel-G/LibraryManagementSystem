<?php
	ob_start();

include_once ('model/classSearchControl.php');



function getTerm(){
	return $_GET['searchKey'];
}
//called in the view(searchBook.php) file to execute Search
function executeSearch(){
	$myControl = new SearchController();
	
	$currentPatronID = $_SESSION['currentPatronID'];
	
	$currentPatron = constructPatron($currentPatronID);
	if (isset($_GET['searchKey'])){
		$searchType = $_GET['searchType'];
		if ($searchType == 'Author'){
			$resArray=$myControl->executeSearchByAuthor($currentPatron, $_GET['searchKey']);
			return $resArray;

		}
		else if ($searchType == 'Title' & trim($_GET['searchKey']) != ""){
			$resArray=$myControl->executeSearchByTitle($currentPatron, $_GET['searchKey']);
			return $resArray;

		}
		else if ($searchType == 'iSBN'){
			$resArray=$myControl->executeSearchByISBN($currentPatron, $_GET['searchKey']);
			
			return $resArray;
		}

	}

}


ob_end_flush();

?>