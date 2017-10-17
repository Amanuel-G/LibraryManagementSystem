<?php
	
	try{
			include_once 'utility/ILMSConnection.php';
		}catch (ErrorException $e){
		include_once '../utility/ILMSConnection.php';	
		}
		try{
			include_once 'model/classPatron.php';
		}catch (ErrorException $e){
		include_once '../model/classPatron.php';
		}




class ChangeController{
	public $conn = null;

	function __construct(){
		$this->conn = new ILMSConnection();
	}

	//gets the book object by giving the id and then calls the displayBook method
	function executeChange($patronID){
		$pass=false;
		$patronObject = constructPatron($patronID);
		$patronPassword = $patronObject->getPassword();
		$newPassword = $_POST['newPass'];
		$cont="";
		if(($patronPassword != $_POST['currentPass'])){
			$cont.= "Incorrect Current Password!!!<br> ";
			$pass=true;
			
		}
		if($_POST['newPass'] != $_POST['confirmPass']){
			$cont.= "Cannot Reset Password!!!";
			$pass=true;
		}

		if(($_POST['newPass'] == $_POST['confirmPass']) && (strlen($newPassword) < 6) )	{
			echo "wrong";
			$cont.= "Password Length should be more than or equal to 6";

		}

		if(($_POST['newPass'] == $_POST['confirmPass']) && (strlen($newPassword) >= 6) && ($patronPassword == $_POST['currentPass'])){
			$patronObject->changePassword($newPassword);
			$cont.="<div id='passwordSuccess'>";
			$cont.= "Your password has been reset Successfully";
			$cont.="</div>";
			
		}
		
		return $cont;

	}	
}

?>