<?php
//controller class to control commenting of a book
//called from review.php

/* include("classPatron.php"); */
include("classComment.php");

class ReviewController{
	public $coon = null;

	function __construct(){
		$this->coon = new ILMSConnection();
	}

	//receives a book Id, does a query, gets comment objects on that book and the calls the displayReview method
	function executeQuery($id){
		$result = $this->coon->getQueryResult("select * from comment where book_ID = '".$id."' order by date");
		$arrayOfCommentIDs = $this->getCommentIDs($result);
		$commentObjects = array();

		for($i=0; $i<count($arrayOfCommentIDs); $i++){
			$commentObject = constructComment($arrayOfCommentIDs[$i]);
			$commentObjects[$i] = $commentObject;
		}

		
		$patronNames = $this->getPatronNames($commentObjects);
		$displayable=$this->displayReview($commentObjects,$patronNames,$id);
		
		return $displayable;

	}

	//returns a bookID
	function getBookID(){
		$bookID = $_GET['bookID'];
		return $bookID;

	}

	//displays comments on a book
	function displayReview($commentObjects, $patronNames,$id){
		$cont= "<p id = 'hiddenBookID'>".$id."</p>";
		$cont.= "<p id = 'hiddenPatronID'>".$_SESSION['currentPatronID']."</p>";
		$cont.= "<p id = 'parReview'><b> Reviews </b></p>";
		$cont.= "<div id = 'reviewResult' class = 'reviewResults'>";

		//iterates through each comment Object and displays patronName and comment
		for ($i = 0; $i < count($commentObjects); $i++){
			$commentID = $commentObjects[$i]->getCommentID();
			//modified
			$commentDate = $commentObjects[$i]->getDate();
			$commentPhpDate = strtotime($commentDate);
			//endOFMOdified
			$nameID = $commentID."nameOF";
			$ccID = $commentID."CC";
			$cont.= "<div id = '$ccID'>";
			$cont.= "<div id = '$nameID'>";
			$cont.= "<b>".$patronNames[$i]."</b>";
			$cont.= "</div>";
			$cont.= "<div class = 'comments' id = '$commentID'>";
			$cont.= "<p>". $commentObjects[$i]->getComment()."</p>";
			$cont.= "</div>";
			//modified
			$cont.= "<div class = 'commentDate'>";
			$cont.= date("D M j, Y",$commentPhpDate);
			$cont.= "</div>";
			//end of modified

			//checks if the current Patron logged in has a comment on the book and gives him/her a delete button
			if ($commentObjects[$i]->getPatronID() == $_SESSION['currentPatronID']){
				$commentID = $commentObjects[$i]->getCommentID();
				$deleteID = $commentID ."delete";
				$cont.= "<div class = 'delete'>";
				$cont.= "<button id = '$deleteID' type = 'button' name = 'deleteComment' onclick = 'deleteComment(\"$deleteID\");'>Delete</button>";
				$cont.= "</div>";
			}
			$cont.= "</div>";
		}

		$cont.= "</div>";

		//for new Comments
		$cont.= "<div id = 'after'>";
		$cont.= "<input class = 'myComments' id = 'myComment' type = 'text' name = 'addComment' placeholder = '   Add a Comment...'>";
		$cont.= "<button id = 'commentButton' type = 'button' name = 'comment'>Comment</button>";
		$cont.= "</div>";
		return $cont;

	}

	//gives the names of patrons for each comments
	function getPatronNames($commentObjects){
		$num = count($commentObjects);
		$patronNames = array();
		for ($i = 0; $i < $num; $i++){
			$patronID = $commentObjects[$i]->getPatronID();
			$result = $this->coon->getQueryResult("select Name from patron where ID = '".$patronID."'");
			$row = $result->fetch_assoc();
			$patronNames[$i] = $row['Name'];
		}
		return $patronNames;
	}


	//returns array of commentIds which will be used to construct commentObjects
	function getCommentIDs($queryResult){
		$num_results = $queryResult->num_rows;
		$arrayOfCommentIDs = array();
		for($i=0; $i<$num_results; $i++){
			$row = $queryResult->fetch_assoc();
			$arrayOfCommentIDs[$i] = $row['comment_ID'];
		}
		return $arrayOfCommentIDs;
	}

}

?>