<?php

include("ayar.php");

$markaid = $_POST["markaid"];
$modelid = $_POST["modelid"];
$image = $_POST["resim"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$karakterler = "1234567890abcdefghijklmnopqrstuvwxyz";
$imagename = rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)};
$path = "images/$imagename.jpg";      
        
        
$ekle = mysqli_query($baglanti,"Insert into Resimler(marka_id,model_id,resim) Values('$markaid','$modelid','$path')");
        
if($ekle)
{
        file_put_contents($path,base64_decode($image));
        $result->tf=true;
        $result->sonuc="Resim Eklendi";
}
else
{
        $result->tf=false;
        $result->sonuc="Bir Hata Oluştu";
}

echo(json_encode($result));

?>