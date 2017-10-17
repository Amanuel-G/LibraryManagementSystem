<?php
	set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});
	
	try{
			include_once '../include/change.php';
		}catch (ErrorException $e){
		include_once 'include/change.php';	
		}

		try{
			include_once '../view/passwordForm.html.php';
		}catch (ErrorException $e){
		include_once 'view/passwordForm.html.php';
		}

	/* function changePass(){ */
	$var=getForm();
	$patronID = $_SESSION['currentPatronID'];
	$changeControl = new ChangeController();
	$var.="<div id='passwor'>";
	if (isset($_POST['changeMyPass'])){
		if (isset($_POST['currentPass'], $_POST['newPass'], $_POST['confirmPass']) &&
			$_POST['currentPass'] != "" &&  $_POST['newPass'] != "" && $_POST['confirmPass'] != ""){
			
			$var.=$changeControl->executeChange($patronID);
			$_SESSION['Target']="home";
			}
		else{
			$var.= "Please fill all the fields!!!";
		}
	}
	$var.="</div>";
	$var.="</div>";
	$var.="</div>";
	
	try{
	
		
		$cont=$var;
		$pageData->content=$var;
	}catch(Exception $e){
		$cont=$var;
		echo "in the catch";
		
	}

?>