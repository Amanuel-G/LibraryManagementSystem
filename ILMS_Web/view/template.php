<?php
	return"<!DOCTYPE html>
<html>
<html lang='en'>
<head>
<link rel='icon' href='img/ilms2.png'>
<title>$pageData->title</title>
<meta http-equiv='Content-Type' content='text/html;charset=utf-8'/>
$pageData->style
$pageData->script
</head>
<body>
$pageData->header
$pageData->content
$pageData->sideBar
$pageData->footer

$pageData->bottomScript
</body>
</html>
";


?>