<?php

//controller class for executing search
//called from search.php

include_once("classPatron.php");
include_once("classBook.php");

	try{
		include_once "control/Home.controller.php";
	}catch(ErrorException $e){
		include_once "../control/Home.controller.php";
	}
	try{
		include_once "index.php";
	}catch(ErrorException $e){
		include_once "../index.php";
	}

class SearchController{
	private $conn = null;
	
	function __construct(){
		$this->conn = new ILMSConnection();
	}

	
	
	//calls the searchByISBN method from Patron class,gets the result and calls the controlSearch
	function executeSearchByISBN($patron, $bookISBN){
		$result = $patron->searchByISBN($bookISBN);
		$res=$this->controlSearch($result);
		return $res;
	}

	
	
	//calls the searchByTitle method from Patron class,gets the result and calls the controlSearch
	function executeSearchByTitle($patron, $bookTitle){
		$result = $patron->searchByTitle($bookTitle);
		$res=$this->controlSearch($result);
		return $res;
	}

	
	
	//calls the searchByAuthor method from Patron class,gets the result and calls the controlSearch
	function executeSearchByAuthor($patron, $bookAuthor){
		$result = $patron->searchByAuthor($bookAuthor);
		$res=$this->controlSearch($result);
		return $res;
	
	}

	
	
	//gets the queryResult and calls displaySearch after getting bookObjects
	function controlSearch($result){
		$bookIDs = $this->getBookIDs($result);
		$bookObjects = array();

		for($i = 0; $i < count($bookIDs); $i++){
			$bookObject = constructBook($bookIDs[$i]);
			$bookObjects[$i] = $bookObject;
		}
		
		$currentPatronID = $_SESSION['currentPatronID'];
		$currentPatron = constructPatron($currentPatronID);
		$patronDep = $currentPatron->getDepartment();

		$suggestedBooks = $this->executeSuggestion($patronDep);
		
		$result_one=$this->displaySearch($bookObjects, $suggestedBooks);
		
		return $result_one;
	}

	
	//executes the seciton of suggested books
	function executeSuggestion($patronDepartment){
		
		$sugresult = $this->conn->getQueryResult("select * from book where Department = '".$patronDepartment."'order by Rate DESC");
		$sugBookIDs = $this->getBookIDs($sugresult);
		$sugBookObjects = array();

		for($i = 0; $i < count($sugBookIDs); $i++){
			$sugBookObject = constructBook($sugBookIDs[$i]);
			$sugBookObjects[$i] = $sugBookObject;
		};
		
		return $sugBookObjects;

	}



	//receives book objects and displays in the view file
	function displaySearch($bookObjects, $suggestedBooks){
		$noOfResult = count($bookObjects);
		$noOfSuggest = count($suggestedBooks);
		echo "<div class = 'RSContainer12'>";
		if ($noOfResult <= 1){
			$a=$noOfResult." result found";
		}
		else{
			$a=$noOfResult." results found";
		}
		echo "</div>";
		//the result of the book search
		$BookResult=$this->display($bookObjects);

			echo "<div class = 'RSContainer12'>";
				if ($noOfSuggest == 0){
					$a="No Suggestions";
				}
				else{
					$a="Suggestions";
				}
			echo "</div>";
		
		$verified_suggestions=array();
		//to check if the book is contained is he result
		$verified=false;
		$no_of_books=count($suggestedBooks);
		if ($noOfResult>0){
		foreach ($suggestedBooks as $suggestion){
			foreach ($bookObjects as $bkItem){
				
				if (($suggestion->getBookID())==($bkItem->getBookID())){
						$verified=false;
						break ;
				}else{
					$verified=true;
				}			
			}
			if ($verified){
				
				$verified_suggestions[]=$suggestion;
					;
			}
		}
		//get the display of suggested books
		$suggestions=$this->display($verified_suggestions);
	}else{
		$suggestions=$this->display($suggestedBooks);
	}
		
		
		
		$result=array($BookResult,$suggestions,$noOfResult);
		
		return $result;
	}

	
	
	//displays books
	function display($bookObjects){
		$container="";
		for ($i = 0; $i < count($bookObjects); $i++){
			$container.="<div class = 'bookContainer'>";
			
			$container.= "<div class = 'bookImage'>";
			$bookCover = $bookObjects[$i]->getBookCover();
			if ($bookCover == null){
				$container.= '<img src = "img/defaultCover.jpg" width = 75 height = 100/>';
			}
			else{
				$container.= '<img src = "data:image/jpg;base64,'.base64_encode($bookCover).'" width = 75 height = 100/>';
			}
			
			
			$container.= "</div>";
			
			
			$container.= "<div class = 'bookContent'>";
			$titleString = $bookObjects[$i]->getTitle();
			if (strlen($titleString) > 17){
				$titleString = substr($titleString,0,17)."..";
			}
			$container.= "<p class = 'Title'><b><a href = 'index.php?page=bookDetail&bookID=".$bookObjects[$i]->getBookID()."' class = 'titleLink'>".$titleString."</a></b></p>";
			$container.= "<p class = 'Edition'>".$bookObjects[$i]->getEdition()." Edition</p>";
			$bookId=$bookObjects[$i]->getBookID();
			$bookRate=getAverage($bookId)[0]['rate'];
			$container.="<div class='rating' data-average=$bookRate data-id=$bookId>
			</div>";
			
			$container.= "</div>";
			$container.= "</div>";
		}
		
		return $container;

	}

	//returns array of BookIds later used to construct book Objects
	function getBookIDs($queryResult){
		$num_results = $queryResult->num_rows;
		$arrayOfBookIDs = array();

		for($i = 0; $i < $num_results; $i++){
			$row = $queryResult->fetch_assoc();
			$arrayOfBookIDs[$i] = $row['BookID'];
		}
		return $arrayOfBookIDs;
	}


}

?>