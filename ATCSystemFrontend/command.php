<!DOCTYPE html>
<html>
<head>
	<title>Command Print</title>
</head>
<body>


	

		<?php
		 $output = shell_exec("cd C:\Program Files (x86)\Java\jdk1.8.0_74\bin");
	    //$output2 = shell_exec("java code.SimpleRead");
	    $output2 = shell_exec("dir");
	    echo $output;
	    echo $output2;


		//echo shell_exec("java code.SimpleRead");
	?>


		




</body>
</html>