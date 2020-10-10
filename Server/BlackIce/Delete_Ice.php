
<?php
  
	$con = mysqli_connect("localhost","root","","blackice");
	
	if(mysqli_connect_errno($con)){
		echo "Failed to connect to MySQL: ". mysqli_connect_error();
	}

	$sLati = $_POST["ds_lati"];
	$lLati = $_POST["dl_lati"];
	$sLongi = $_POST["ds_longi"];
	$lLongi = $_POST["dl_longi"];

	$search = mysqli_query($con, " select lati, longi from blackice1 where $sLati<lati and lati<$lLati and $sLongi<longi and longi<$lLongi");
	
	if($search){
		if(mysqli_num_rows($search)>0){
			$result = mysqli_query($con, " delete from blackice1 where $sLati<lati and lati<$lLati and $sLongi<longi and longi<$lLongi");
			if($result){
				echo 1;
			}
			else{
				echo -3;
			}
		}
		else{
			echo -2;
		}
	}
	else{
		echo -1;
	}

	mysqli_close($con);

?>


