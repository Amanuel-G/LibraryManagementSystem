<?php
	
	$pageData->script.="<script type='text/javascript'>
			$(document).ready(function(){
				$('#sidebar-toggle').click(function(){
					$('.sidebar').toggleClass('visible');
					$('#content').toggleClass('visible');
					$('.footer').toggleClass('visible');
					$('.blockTag').toggleClass('visible');
					$('#slider').toggleClass('visible');
				});
			});
		</script>
	
	";
	$pageData->addScript("changeback.js");
	$pageData->addScript("my.js");
	$pageData->addStyle("sliderMenu.css");
	return "
	<nav>
		<div class='sidebar'>
			<div class='sidebar-wraper'>
				<ul>
					<li><a href='index.php?page=Home'><img src='img/icons/home.jpg' width:30px height=30px/>Home</a></li>
					<li><a href='index.php?page=history'>History</a></li>
					<li><a href='index.php?page=aait'>AAiT Library</a></li>
					<li><a href='index.php?page=help'>Help</a></li>
					<li><a href='index.php?page=aboutUs'>About</a></li>
					<li><a href='index.php?page=changePassword'>Reset Password</a></li>
					<li><a href='index.php?page=logout'>Log out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div id = 'content'>
		<div class='contentHeader'>
				<!-- hamburger menu toggle button -->
					<div id='sidebar-toggle'>
						<span></span>
						<span></span>
						<span></span>
					</div>
				<!-- end of side bar button -->
				<!-- logo here -->
				<span id='logo'> ILMS </span>
				<!-- end of logo -->
		</div>
		<!--middle white Section of ILMS and AAIT
		<section class='module content'>
				 <div class='container'>
					 ILMS and AAIT
				 </div>
		</section>
		-->
	</div>
	";

?>