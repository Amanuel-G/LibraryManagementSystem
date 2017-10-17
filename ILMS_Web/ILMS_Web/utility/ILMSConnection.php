<?php ob_start();?>
<?php

class ILMSConnection{
	private $username = "root";
    private $password = "";
    private $serverName = "localhost";
    private $databaseName = "ilms";
    private $con = null;

    function __construct(){
    	$this->con = mysqli_connect($this->serverName, $this->username, $this->password) or die(mysql_error());
        mysqli_select_db($this->con,$this->databaseName) or die(mysql_error());
        //echo "constructed";
    }

    public function getQueryResult($query){
    		$result = mysqli_query($this->con, $query) or die(mysql_error());
            return $result;
    }

    public function addQuery($query){
        mysqli_query($this->con, $query) or die(mysql_error());
    }

    public function getCon(){
        return $this->con;
    }

    public function closeCon(){
        mysqli_close($this->con);
    }
    		
}
?>
<?php ob_end_flush();?>