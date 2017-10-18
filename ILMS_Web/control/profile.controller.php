<?php

function getUserName($id){
	include_once "utility/connection.php";
	include_once "model/uploader.class.php";
	$name=new database($db);
	$userName=$name->getName($id);
	
	return $userName;
	
}
if (!function_exists('parseDate')){
	function upload($student_id){
	/*** check if a file was uploaded ***/
		if (isset($_POST['new-image'])){
			include "utility/connection.php";
			include_once "model/uploader.class.php";
		

			$name=new database($db);
		
			$imgName=$_FILES["upload_image"]["name"];
			$imgData=$_FILES["upload_image"]["tmp_name"];
			$imgPath=pathinfo($_FILES["upload_image"]["name"]);
			$fp=fopen($imgData,'rb');
			$data=fread($fp,filesize($imgData));
			
			/* $data=addslashes($data); */
		
			$uploader=new database($db);
			$uploader->uploadImage($student_id,$data);
			fclose($fp);
			
			
			}else{
				echo "nothing other than images";
			} 
			
		


	}
}

if (!function_exists('parseDate')){
function getProfile($newid){
	
	//to use the connection pdo
	include "utility/connection.php";
	//to get the methods image from the database
	include_once "model/uploader.class.php";
	include_once 'model/book.class.php';
	$getImage=new database($db);
	//assign the image returned from the databse to the variable
	$imgvar=$getImage->getProfilePic($newid);
	
	return $imgvar;
}
}
?>