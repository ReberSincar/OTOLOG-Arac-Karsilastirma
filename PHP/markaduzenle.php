<?php
include("ayar.php");

$id=$_POST["id"];
$markaad=$_POST["markaad"];
$logo =$_POST["logo"];
$oldlogo=$_POST["oldLogo"];

class Result
        {
                public $tf;
                public $sonuc;
        }
        
$result = new Result();

$kontrol=mysqli_num_rows(mysqli_query($baglanti,"Select * from Markalar where id='$id'"));


if($kontrol>0)
{
        $karakterler = "1234567890abcdefghijklmnopqrstuvwxyz";
        $imagename = rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)};
        $path = "Logo/$imagename.png";  
                
        $duzenle = mysqli_query($baglanti,"Update Markalar Set marka_ad='$markaad',logo='$path' where id='$id'");
                
        if($duzenle)
        {
                file_put_contents($path,base64_decode($logo));
                unlink($oldlogo);
                $result->tf=true;
                $result->sonuc="Marka Güncellendi";
        }
        else
        {
                $result->tf=false;
                $result->sonuc="Marka Güncellenemedi";
        }
}
else
{
        $result->tf=false;
        $result->sonuc="Marka Bulunamadı";
}
        




echo(json_encode($result));



?>