<?php
include("ayar.php");

$markaid=$_GET["markaid"];
$modelid=$_GET["modelid"];
$userid=$_GET["userid"];
$userpuan=$_GET["userpuan"];

class Result
{
        public $puan;
        public $tf;
        public $sonuc;
}

$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Puanlar where user_id='$userid' and marka_id='$markaid' and model_id='$modelid'");
$boyutkontrol=mysqli_num_rows($kontrol);

if($boyutkontrol==0)
{
        $ekle = mysqli_query($baglanti,"Insert into Puanlar(user_id,marka_id,model_id,puan) Values('$userid','$markaid','$modelid','$userpuan')");

        $sorgu = mysqli_query($baglanti,"Select puan from Puanlar where marka_id='$markaid' and model_id='$modelid'");
        
        $kisiSayisi=mysqli_num_rows($sorgu);
        
        while($atama=mysqli_fetch_assoc($sorgu))
        {
                $puan=$puan+$atama["puan"];
        }              
        
        $puan=$puan/$kisiSayisi;
        
        $update = mysqli_query($baglanti,"Update Modeller Set puan='$puan' where marka_id='$markaid' and id='$modelid'");
        
        if($update)
        {
                $result->puan=$puan;
                $result->tf=true;
                $result->sonuc="Puaniniz Eklendi";
        }           
        
}
else
{
        $guncelle = mysqli_query($baglanti,"Update Puanlar Set puan='$userpuan' where user_id='$userid' and marka_id='$markaid' and model_id='$modelid'");

        $sorgu = mysqli_query($baglanti,"Select puan from Puanlar where marka_id='$markaid' and model_id='$modelid'");
        
        $kisiSayisi=mysqli_num_rows($sorgu);
        
        while($atama=mysqli_fetch_assoc($sorgu))
        {
                $puan=$puan+$atama["puan"];
        }              
        
        $puan=$puan/$kisiSayisi;
        
        $update = mysqli_query($baglanti,"Update Modeller Set puan='$puan' where marka_id='$markaid' and id='$modelid'");
        
        if($update)
        {
                $result->puan=$puan;
                $result->tf=true;
                $result->sonuc="Puaniniz Güncellendi";
        }
        /*
        $result->tf=false;
        $result->sonuc="Zaten Bu Araca Puan Verdiniz";*/
}



echo(json_encode($result));




?>