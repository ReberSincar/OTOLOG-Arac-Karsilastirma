<?php
include("ayar.php");

$markaid=$_GET["markaid"];
$modelid=$_GET["modelid"];
$userid=$_GET["userid"];

class Result
{
        public $puan;
        public $tf;
}

$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Puanlar where user_id='$userid' and marka_id='$markaid' and model_id='$modelid'");
$boyutkontrol=mysqli_num_rows($kontrol);

if($boyutkontrol>0)
{
        $satir = mysqli_fetch_assoc($kontrol);
        
        $result->puan=$satir["puan"];
        $result->tf = true;
}
else
{
        $result->tf=false;
}

echo(json_encode($result));


?>