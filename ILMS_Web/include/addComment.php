<?php
//a php used to add a comment as an ajax;
//called from ajaxAddComment.js
header('Content-Type: text/xml');
echo '<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>';
echo '<response>';
	include_once ('../model/classPatron.php');
	if(isset($_GET['comment']) && $_GET['comment'] !== ""){
		$patronID = $_GET['patronID'];
		$comment = $_GET['comment'];
		$bookID = $_GET['bookID'];
		
		$date = date('Y-m-d H:i:s');
		$toBeHashed = $patronID."".$bookID."".$date;
		$commentID = md5($toBeHashed);
		$currentPatron = constructPatron($patronID);
		$currentPatron->addComment($commentID, $bookID, $comment, $date);
		$patronName = $currentPatron->getName();
		
		echo "<b>$patronName</b>";
		echo "<p>$comment</p>";
	}
echo '</response>';
?>