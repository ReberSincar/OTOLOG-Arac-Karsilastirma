<?php
include("ayar.php");

$markaad=$_POST["markaad"];
$logo =$_POST["logo"];

class Result
        {
                public $tf;
                public $sonuc;
        }
        
$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Markalar where marka_ad='$markaad'");
$kontrol = mysqli_num_rows($kontrol);

if($kontrol==0)
{
        $karakterler = "1234567890abcdefghijklmnopqrstuvwxyz";
        $imagename = rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)};
        $path = "Logo/$imagename.png";      
        
        
        $ekle = mysqli_query($baglanti,"insert into Markalar(marka_ad,logo) Values('$markaad','$path')");
        
        if($ekle)
        {
                file_put_contents($path,base64_decode($logo));
                $result->tf=true;
                $result->sonuc="Marka Eklendi";
        }
        else
        {
                $result->tf=false;
                $result->sonuc="Marka Eklenemedi";
        }
}
else
{
        $result->tf=false;
        $result->sonuc="Marka Zaten Var";
}

echo(json_encode($result));



?>