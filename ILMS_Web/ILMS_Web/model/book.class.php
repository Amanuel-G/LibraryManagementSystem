<?php
class Books{
	private $Book_Id;
	
	private $Book_Title;
	private $BookCover;
	private	$rate;
	private $totalRatings;
	private $number;
	
	
	private	$comments;
	private	$reservation;
	private	$rented;
	private $BookISBN;
	private $Book_Author;
	private	$renters;
	private	$currentRenter;
	private	$publisher;
	private	$author;	
	private	$edition;
	private	$catagory;
	private $noOfPages;
	
	
	
	function __construct($Book_Id,$Book_Title,$BookCover,$rate,$totalRating,$number){
		$this->Book_Id=$Book_Id;
		$this->Book_Title=$Book_Title;
		$this->BookCover=$BookCover;
		$this->rate=$rate;
		$this->totalRatings=$totalRating;
		$this->number=$number;
	}
	
	/* function __construct($Book_Id,$BookISBN,$Book_Author,$Book_Title,$BookCover,$publisher,$author,$edition,$catagory,$noOfPages,$rate,$comments,$reservation,$rented,$renters,$currentRenter){
		$this->Book_Id=$Book_Id;
		$this->BookISBN=$BookISBN;
		$this->Book_Author=$Book_Author;
		$this->Book_Title=$Book_Title;
		$this->BookCover=$BookCover;
		$this->publisher=$publisher;
		$this->author=$author;	
		$this->edition=$edition;
		$this->catagory=$catagory;
		$this->noOfPages=$noOfPages;
		$this->rate=$rate;
		$this->comments=$comments;
		$this->reservation=$reservation;
		$this->rented=$rented;
		$this->renters=$renters;
		$this->currentRenter=$currentRenter;
		
	} */
	function getRate(){
		return $this->rate;
	}
	
	function getTotalRatings(){
		return $this->totalRatings;
	}
	function getNumber(){
		return $this->number;
	}
	function getBook_Id(){
		return $this->Book_Id;
	}
	
	
	function getBookISBN(){
		return $this->BookISBN;
	}
	function getBookCover(){
		return $this->BookCover;
	}
	function getBook_Author(){
		return $this->Book_Author;
	}
	function getBook_Title(){
		return $this->Book_Title;
	}
	function getpublisher(){
		return $this->publisher;
	}
	function getauthor(){
		return $this->author;
	}
	function getedition(){
		return $this->edition;
	}
	function getcatagory(){
		return $this->catagory;
	}
	function getnoOfPages(){
		return $this->noOfPages;
	}

	function getcomments(){
		return $this->comments;
	}
	function getreservation(){
		return $this->reservation;
	}
	function getrented(){
		return $this->rented;
	}
	function getrenters(){
		return $this->renters;
	}
	function getcurrentRenter(){
		return $this->currentRenter;
	}

}