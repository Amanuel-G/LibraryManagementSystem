<?php
include_once('include/reserve.php');


ob_start(); 
$content=executeReservation(); 
ob_end_flush();


$cont=$content;

?>