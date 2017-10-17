<?php

	include "control/Home.controller.php";
	$student_id=$_SESSION['currentPatronID'];
		
	$profile_pic=getProfile($student_id);
	//create an array to recieve the list of books

	//get the latest books

	$books=getRentedHistory($student_id);
	
	
	//assign the values from the above function to the following variables

	$booksTitle=$books[0];
	$booksimg=$books[1];
	$transactionDate=$books[2];
	$returnDate=$books[3];
	$cont="<div class='blockTag'>
		<div id='latest-books' class='home-page'>";
	if (count($booksTitle)==0){
		
		$cont.="<div id='noResult'>You Don't Currently Have<br>Any Rented Books</div>";
	}else{
		
	
	$cont.="
			<h1>Rented Books</h1>
			<table border=0 cellpadding='10px'>
				<tr>
					<th>Book Cover</th>
					<th>Book Title</th>
					<th>Rented Date</th>
					<th>Return Date</th>
			
				</tr>
			";
			//loop through the resultset to get rented books
			for ($i=0;$i<count($booksTitle);$i++){
			$cont.="
			<tr>
					<td class='covers' class='firstcol'><a href='#'>$booksimg[$i]</a></td>
					<td class='covers'><a id='lft' href='#'>$booksTitle[$i]</a></td>
					<td>$transactionDate[$i]</td>
					<td >$returnDate[$i]</td>
				</tr></div>";
			}
			$cont.="</table>";

	}
	$cont.="</div>
	</div>";

$pageData->content=$cont;
?>