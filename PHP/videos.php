<?php
include("ayar.php");

$modelid=$_POST["modelid"];
$markaid =$_POST["markaid"];

class Video
{
        public $reklam;
        public $inceleme;
        public $kaza;
        public $tf;
}

$result = new Video();

$video = mysqli_query($baglanti,"Select reklam,inceleme,kazatest from Videolar where marka_id='$markaid' and model_id='$modelid'");

if($video)
{
        $video=mysqli_fetch_assoc($video);
        
        $result->reklam = $video["reklam"];
        $result->inceleme = $video["inceleme"];
        $result->kaza = $video["kazatest"];
        $result->tf = true;
        
}
else
        $result->tf=false;
        

echo(json_encode($result));

















?>