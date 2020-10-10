<?php

    $conn = mysqli_connect("localhost","root","","blackice");

    $sLati = $_POST["ms_lati"];
	$lLati = $_POST["ml_lati"];
	$sLongi = $_POST["ms_longi"];
	$lLongi = $_POST["ml_longi"];
	$Lati = $_POST["Lati"];
	$Longi = $_POST["Longi"];


    $search = mysqli_query($conn, " select lati,longi from blackice1 where $sLati<lati and lati<$lLati and $sLongi<longi and longi<$lLongi");


	if(mysqli_num_rows($search)>0){
		echo -1;
	}
	else{

		$result= mysqli_query($conn,"insert into blackice1(lati,longi) values ('$Lati','$Longi')");
			
		if($result){
			echo 1;
		}
		else{
			echo -2;
		}
	}

	mysqli_close($conn);

?>
