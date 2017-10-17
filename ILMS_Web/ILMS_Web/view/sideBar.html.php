<?php
if (isset($_SESSION['currentPatronID'])){
	$student_id=$_SESSION['currentPatronID'];
}else{
	$student_id=12;
}
	set_error_handler(function($errno,$errstr,$errfile,$errline,array $errcontext){
	if (0=== error_reporting()){
		return false;
	}
	throw new ErrorException($errstr,0,$errno,$errfile,$errline);
	});


	try{
		include_once "control/profile.controller.php";
	}catch(ErrorException $e){
		include_once "../control/profile.controller.php";
	}



	
	if (isset($_GET['Search'])){
		include "view/searchResults.html.php";
	}else{
	$stu=getUserName($student_id);
	$stu_name=$stu;
	$profile_pic=getProfile($student_id); 

	
	return"<div class='blockTag'> <div id='profile' class='home-page'>
					<div id = 'userProfile'>
					<h3>$stu_name</h3>
					</div>
					$profile_pic 
					<div id='upload'>
						<a onClick='showUpload()'>Change</a>
						
						<form  id='upload-form' method='post' action='index.php?page=home' enctype='multipart/form-data'>
						
						
							<input type='file' name='upload_image' accept='image/jpeg'/>
							<input type='submit' value='upload' name='new-image' /> 
						</form>
					</div>
				
				
				<div class='searchs'>
				
					<form class='searchBar' action = 'index.php' method = 'get'>
					<label id='sid'>Search by: </label>
						<select name = 'searchType' id = 'select'>
						<option value = 'Author'>Author</option>
						<option value = 'Title'>Title</option>
						<option value = 'iSBN'>ISBN</option>
						</select>
					
				</br>
				
					<span>

					<input class'searchBars  round' type = 'text' name = 'searchKey' placeholder = 'Search book by Author' class='searchBars round' id = 'searchInput'>
					
					
					<input type = 'submit' value = 'Search' name = 'search'>
					</span>
					</form>
				</div>
			</div>
		</div>
	</div>
	";}
	
	
	?>
