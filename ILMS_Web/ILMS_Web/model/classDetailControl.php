<?php

//This is a controller class used to show detail of a book after a book is clicked on a search result
//called from detail.php
include_once("utility/ILMSConnection.php");
include_once("control/Home.controller.php");
include_once("classBook.php");

class DetailController{
	public $conn = null;

	function __construct(){
		$this->conn = new ILMSConnection();
	}

	//gets the book object by giving the id and then calls the displayBook method
	function executeQuery($id){
		$bookObject = constructBook($id);

		$displayable=$this->displayBook($bookObject); 
		return $displayable;
	}

	//returns true if book is reserved and false if not
	function boolIsReserved($bookObject){
		if ($bookObject->getIsReserved() == 1){
			return true;
		}

		else if($bookObject->getIsReserved() == 0){
			return false;
		} 

		else{
			echo "Incorrect Value for IsReserved";
			return "";
		}
	}

	//returns true if book is rented and false if not
	function boolIsRented($bookObject){
		if ($bookObject->getIsRented() == 1){
			return true;
		}

		else if($bookObject->getIsRented() == 0){
			return false;
		} 

		else {
			echo "Incorrect value for IsRented";
			return "";
		}
	}

	//displays the details of the required book
	function displayBook($bookObject){
			$bookObject->cancelReservation();
			 $cont="<div class = 'bookDetailContainer'>";
				$cont.= "<div class = 'bookDetailImage'>";
				$bookCover = $bookObject->getBookCover();
				//gives default image if book cover is null
				if ($bookCover == null){
					$cont.= '<img src = "img/defaultCover.jpg" width = 200 height = 250/>';
				}
				else{
					$cont.= '<img src = "data:image/jpg;base64,'.base64_encode($bookCover).'" width = 200 height = 250/>';
				}
				
				$cont.= "</div>";
				$cont.= "<div class = 'bookDetailContent'>";
				$cont.= "<p class = 'Title'><b><a href = index.php?page=bookDetail&bookID=".
				$bookObject->getBookID()."' class = 'titleLink'>".$bookObject->getTitle()."</a></b></p>";
				$cont.= "<div id = 'displayISBN'>";
				$cont.= "ISBN: ".$bookObject->getISBN();
				$cont.= "</div>";

				$cont.= "<div id = 'displayPage'>";
				$cont.= "Number of Page: ".$bookObject->getNoOfPages();
				$cont.= "</div>";

				$cont.= "<div id = 'displayDetailed'>";

				//gives a default note if book Description is null
				if ($bookObject->getDescription() !== null && $bookObject->getDescription() !== ""){
				$cont.= "<p>".$bookObject->getDescription()."</p>";
				}	
				else{
					$cont.= "<p> There is no book Description. </p>"; 
				}
				$cont.= "</div>";

				$cont.= "</div>";
				//get The current Rating of the book
				$bookId=$bookObject->getBookID();
				$bookRate=getAverage($bookId);
				$currRate=$bookRate[0]['rate'];
				$cont.="<div class='rating' id='rating' data-average=$currRate data-id=$bookId>
				</div>";
	
				$cont.= "<div id = 'reserveDiv'>";
				$cont.= "<form action = 'index.php' method = 'post' id = 'reserveForm'>";
				
				//disables the Reserve button if book is rented or reserved
				if ($bookObject->getIsReserved() == 0 && $bookObject->getIsRented() == 0){	
					$cont.= "<input type = 'submit' name='reserve' value = 'Reserve' id = 'reserveInput'>";
				}
				else{
					
					$cont.= "<input type = 'submit' value = 'Reserve' id = 'reserveInput' disabled>";
				}
				$cont.= "</form>";
				$cont.= "</div>";

			$cont.= "</div>";
			
			return $cont;
	}
}

?>
