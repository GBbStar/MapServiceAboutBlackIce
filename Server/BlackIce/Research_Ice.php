
<?php
  
	$con = mysqli_connect("localhost","root","","blackice");
	
	if(mysqli_connect_errno($con)){
		echo "Failed to connect to MySQL: ". mysqli_connect_error();
	}

	$sLati = $_POST["s_lati"];
	$lLati = $_POST["l_lati"];
	$sLongi = $_POST["s_longi"];
	$lLongi = $_POST["l_longi"];

	$search = mysqli_query($con, " select lati, longi from blackice1 where $sLati<lati and lati<$lLati and $sLongi<longi and longi<$lLongi");

	$result = array();

	while($row = mysqli_fetch_array($search)){
		array_push($result,array(
			'lati' => $row['lati'],
			'longi' => $row['longi']
		));
	}

	echo json_encode(array("result"=>$result));

	mysqli_close($con);

?>


