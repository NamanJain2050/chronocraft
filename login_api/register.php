<?php
	
	require_once 'include/DB_Functions.php';
	
	$db = new DB_Functions();
	$response = array("error" => FALSE);
	
	if(isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['mobile'])){
		$name = $_POST['name'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$mobile = $_POST['mobile'];
		$otp = rand(100000, 999999);
		
		$res = $db->storeUser($name,$email,$password,$mobile,$otp);
		
		if($res == USER_CREATED_SUCCESSFULLY){
			sendSMS($mobile,$otp);
			$response["error"] = FALSE;
			$response["message"] = "SMS request is initiated! You will be receiving it shortly.";
		}else if(USER_CREATED_FAILED){
			$response["error"] = true;
			$response["message"] = "Sorry! Error occurred in registration.";
		}else if(USER_ALREADY_EXISTED){
			$response["error"] = true;
			$response["message"] = "Mobile number already existed!";
		}
	}else{
		$response["error"] = true;
		$response["message"] = "Sorry! mobile number is not valid or missing.";
	}
	
	echo json_encode($response);

	function sendSMS($mobile,$otp) {
		$otp_prefix = ':';
		$message = urlencode("Hello! Welcome to Cronocraft. Your OPT is '$otp_prefix $otp'");
		$response_type = 'json';
		$route = "4";
		$postData = array(
			'authkey' => MSG91_AUTH_KEY,
			'mobiles' => $mobile,
			'message' => $message,
			'sender' => MSG91_SENDER_ID,
			'route' => $route,
			'response' => $response_type
		);
		$url = "https://control.msg91.com/sendhttp.php";
		$ch = curl_init();
		curl_setopt_array($ch, array(
        CURLOPT_URL => $url,
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_POST => true,
        CURLOPT_POSTFIELDS => $postData));
		curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
		$output = curl_exec($ch);
		if (curl_errno($ch)) {
			echo 'error:' . curl_error($ch);
		}
		curl_close($ch);
	}
?>
