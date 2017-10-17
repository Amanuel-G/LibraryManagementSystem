<?php
	class PageData{
		public $title="";
		public $header="";
		public $footer="";
		public $content="";
		public $style="";
		public $script="";
		public $sideBar="";
		public $target="login";
		public $bottomScript="";
	
	public function addStyle($href){
		$this->style.="<link href='css/$href' rel='stylesheet'>";
		
	}
	public function addSliderStyle($href){
		$this->style.="<link href='css/$href' rel='stylesheet'>";
		
	}
	public function addSliderScript($href){
		$this->script.="<script src='engine0/$href'></script>";
	}
	public function changeTarget($new){
		$this->target=$new;
	
	}
	
	public function addBottomScript($href){
		$this->bottomScript.="<script src='js/$href'></script>";
	}
	public function addBottomSliderScript($href){
		$this->bottomScript.="<script src='engine0/$href'></script>";
	}
	public function addStyles($href){
		$this->style.="<link href='jquery/$href' rel='stylesheet'>";
		
	}
	public function addScript($href){
		$this->script.="<script src='js/$href'></script>";
		
	}
	
	public function addScripts($href){
		$this->script.="<script src='jquery/$href'></script>";
		
	}
	
	
	}
?>