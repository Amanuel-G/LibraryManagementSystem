<html>
<head>
	<meta charset="UTF-8">
	<title>Search Results</title>
	<link rel="stylesheet" href="../css/main.css" >
	<link rel="stylesheet" href="../css/sliderMenu.css" >
	<link rel="stylesheet" href="../css/content.css" >
	<link rel="stylesheet" href="../css/style.css" />
	<link rel="stylesheet" href="../css/footer.css" />
	<link rel="stylesheet" href="../css/contentForDetail.css" />
	<link rel="stylesheet" href="../css/contentForReserve.css" />
</head><?php
//a php file used to execute the reservation of a book;

?><body sty="opacity:1">
		
	<!-- INCLUDED AS ONE SHIT -->
	<div class="sidebar">
		<div class="sidebar-wraper">
			<ul>
				<li><a href="#">Home</a></li>
				<li><a href="#">History</a></li>
				<li><a href="#">AAiT Library</a></li>
				<li><a href="#">Help</a></li>
				<li><a href="#">Contact Us</a></li>
				<li><a href="#">About</a></li>
			</ul>
		</div>
	</div>
		
		
		
		
	<!-- INCLUDED AS ANOTHER SHIT -->
	<!-- 2ND SHIT -->
	<div id = "content">
		<div class="contentHeader">
				<!-- hamburger menu toggle button -->
					<div id="sidebar-toggle">
						<span></span>
						<span></span>
						<span></span>
					</div>
				<!-- end of side bar button -->
				<!-- logo here -->
				<span id="logo"> ILMS </span>
				<!-- end of logo -->
		</div>
		<!--middle white Section of ILMS and AAIT
		<section class="module content">
				 <div class="container">
					 ILMS and AAIT
				 </div>
		</section>
		-->
	</div>


	
		<!-- all php contents and profile of the user (includes search bar) in here-->

	</div>
	<div id = "resultContainer"><?php /*executes Reservation of a book*/?></div>
	<!--php in here-->
	<div id = "myFloat">
	</div>
</div>


	<!-- 2ND SHIT -->
	<!-- footer and stuff -->
	<div class="footer">
		<div id="wraper">
			<div id="foot-left">
				
				<span class="icons">
					<a class="twitter" href=""></a>
					<a class="facebook" href=""></a>
					<a class="email" href=""></a>
					<a class="github" href=""></a>
					<a class="googleplus" href=""></a>
				</span>
				<ul>
					
					<li><a href="#">About Us</a></li>
					<li><a href="#" style="width:17%;" >Contact Us</a></li>
					<li><a href="#" style="width:24%;">Terms &amp; Conditions</a></li>
					
				</ul>
				<span id="copyright"> &copy; 2016 ILMS. By <b style="color:#d4d7e;">tmEleVen Creative.</b></span>
				
			</div>
			<div id="foot-right">
				<img id="footLogo" src="../img/ILMS2.png" alt="ILMS logo" >
			</div>
		</div>
			
			
	</div>
	<!--end of footer -->
		
		<script src="../script/jquery.min.js"></script>
		
		<!-- toggle class function -->
		<script type="text/javascript">
			$(document).ready(function(){
				$('#sidebar-toggle').click(function(){
					$('.sidebar').toggleClass("visible");
					$('#content').toggleClass("visible");
					$('.footer').toggleClass("visible");
					$('.blockTag').toggleClass("visible");
				});
			});
		</script>
		<!-- end of toggle class function -->	
		<script src="../script/changeback.js"></script>
		<script src="../script/my.js"></script>
</body>
</html>