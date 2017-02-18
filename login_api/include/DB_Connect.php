<?php
	
	class DB_Connect{
		
		private $conn;
		function __construct(){
			
		}
		function __destruct(){
			
		}
		function connect(){
			require_once 'Config.php';
			$this->conn = new mysqli(DB_HOST,DB_USERNAME,DB_PASSWORD,DB_NAME);
			if(mysqli_connect_errno()){
				echo "Failed to connect to MySQL: " . mysqli_connect_error();
				exit;
			}
			return $this->conn;
		}
	}
?>