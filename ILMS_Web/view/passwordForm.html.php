<?php
function getForm(){
	$content="<div class='blockTag'>";
	$content.="<div class='passwordChange'>";
	$content.="<div id = 'changePassForm'>
		<h2>Enter your New Password</h2>
		<form action = 'index.php' method = 'post'>
		<div id = 'changePassTable'>
		<table>
			<tr>
				<td>
					<label>Current Password: </label>
				</td>
				<td>
					<input class = 'input' type = 'password' name = 'currentPass'>
				</td>
			</tr>
			<tr>
				<td>
					<label>New Password: </label>
				</td>
				<td>
					<input class = 'input' type = 'password' name = 'newPass'>
				</td>
			</tr>
			<tr>
				<td>
					<label>Confirm Password: </label>
				</td>
				<td>
					<input class = 'input' type = 'password' name = 'confirmPass'>
				</td>
			</tr>
		</table>
		</div>
		<input id = 'changePassButton' type = 'submit' value = 'Reset' name = 'changeMyPass'>
	</form><br>
	";

	return $content;	
		
		}
	 ?>