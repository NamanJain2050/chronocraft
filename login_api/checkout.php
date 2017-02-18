<?php

		$name = $_POST["name"];
		$email = $_POST["email"];
		$address = $_POST["address"];
		$city = $_POST["city"];
		$pin = $_POST["pin"];
		$mobile = $_POST["mobile"];
		$amount = $_POST["amount"];
		$code = $_POST["code"];
		
		define('MSG91_AUTH_KEY',"128997Ab1SbsmRhz5809f190");
		define('MSG91_SENDER_ID','CRONOC');
		
	
		$connection = mysqli_connect('localhost','id66800_naman','naman2208','id66800_cronocraft') or die("Error " . mysqli_error($connection));
		$stmt = $connection->prepare("INSERT INTO ORDERS(name,email,address,city,pin,mobile,amount,code) VALUES(?,?,?,?,?,?,?,?)");
			$stmt->bind_param("ssssssss",$name,$email,$address,$city,$pin,$mobile,$amount,$code);
			$result = $stmt->execute();
		//$sql = "INSERT INTO ORDERS(name,email,address,city,pin,mobile,amount,code) VALUES($name,$email,$address,$city,$pin,$mobile,$amount,$code)";
		//$result = mysqli_query($connection, $sql) or die("Error in Selecting " . mysqli_error($connection));
			if($result){
				//$otp_result = $this->createOTP($new_user_id,$otp);
				//sendSMS($mobile);
				
		$otp_prefix = ':';
		$message = urlencode("Thank you for choosing Chronocraft, You order has been received '$otp_prefix $code'");
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
				
			$response["in sms"] = curl_error($ch);	
				
				$response["error"] = FALSE;
				$response["message"] = "SMS request is initiated! You will be receiving it shortly.";
			}else{
				$response["error"] = true;
				$response["message"] = "Sorry! Error occurred in registration.";
			}
			
	echo json_encode($response);
	
	/*function sendSMS($mobile) {
		$response["in sms"] = "in sms";
		//$otp_prefix = ':';
		$message = urlencode("Hello! Thank you for choosing cronocraft!Your order has been received by us!");
		$response_type = 'json';
		$route = "4";
		$postData = array(
			'authkey' => MSG91_AUTH_KEY,
			'mobiles' => '+91'+ $mobile,
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
	}*/
		
?>
