<?php
//a php used to delete a comment using ajax
//called from ajaxDeleteComment.js

header('Content-Type: text/xml');
echo '<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>';
echo '<response>';
	include_once ('../utility/ILMSConnection.php');
	if(isset($_GET['commentID']) && $_GET['commentID'] !== ""){
		$commentID = $_GET['commentID'];
		$myDb = new ILMSConnection();
		$myDb->addQuery("delete from comment where comment_ID = '$commentID'");
	}
echo '</response>';
?>