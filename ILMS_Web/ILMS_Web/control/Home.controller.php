<?php

//For the ratng system
//to select everytime a star is selected
if (!function_exists('parseDate')){
function getAverage($id=null){
	include "utility/connection.php";
	//to get the methods image from the database
	include_once 'model/book.class.php';
	$getRate=new database($db);
	
	$temp=$getRate->getItems($id);

	return $temp;
	}
}

if (!function_exists('parseDate')){
function getRentedHistory($student_id){
	//create a connection
	include "utility/connection.php";
	//to get the methods image from the database
	include_once 'model/book.class.php';
	$getImage=new database($db);
	//assign the returned from the databse to the variable
	$imgvar=$getImage->getHistory($student_id);
	//create arrays to store the book details
	$bookCovers=array();
	$transactionDate=array();
	$returnDate=array();
	//fill in the variables with the data that has been rerturned from the database
	$bookCovers=$imgvar[0];
	$transactionDate=$imgvar[1];
	$returnDate=$imgvar[2];
	
	
	$bookTitle=Array();
	$bookCover=Array();
	for ($i=0;$i<count($bookCovers);$i++){
		
		$bookTitle[$i]=$bookCovers[$i]->getBook_Title();
		$bookCover[$i]=$bookCovers[$i]->getBookCover();
	}
	return array($bookTitle,$bookCover,$transactionDate,$returnDate);
}
}


if (!function_exists('parseDate')){
function getLatestBooks($bookid=0){
	try{
		include "utility/connection.php";
	}catch(ErrorException $e){
		include "../utility/connection.php";
	}
	
	try{
		include_once 'model/book.class.php';
	}catch(ErrorException $e){
		include_once '../model/book.class.php';
	}
	
	/* include "model/uploader.class.php"; */
	
	$getImages=new database($db);
	//assign the image returned from the databse to the variable
	$bookCovers=$getImages->getBooks($bookid);
	$bookTitle=Array();
	$bookCover=Array();
	$rate=Array();
	$totalRatings=Array();
	$number=Array();
	$ids=Array();
	for ($i=0;$i<count($bookCovers);$i++){
		$bookTitle[$i]=$bookCovers[$i]->getBook_Title();
		$bookCover[$i]=$bookCovers[$i]->getBookCover();
		$rate[$i]=$bookCovers[$i]->getRate();
		$totalRatings[$i]=$bookCovers[$i]->getTotalRatings();
		$number[$i]=$bookCovers[$i]->getNumber();
		$ids[$i]=$bookCovers[$i]->getBook_Id();
	}
	return array($bookTitle,$bookCover,$rate,$ids);
}
}
