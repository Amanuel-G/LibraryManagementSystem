<?php
	
	class database{
		private $db;
		
		function __construct($dbConn){
			$this->db=$dbConn;
		}
		function insertImage($id,$name,$data,$pass){
			$sql="insert into patron values('1221','$name','$pass','$data','')";
					$statement=$this->db->prepare($sql);
			try{
	
			$statement->execute();
			}catch(PDOExcption $ex){
				echo $ex;
			}
		
			
		}
		
		function getName($id){
				$sql="SELECT Name ";
				$sql.="FROM patron ";
				$sql.="Where ID=$id";
				
				$statement=$this->db->query($sql);
				$rowcount=$statement->rowCount();
				
				$res=Array();
				if ($rowcount==1){
					$res=$statement->fetch();
					$stuName=$res['Name'];
				}else{
					$stuName="Student";
				}
				
				return $stuName;
	}
	
		function insertRatings($id,$rating,$totalrates,$no,$ip){
			$sql="UPDATE bookrating set
			rate='$rating',
			totalratings='$totalrates',
			number='$no',
			ip=CONCAT(ip,',$ip')
			WHERE BookId=$id";
			
			$statement=$this->db->query($sql);
			
	
		}
		
		
		
		
//get The curent rate items from the database		
		function getItems($id){
			if (isset($id)){
				
				$sql="select * from bookrating where BookId='$id'";
				
			}else{
				$sql="select * from bookrating";
			}
			$statement=$this->db->query($sql);
			
			$rowCount=$statement->rowCount();
			if ($rowCount>=1){
				$result=$statement->fetchAll();
			}else{
				$result=0;
			}

			
			return $result;
		}
		
		
//get a Book item from the database		
		function getBooks($bookId=0){
			
			$bookArray=Array();
			if ($bookId==0){
				$sqlCommand="SELECT BookID,Title,BookCover From book Where BookID!=$bookId ";
			}else{
				$sqlCommand="SELECT BookID,Title,BookCover From book";
			}
			
			$statement=$this->db->prepare($sqlCommand);
			$statement->execute();
			$statement->bindColumn(1,$id);
			$statement->bindColumn(2,$title);
			$statement->bindColumn(3,$cover,PDO::PARAM_LOB);
			$statement->bindColumn(4,$rate);
			$statement->bindColumn(5,$totalRatings);
			$statement->bindColumn(6,$number);
			$dump="";
			$new=array();
			$i=0;
			while($statement->fetch() &&($i<6)){
				
				
				if($cover==NULL){
					$new="<img src='img/defbookcover.jpg'  alt='no imgs'><br/>";
				}else{
					file_put_contents("BookCover/".$id.".jpg",$cover);
					$dump=$cover;
					$new='<img src = "data:image/jpg;base64,'.base64_encode($cover).'" width = 75 height = 100/>';
				}
				//create a book containing the id,title and cover of a specific book
			
				$bookArray[$i]=new Books($id,$title,$new,$rate,$totalRatings,$number);
				
				$i+=1;
			}
			
			return $bookArray;
		}

		
		function getHistory($id){
			//get the history of the book id from the database
			$sqlCommand="SELECT BookID,TransactionDate,Return_Date FROM rent_transaction WHERE PatronID=$id";
			$statement=$this->db->prepare($sqlCommand);
			$statement->execute();
			$statement->bindColumn(1,$bookId);
			$statement->bindColumn(2,$Transaction_date);
			$statement->bindColumn(3,$Return_Date);
			//create arrays to store the results
			//to store the book id
			$rentedBooks=Array();
			//store the return and the transaction date
			$transactionDate=array();
			$returndate=array();
		
			$i=0;
			
			while($statement->fetch()){
				$rentedBooks[$i]=$bookId;
				$transactionDate[$i]=$Transaction_date;
				//Check if the book has been returned 
				if ($Return_Date==0){
					$returndate[$i]="hasn't been Returned";
				}else{
					$returndate[$i]=$Return_Date;
				}
				$i+=1;
			}
			
			
			$bks=Array();
			$bksTotal=Array();
			
			$j=0;
			//loop through each book and get their details
			foreach($rentedBooks as $item){
				$tempbks=$this->getBooks($item);
				$bks[$j]=$tempbks[0];
				$j+=1;
			}
			
	
			$bksTotal=Array($bks,$transactionDate,$returndate);
			return $bksTotal;
			
		}
		
	
	
	
//get The profile picture of the student 
		function getProfilePic($id){
			
			$sqlCommand="SELECT ID,Name,PatronImage FROM patron WHERE id=$id";
			$statement=$this->db->prepare($sqlCommand);
			$statement->execute();
			//bind the returned values to v\variables
			$statement->bindColumn(1,$poll_id);
			$statement->bindColumn(2,$poll_question);
			$statement->bindColumn(3,$newVar,PDO::PARAM_LOB);
			
			$new=array();
			$i=0;
			while($statement->fetch()){
				if($newVar==NULL){
					
					$new="<img src='img/profile.jpg'  alt='no imgssss'><br/>";
				}else{
				file_put_contents("profilePics/".$poll_id.".jpg",$newVar);
					$new="<img src='profilePics/".$poll_id.".jpg'  alt='none imgs'><br/>";
				}
		}
		
			
		return $new;		
		}
		

		
//allow the student to upload their own profile picture		
		function uploadImage($stuID,$imgfp){
			
				/*** our sql query ***/
		
				$sqlCommand="UPDATE patron"; 
				$sqlCommand.=" SET PatronImage=(?)";
				$sqlCommand.=" WHERE ID=$stuID"; 
				$stmt = $this->db ->prepare ( $sqlCommand);
				/*** bind the params ***/
				$this->db->errorInfo();
				$stmt ->bindParam( 1 , $imgfp , PDO:: PARAM_LOB );
				/*** execute the query ***/
				$stmt ->execute ();
				
				/* $stmt=$this->db->query($sqlCommand);
				$rowcount=$stmt->rowcount();
				var_dump($rowCount); */
				
			
		}
	}				
?>