<?php
	
	$dbInfo = "mysql:host=localhost;dbname=ilms";
	$dbUser = "root";
	$pass="";
	try{
		$db=new PDO($dbInfo,$dbUser,$pass);
		$db->setAttribute(PDO::ATTR_ERRMODE ,PDO::ERRMODE_EXCEPTION);
	}catch(PDOEXCEPTION $e){
		$db="";
		trigger_error("CAN NOT CONNECT TO THE DATABASE");
	}
		

	
?>