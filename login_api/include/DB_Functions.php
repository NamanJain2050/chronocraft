<?php

class DB_Functions{
		
	private $conn;
	function __construct(){
		require_once 'DB_Connect.php';
		$db = new DB_Connect();
		$this->conn = $db->connect();
	}
	function __destruct(){
		
	}
		
	public function storeUser($name,$email,$password,$mobile,$otp){
		
		$response = array();
		if(!$this->ifUserExists($mobile)){
			$api_key = $this->generateApiKey();
			$uuid = uniqid('',true);
			$hash = $this->hashSSHA($password);
			$encrypted_password = $hash["encrypted"];
			$salt = $hash["salt"];
			$stmt = $this->conn->prepare("INSERT INTO users(unique_id,name,email,encrypted_password,salt,mobile,apikey,status,created_at) VALUES(?,?,?,?,?,?,?,0,NOW())");
			$stmt->bind_param("sssssss",$uuid,$name,$email,$encrypted_password,$salt,$mobile,$api_key);
			$result = $stmt->execute();
			$new_user_id = $stmt->insert_id;
			$stmt->close();
			if($result){
				$otp_result = $this->createOTP($new_user_id,$otp);
				return USER_CREATED_SUCCESSFULLY;
			}else{
				return USER_CREATED_FAILED;
			}
		}else{
			return USER_ALREADY_EXISTED;
		}
	}
	
	public function storeOrder($name,$email,$address,$city,$pin,$mobile,$amount,$code){
		
		$response = array();
			$stmt = $this->conn->prepare("INSERT INTO ORDERS(name,email,address,city,pin,mobile,amount,code) VALUES(?,?,?,?,?,?,?,?)");
			$stmt->bind_param("ssssssss",$name,$email,$address,$city,$pin,$mobile,$amount,$code);
			$result = $stmt->execute();
			//$new_user_id = $stmt->insert_id;
			$stmt->close();
			if($result){
				//$otp_result = $this->createOTP($new_user_id,$otp);
				return USER_ORDER_SUCCESSFULLY;
			}else{
				return USER_ORDER_FAILED;
			}
	}
		
	public function activateUser($otp){
		$stmt = $this->conn->prepare("SELECT u.id,u.name,u.email,u.mobile,u.apikey,u.status,u.created_at FROM users u,sms_codes WHERE sms_codes.code=? and sms_codes.user_id=u.id");
		$stmt->bind_param("s",$otp);
		if($stmt->execute()){
			$stmt->bind_result($id, $name, $email, $mobile, $apikey, $status, $created_at);
			$stmt->store_result();
			if($stmt->num_rows > 0){
				$stmt->fetch();
				$this->activateUserStatus($id);
				$user = array();
				$user["name"] = $name;
				$user["email"] = $email;
				$user["mobile"] = $mobile;
				$user["apikey"] = $apikey;
				$user["status"] = $status;
				$user["created_at"] = $created_at;
				$stmt->close();
				return $user;
			}else{
				return NULL;
			}
		}else{
			return NULL;
		}
	}
		
	public function getUserByEmailAndPassword($email, $password) { 
		$stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            // verifying user password
            $salt = $user['salt'];
            $encrypted_password = $user['encrypted_password'];
            $hash = $this->checkhashSSHA($salt, $password);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $user;
            }
        }else {
            return NULL;
        }
	}
	
	public function checkhashSSHA($salt, $password) {
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
        return $hash;
    }
	
	public function activateUserStatus($user_id){
		$stmt = $this->conn->prepare("UPDATE users set status = 1 where id = ?");
		$stmt->bind_param("i", $user_id);
		$stmt->execute();
		$stmt = $this->conn->prepare("UPDATE sms_codes set status = 1 where user_id = ?");
		$stmt->bind_param("i", $user_id);
		$stmt->execute();
	}
	
	private function createOTP($new_user_id,$otp){
		$stmt = $this->conn->prepare("DELETE FROM sms_codes WHERE user_id = ?");
		$stmt->bind_param("i",$new_user_id);
		$stmt->execute();
		$stmt = $this->conn->prepare("INSERT INTO sms_codes(user_id,code,status) VALUES(?,?,0)");
		$stmt->bind_param("is",$new_user_id,$otp);
		$result = $stmt->execute();
		$stmt->close();
		return $result;
	}
	
	private function hashSSHA($password){
		$salt = sha1(rand());
		$salt = substr($salt,0,10);
		$encrypted = base64_encode(sha1($password.$salt,true).$salt);
		$hash = array("salt"=>$salt,"encrypted"=>$encrypted);
		return $hash;
	}
	
	private function ifUserExists($mobile){
		$stmt = $this->conn->prepare("SELECT id FROM users WHERE mobile = ? AND status = 1");
		$stmt->bind_param("s",$mobile);
		$stmt->execute();
		$stmt->store_result();
		$numRows = $stmt->num_rows;
		$stmt->close();
		return $numRows > 0;
	}
	
	private function generateApiKey(){
		return md5(uniqid(rand(),true));
	}
}
?>