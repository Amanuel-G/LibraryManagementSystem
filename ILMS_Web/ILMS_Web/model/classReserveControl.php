<?php

//a controller class to reserve a book
//called from reserve.php

include_once("classBook.php");
/* include_once("classPatron.php"); */

class ReservationController{
	public $conn = null;
	
	function __construct(){
		$this->conn = new ILMSConnection();
	}

	//gets Result, feeds result, accepts Book Constructs and displays Books for a search in ISBN
	function executeReserve($book, $patron){
		$bookID = $book->getBookID();
		echo $bookID;
		$patronID = $patron->getID();
		$date = date('Y-m-d H:i:s');
		$expireDate = date('Y-m-d H:i:s', strtotime('+6hours'));
		$toBeHashed = $patronID."".$date."".$bookID;
		$transactionNumber = md5($toBeHashed);
		$isCanceled = 0;
		$isRented = 0;
		$patron->reserveBook($transactionNumber,$bookID,$date,$expireDate,$isCanceled,$isRented);
		$result=$this->displayReserve($book,$patron,$date,$expireDate);
		return $result;
		
	}

	//displays reservation results
	function displayReserve($book,$patron,$date,$expireDate){
		$container="<div class='blockTag'>";
		$container.="<div id='latest-books' class='home-page' >";
		$container.="<div class = 'bookDetailContainer'>";
				$container.="<div class = 'bookDetailImage'>";
				$bookCover = $book->getBookCover();

				//displays default image if bookCover is null
				if ($bookCover == null){
					
					$container.='<img src = "img/defaultCover.jpg" width = 200 height = 250/>';
				}
				else{
					$container.='<img src = "data:image/jpg;base64,'.base64_encode($bookCover).'" width = 200 height = 250/>';
				}
				
				$container.="</div>";
				$container.="<div class = 'bookDetailContent'>";
				$container.="<p class = 'Title'><b><a href = 'index.php?page=bookDetail?bookID=".$book->getBookID()."' class = 'titleLink'>".$book->getTitle()."</a></b></p>";
				$container.="<div id = 'displayISBN'>";
				$container.="ISBN: ".$book->getISBN();
				$container.="</div>";

				$container.="<div id = 'displayPage'>";
				$container.="Number of Page: ".$book->getNoOfPages();
				$container.="</div>";

				$container.="<div id = 'displayDetailed'>";
				
				//displays default description if book description is null
				if ($book->getDescription() !== null && $book->getDescription() !== ""){
				$container.="<p>".$book->getDescription()."</p>";
				}	
				else{
					$container.="<p> There is no book Description. </p>"; 
				}
				$container.="</div>";

				$container.="</div>";
		$container.="</div>";


		$container.="<div id='reservationDetail'>";
		$container.='<div id = "reserved">';
		$container.='<h3>'.$patron->getName().', your Reservation has Been Successfull! </h3>';
		$container.='<p><b> Reservation Date and Time: '.$date.'</b></p>';
		$container.='<p><b>Your reservation will be over on: '.$expireDate.' (After 6 hours)</b></p>';
		$container.='<h3>You can rent this book before the expiry of the Reservation.</h3>';
		$container.='Your Welcome!!!';
		$container.='</div>';
		$container.='</div>';
		$container.='</div>';
		$container.='</div>';
		
		return $container;
	}

}

?>