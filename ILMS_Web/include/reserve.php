<?php

//called from the boundary bookReserve.php
//calls the executeReserve method from classReserveControl.php
set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});
	
	
	try{
		include_once('model/classReserveControl.php');

	}catch(ErrorException $e){
		include_once('../model/classReserveControl.php');

	}
	try{
		include_once('model/classPatron.php');

	}catch(ErrorException $e){
		include_once('../model/classPatron.php');

	}

function executeReservation(){
	$currentPatronID = $_SESSION['currentPatronID'];
	$currentBookID = $_SESSION['currentBookID'];
	$currentPatron = constructPatron($currentPatronID);
	$currentBook = constructBook($currentBookID);
	$reserveControl = new ReservationController();
	$reservationResult=$reserveControl->executeReserve($currentBook, $currentPatron);
	
	return $reservationResult;
}



?>
