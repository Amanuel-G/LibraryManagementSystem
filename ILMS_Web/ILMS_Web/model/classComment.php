<?php

//this php defines the class Comment and a function to construct Comment Objects;

class Comment {
	private $commentID = null;
	private $patronID = null;
	private $bookID = null;
	private $comment = null;
	private $date = null;

	public function __construct($commentID, $patronID, $bookID, $comment, $date){
		$this->commentID = $commentID;
		$this->patronID = $patronID;
		$this->bookID = $bookID;
		$this->comment = $comment;
		$this->date = $date;
	}

	function getCommentID(){
		return $this->commentID;
	}
	function getPatronID(){
		return $this->patronID;
	}
	function getBookID(){
		return $this->bookID;
	}
	function getComment(){
		return $this->comment;
	}
	function getDate(){
		return $this->date;
	}
	
}


//constructs a comment object after receiving commentID
function constructComment($commentID){
	$conn = new ILMSConnection();
	$queryResult = $conn->getQueryResult("select * from comment where comment_ID = '".$commentID."'");
	$row = $queryResult->fetch_assoc();
			$arrayOfAttributes = array();
			$arrayOfAttributes[0] = $row['comment_ID'];
			$arrayOfAttributes[1] = $row['patron_ID'];
			$arrayOfAttributes[2] = $row['book_ID'];
			$arrayOfAttributes[3] = $row['comment'];
			$arrayOfAttributes[4] = $row['date'];
			
			$commentObject = new Comment
			($arrayOfAttributes[0],
			 $arrayOfAttributes[1],
			 $arrayOfAttributes[2],
			 $arrayOfAttributes[3],
			 $arrayOfAttributes[4]);
			return $commentObject;
}

?>