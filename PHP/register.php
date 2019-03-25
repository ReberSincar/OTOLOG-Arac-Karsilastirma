<?php
include("ayar.php");

$ad=$_POST["ad"];
$soyad=$_POST["soyad"];
$mail=$_POST["email"];
$sifre=$_POST["sifre"];

class Result
{
        public $result;
        public $tf;
}

$sonuc = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Kullanicilar where mail='$mail'");

if(mysqli_num_rows($kontrol)==0)
{
        $ekle = mysqli_query($baglanti,"insert into Kullanicilar(ad,soyad,mail,sifre) Values('$ad','$soyad','$mail','$sifre')");
        if($ekle)
        {
               $sonuc->result="Kayit Basarili";
               $sonuc->tf=true;
        }
}
else
{
       $sonuc->result="Mail Zaten Kayitli";
       $sonuc->tf=false;
}

echo(json_encode($sonuc));


?>