<?php

//this php defines the class Book and a function to construct Book Objects;
try{
		include_once "../utility/ILMSConnection.php";
	}
catch(ErrorException $e){
		include_once "utility/ILMSConnection.php";
	}

class Book {
	private $title = null;
	private $isbn = null;
	private $bookID = null;
	private $bookCover = null;
	private $publisher = null;
	private $author = null;
	private $edition = null;
	private $course = null;
	private $department = null;
	private $category = null;
	private $description = null;
	private $noOfPages = null;
	private $rate = null;
	private $isReserved = null;
	private $isRented = null;

	public function __construct($bookID, $isbn, $title, $bookCover, $author, $publisher, $edition, 
								$course, $department, $category, $description, $noOfPages, $rate, $isRented, 
								$isReserved){
		$this->title = $title;
		$this->isbn = $isbn;
		$this->bookID = $bookID;
		$this->bookCover = $bookCover;
		$this->publisher = $publisher;
		$this->author = $author;
		$this->edition = $edition;
		$this->course = $course;
		$this->department = $department;
		$this->category = $category;
		$this->description = $description;
		$this->noOfPages = $noOfPages;
		$this->rate = $rate;
		$this->isReserved = $isReserved;
		$this->isRented = $isRented;
	}

	function getTitle(){
		return $this->title;
	}
	function getISBN(){
		return $this->isbn;
	}
	function getBookID(){
		return $this->bookID;
	}
	function getBookCover(){
		return $this->bookCover;
	}
	function getPublisher(){
		return $this->publisher;
	}
	function getAuthor(){
		return $this->author;
	}
	function getEdition(){
		return $this->edition;
	}
	function getCourse(){
		return $this->course;
	}
	function getDepartment(){
		return $this->department;
	}
	function getCategory(){
		return $this->category;
	}
	function getDescription(){
		return $this->description;
	}
	function getNoOfPages(){
		return $this->noOfPages;
	}
	function getRate(){
		return $this->rate;
	}
	function getIsReserved(){
		return $this->isReserved;
	}
	function getIsRented(){
		return $this->isRented;
	}
	function setIsReserved($new){
		$this->isReserved = $new;
	}
	function cancelReservation(){
		$bookID = $this->getBookID();
		$connector = new ILMSConnection();
		$result = $connector->getQueryResult("select ExpireDate from reservation_transaction where BookID = '$bookID' AND is_Canceled = 0 order by TransactionDate DESC");
		$row = $result->fetch_assoc();
		if($row != null){
			$expireDate = $row['ExpireDate'];
			$expireTime = strtotime($expireDate, time());
			$currentTime = time();
			if($expireTime < $currentTime){
				$connector->addQuery("update book set is_Reserved = 0 where BookID = 'bookID'");
				$connector->addQuery("update reservation_transaction set is_Canceled = 1 where BookID = '$bookID'");
				$this->setIsReserved(0);
			}
		}
	}
}

//constructs a book object after receiving a book ID
function constructBook($bookID){

	$conn = new ILMSConnection();

	$queryResult = $conn->getQueryResult("select * from book where BookID = '".$bookID."'");

	$row = $queryResult->fetch_assoc();
			$arrayOfAttributes = array();
			$arrayOfAttributes[0] = $row['BookID'];
			$arrayOfAttributes[1] = $row['ISBN'];
			$arrayOfAttributes[2] = $row['Title'];
			$arrayOfAttributes[3] = $row['BookCover'];
			$arrayOfAttributes[4] = $row['Author'];
			$arrayOfAttributes[5] = $row['Publisher'];
			$arrayOfAttributes[6] = $row['Edition'];
			$arrayOfAttributes[7] = $row['Course'];
			$arrayOfAttributes[8] = $row['Department'];
			$arrayOfAttributes[9] = $row['Category'];
			$arrayOfAttributes[10] = $row['Description'];
			$arrayOfAttributes[11] = $row['NO_of_Page'];
			$arrayOfAttributes[12] = $row['Rate'];
			$arrayOfAttributes[13] = $row['is_Rented'];
			$arrayOfAttributes[14] = $row['is_Reserved'];
			
			$bookObject = new Book 
			($arrayOfAttributes[0],
			 $arrayOfAttributes[1],
			 $arrayOfAttributes[2],
			 $arrayOfAttributes[3],
			 $arrayOfAttributes[4],
			 $arrayOfAttributes[5],
			 $arrayOfAttributes[6],
			 $arrayOfAttributes[7],
			 $arrayOfAttributes[8],
			 $arrayOfAttributes[9],
			 $arrayOfAttributes[10],
			 $arrayOfAttributes[11],
			 $arrayOfAttributes[12],
			 $arrayOfAttributes[13],
			 $arrayOfAttributes[14]);
			return $bookObject;
}

?>