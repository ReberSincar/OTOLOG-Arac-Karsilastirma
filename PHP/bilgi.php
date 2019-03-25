<?php

include("ayar.php");

$userid=$_POST["userid"];

class Result
{
        public $password;
        public $ad;
        public $soyad;
        public $mail;
        public $tf;
}

$result = new Result();

$sorgu = mysqli_query($baglanti,"Select * from Kullanicilar where id='$userid'");

if($sorgu)
{
        $atama = mysqli_fetch_assoc($sorgu);
        
        $result->password=$atama["sifre"];
        $result->ad=$atama["ad"];
        $result->soyad=$atama["soyad"];
        $result->mail=$atama["mail"];
        $result->tf=true;
}
else
{
        $result->tf=false;
}

echo(json_encode($result));




?>