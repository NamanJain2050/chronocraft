<?php
	
	$connection = mysqli_connect('localhost','id66800_naman','naman2208','id66800_cronocraft') or die("Error " . mysqli_error($connection));

    $sql = "select * from case_style";
    $result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));

    //create an array
    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
    echo json_encode($emparray);

    //close the db connection
    mysqli_close($connection);
?>