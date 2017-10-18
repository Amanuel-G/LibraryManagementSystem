<?php
$var="<div class='blockTag'>
		<div class='holder'>
		<div id='snipnet'>
            <div class='row'>
                <!--colone -->
                <div class='col'>
                    <a href='#' class='general'>
                        <div class='background'>
                            <div class='info'>
                                General Settings
                            </div>
                        </div>
                        
                        <div class='desc'>
                            Click to view
                        </div>
                    </a>
                </div>
                <!-- end of col one -->

                 <!--colone -->
                <div class='col'>
                    <a href='#' class='reserve'>
                        <div class='background'>
                            <div class='info'>
                               Reserve a book
                            </div>
                        </div>

                        <div class='desc'>
                            Click to view
                        </div>
                    </a>
                </div>
                <!-- end of col one -->

                 <!--colone -->
                <div class='col'>
                    <a href='#' class='comment'>
                        <div class='background'>
                            <div class='info'>
                                Comments...
                            </div>
                        </div>
                         
                        <div class='desc'>
                            Click to view
                        </div></a>
                    
                </div>
                <!-- end of col one -->

                 <!--colone -->
                <div class='col'>
                    <a href='#' class='rate'>
                        <div class='background'>
                           <div class='info'>
                                Rate a book
                            </div>
                        </div>
                         
                        <div class='desc'>
                            Click to view
                        </div>
                    </a>
                </div>
                <!-- end of col one -->

            </div><!-- end of row one -->

            <!-- star of description -->
            <div class='helpContent'>
              <div class='generalContent'>
				  <h3> Change Profile picture </h3>
				  <p id='helpProfile'>Step 1. To change your profile picture, hover over the Profile detail.If there is no profile picture assigned, a place holder can be seen in its place.
				  </p>
				  
				  <p id='helpProfile'>Step 2. After hovering on the image , change button appears on top the profile picture.
				  </p>
				  <p id='helpprofile'>step 3. Click the change button </p>
				  <p id='helpprofile'>step 4. After clicking the help button upload button appears on display.</p>
				  <p id='helpprofile'>step 5. Browse to the file u seek, select and  refresh the page.</p>
				  <p id='helpprofile'> That's it all done!!</p>
              </div>
              <div class='rateContent'>
                	<h3> Rate a Book</h3>
				  <p id='helpProfile'>Step 1. To rate a book you have to search it first.
				  </p>
				  
				  <p id='helpProfile'>Step 2. On the result page, the rating measure(gold stars) appear. These are disabled.To rate a
					  specific book click on it. 
				  </p>
				  <p id='helpprofile'>step 3. The book detail page is displayed. </p>
				  <p id='helpprofile'>step 4. Hover over the stars to rate.</p>
				  <p id='helpprofile'> That's it all done!!</p>
              </div>
              <div class='reserveContent'>
                <h3> Reserve a book.</h3>
				  <p id='helpProfile'>Follow the steps on Rating a book upto step 3.
				  </p>
				  
				  <p id='helpProfile'>Step 4. After book detail is displayed, click on the reserve button bellow.
				  </p>
				  <p id='helpprofile'>Reminder!. If a book is reserved the reserve button is disabled. </p>
				  <p id='helpprofile'> That's it all done!!</p>
              </div>
              <div class='commentContent'>
                 <h3> Comment a book.</h3>
				  <p id='helpProfile'>Follow the steps on Rating a book upto step 3.
				  </p>
				  
				  <p id='helpProfile'>Step 4. After book detail is displayed, go to the bottom and click on comment.
				  </p>
				  <p id='helpprofile'>Reminder! To delete a comment click on delete button bellow. </p>
				  <p id='helpprofile'> That's it all done!!</p>
              </div>
              </div>
            </div>

            <!-- end of descreption -->
        </div>
	</div>";
		
		$pageData->addStyle('help.css');
		$pageData->addBottomScript('jquery.min.js');
		$pageData->script.="<script type='text/javascript'>
			$(document).ready(function(){
				$('.general').click(function(){
					$('.generalContent').css('display','block').show().animate({opacity:0.8},{duration:'slow'});
                    $('.reserveContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.rateContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.commentContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
               
				});
                $('.reserve').click(function(){
					$('.generalContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.reserveContent').css('display','block').show().animate({opacity:0.8},{duration:'slow'});
                    $('.rateContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.commentContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
               
				});
                $('.rate').click(function(){
					$('.generalContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.reserveContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.rateContent').css('display','block').show().animate({opacity:0.8},{duration:'slow'});
                    $('.commentContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    
                  
				});
                $('.comment').click(function(){
					$('.generalContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.reserveContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.rateContent').css('display','none').hide().animate({opacity:0},{duration:'slow'});
                    $('.commentContent').css('display','block').show().animate({opacity:0.8},{duration:'slow'});
           
				}); 
               
			});
		</script>";
	$pageData->sideBar="";
	$pageData->content.=$var;
	
		

?>