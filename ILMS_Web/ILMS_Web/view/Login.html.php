<?php 
    ob_start(); 
    $connection = mysqli_connect("localhost",'root','','ilms');
    if(mysqli_connect_errno()){
        die('Connection error: '.mysqli_connect_error());
    }

?>

		<meta charset="UTF-8">
		
		<link rel="stylesheet" href="css/main.css" >
		<link rel="stylesheet" href="css/sliderMenu.css" >
		<link rel="stylesheet" href="css/content.css" >
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/footer.css" />
		

		
		
	<?php 
            $username;
            $password;
            $user = array();
            $user_password = array();
            $notify =""; // notify the user
            $checker = array("/","^","[","]","~","+",","); // invalid characters
            if(isset($_POST['submit']) || $_SERVER['REQUEST_METHOD'] == 'POST'){
                // assign the username and password
                 $username = $_POST['username'];
                 
                 if(!empty(trim($username))){
                     // check for invalid characters
                     $count = 0;
                     $array = str_split($username); // arbitary array to hold individual username strings
                     for($i=0;$i < count($array);$i++){
                           if(in_array($array[$i], $checker)){
                               $count++; // if count equals zero no invalid string values
                           }   
                     }
                     if($count > 0){
                         $notify = "Invalid Username Entry!";
                     }
                     else{
                        $password = $_POST['password'];
                        if(!empty(trim($password))){

                            // queries to the database
                             $query = 'select ID,NAme,password from patron';
                             $result = mysqli_query($connection,$query);

                             // populate the user and user_password array
                             while($values = mysqli_fetch_assoc($result)){
								 
                                  $user[] = $values['NAme'];
                                  $user_password[] = $values['password'];
                                  $patronID[] = $values['ID'];
                              }
							
                              if(in_array($username, $user)){ // check if username is in the database
                                  for($i=0;$i < count($user);$i++){
                                      if($user[$i] == $username && $user_password[$i] == $password){
								
                                          $url = "index.php?page=home&patronId=$patronID[$i]";
										
											$_SESSION['Target']="home";
										 
											$_SESSION['currentPatronID']=$patronID[$i];			 
                                          header("Location: $url");
                                      }else{
                                          $notify = 'Password not correct!';
                                      }
                                  }
                              }
                              else{
                                 $notify = "Username not correct!";
                              }

                        }
                        else{
                            $notify = 'Password field left blank. Please enter password';
                        }
                    }
                 }
                 else{
                     $notify = 'Username or Password left blank';
                 }
                
            }
        
        
        
        
		
	
		$container="
		
		
		<div id='content'>
			<!-- parallax effect!(Don't touch) -->
			<section class='module parallax parallax-1'>
				  <div class='container'>
					<div class='form-container'> 
						  <form autocomplete='on' method='post' target='_self' name='formOne'>
                              <div id='userContainer'><label id='username'>Username</label> <input name='username' placeholder='username' type='text' size='50' id='user'/></div>
                                <div id='passContainer'><label id='password'>password </label><input type='password'  name='password'  placeholder='**********' size='50' id='pass'/></div>
                                <div id='log'><input type='submit' name='submit' value='login' id='login' onclick='validator()'/></div>
						 </form>
					 </div>
         <div id='notify' style='color: white;'>";
		
		$container.= $notify; 
		$notify ='';
		$container.="
		</div>
                           
			</section>

			<section class='module content'>
				 <div class='container'>
					 ILMS and AAIT
				 </div>
			</section>

			<section class='module parallax parallax-2'>
				  <div class='container'>
					<h1 style='font-family:century gothic;'>ILMS</h1>
				  </div>
			</section>
			<!-- parallax effect!(Don't touch) -->
		</div>
		";

	$pageData->content=$container;
	$pageData->sideBar="";
	$pageData->header="";	
		
		
		
	?>
		
		<script src="script/jquery.min.js"></script>
		<!-- end of toggle class function -->	
	<script src="script/changeback.js"></script>

<?php ob_end_flush(); ?>

