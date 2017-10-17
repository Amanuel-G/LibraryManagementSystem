<?php

//this php defines the class Patron and a function to construct Patron Objects;

	try{
		include_once "../utility/ILMSConnection.php";
	}catch(ErrorException $e){
		include_once "utility/ILMSConnection.php";
	}
class Patron {
	private $id = null;
	private $name = null;
	private $password = null;
	private $patronImage = null;
	private $department = null;
	private $email = null;
	

	public function __construct($id, $name, $password, $patronImage, $department, $email){
		$this->id = $id;
		$this->name = $name;
		$this->password = $password;
		$this->patronImage = $patronImage;
		$this->department = $department;
		$this->email = $email;
	}

	function getID(){
		return $this->id;
	}
	function getName(){
		return $this->name;
	}
	function getPassword(){
		return $this->password;
	}
	function getPatronImage(){
		return $this->patronImage;
	}
	function getDepartment(){
		return $this->department;
	}
	function getEmail(){
		return $this->email;
	}

	//connects to a db and returns the query result
	function searchByISBN($bookISBN){
		$connector = new ILMSConnection();
		$result = $connector->getQueryResult("select * from book where ISBN = ".$bookISBN);
		$connector->closeCon();
		return $result;
	}

	//connects to a db and returns the query result
	function searchByTitle($bookTitle){
		$connector = new ILMSConnection();
		//var_dump($this->connector);
		$result = $connector->getQueryResult("select * from book where Title like '".$bookTitle."%'");
		$connector->closeCon();
		return $result;
	}

	//connects to a db and returns the query result
	function searchByAuthor($bookAuthor){
		$connector = new ILMSConnection();
		$result = $connector->getQueryResult("select * from book where Author = '".$bookAuthor."'");
		$connector->closeCon();
		return $result;
	}

	//adds a comment to the comment table in the database
	function addComment($commentID, $bookID, $comment, $date){
		$patronID = $this->getID();
		$connector = new ILMSConnection();
		$connector->addQuery("insert into comment(comment_ID, patron_ID, book_ID, comment, date)
								values('$commentID','$patronID','$bookID','$comment','$date')");
		$connector->closeCon();
	}

	//reserves a book by adding a transacion in the reservaionTransaction table
	//makes the book isReserved value to 1(true)
	function reserveBook($transactionNumber,$bookID,$date,$expireDate,$isCanceled,$isRented){
		$patronID = $this->getID();
	
		$connector = new ILMSConnection();
		$connector->addQuery("insert into reservation_transaction(TransactionNumber, BookID, PatronID, TransactionDate, ExpireDate, is_Canceled, is_Rented)
									values('$transactionNumber', '$bookID', '$patronID', '$date', '$expireDate', $isCanceled, $isRented)");
		$connector->addQuery("update book set is_Reserved = 1 where BookID = '$bookID'");
		$connector->closeCon();

	}
	//change the password of a patron
	function changePassword($newPassword){
		$patronID = $this->getID();

		$connector = new ILMSConnection();

		$connector->addQuery("update patron set Password = '$newPassword' where ID = $patronID");
		//echo "$newPassword";
		$connector->closeCon();
	}
}


function constructPatron($patronID){
	$conn = new ILMSConnection();
	$queryResult = $conn->getQueryResult("select * from patron where ID = '".$patronID."'");
	$row = $queryResult->fetch_assoc();
			$arrayOfAttributes = array();
			$arrayOfAttributes[0] = $row['ID'];
			$arrayOfAttributes[1] = $row['Name'];
			$arrayOfAttributes[2] = $row['Password'];
			$arrayOfAttributes[3] = $row['PatronImage'];
			$arrayOfAttributes[4] = $row['Department'];
			$arrayOfAttributes[5] = $row['Email'];
			
			$patronObject = new Patron
			($arrayOfAttributes[0],
			 $arrayOfAttributes[1],
			 $arrayOfAttributes[2],
			 $arrayOfAttributes[3],
			 $arrayOfAttributes[4],
			 $arrayOfAttributes[5]);
			return $patronObject;
}

?>