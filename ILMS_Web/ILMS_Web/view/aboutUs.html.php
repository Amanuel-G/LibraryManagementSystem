<?php
	if (!(isset($_SESSION['currentPatronID']))){
		header("Location: index.php");
	}
	$var="<div class='blockTag'>
			<div class='mainOne'>
			<div id='placeHolder'>
			 <img src='img/360%20Fitness%20Clubpresents%20-%20Copy.jpg' alt=' ' id='backimage'>
			</div>
			<div id='statment'>
                <h3>About Us ... </h3>
                <p> 
                    
                     tmEleVen is a group of AAiT students assigned with an arduous job of being students.Group work is a very practical motto of tmEleVen.Work done in a group, allows a barrier to be crossed.tmEleVen is best elucidated as a group of students with different skills working together, Where every member gives time and effort to a cause...  Part of being tmEleVen is being able to give your opinion and ideas, as well as listening and building off another member's view.
                    

                </p>
            </div>
           </div>
				
		</div>";
	$pageData->sideBar="";
	$pageData->addStyle("about.css");
	$pageData->content=$var;

?>