<?php
$aResponse['error'] = false;
$aResponse['message'] = '';

// ONLY FOR THE DEMO, YOU CAN REMOVE THIS VAR
	$aResponse['server'] = ''; 
// END ONLY FOR DEMO
	
	
if(isset($_POST['action']))
{
	if(htmlentities($_POST['action'], ENT_QUOTES, 'UTF-8') == 'rating')
	{
		/*
		* vars
		*/
		$id = intval($_POST['idBox']);
		$rate = floatval($_POST['rate']);
		
		
	 
		 include_once "../utility/connection.php";
		include_once "../model/uploader.class.php";
		  $conn=new database($db); 
		
		$ip=1;

		$existingData=$conn->getItems($id);
		
	
		foreach($existingData as $data){
			$oldRate=$data['totalratings'];
			$number_of_rates=$data['number'];
			
		}

		$total_amount_of_rates_combined=$oldRate+$rate;
		$new_total_rates=$number_of_rates+1;
		$new_rating=($oldRate+$rate)/$new_total_rates;
 
		$success=$conn->insertRatings($id,$new_rating,$total_amount_of_rates_combined,$new_total_rates,$ip) ; 
		  
		// if request successful
		$success = true;
	
		
		// json datas send to the js file
		if($success)
		{
			$aResponse['message'] = 'Your rate has been successfuly recorded. Thanks for your rate :)';
			
			
				$aResponse['server'] = '<strong>Success answer "$success":</strong> Success : Your rate has been recorded. Thanks for your rate :)<br />'.$existingData;
			
			
			echo json_encode($aResponse);
			/* echo $id; */
		}
		else
		{
			$aResponse['error'] = true;
			$aResponse['message'] = 'An error occured during the request. Please retry';
			
			
				$aResponse['server'] = '<strong>ERROR :</strong> Your error if the request crash !';
			
			
			
			echo json_encode($aResponse);
		}
	}
}