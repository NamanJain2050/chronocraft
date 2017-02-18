<?php
	
	require_once 'include/DB_Functions.php';
	$db = new DB_Functions();
	$response = array();
	$response["error"] = false;
	if(isset($_POST['otp'])){
		$otp = $_POST['otp'];
		$user = $db->activateUser($otp);
		if($user != NULL){
			$response["message"] = "User created successfully!";
			$response["profile"] = $user;
		}else{
			$response["message"] = "Sorry! Failed to create your account.";
			$response["error"] = true;
		}
	}else{
		$response["message"] = "Sorry! OTP is missing.";
		$response["error"] = true;
	}
	
	echo json_encode($response);
?>