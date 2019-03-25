<?php

include("ayar.php");

$resimId = $_GET["resimid"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Resimler where id='$resimId'");

$sayi = mysqli_num_rows($kontrol);

if($sayi>0)
{
        $silresim=mysqli_query($baglanti,"Delete from Resimler where id='$resimId'");
        
        if($silresim)
        {
        
                $atama=mysqli_fetch_assoc($kontrol);
                
                unlink($atama["resim"]);
                
                $result->tf=true;
                $result->sonuc="Seçtiğiniz Resim Silindi";
        }
        else
        {
                $result->tf=false;
                $result->sonuc="Bir Sorun Oluştu";
        }
}
else
{
        $result->tf=false;
        $result->sonuc="Seçtiğiniz Resim Bulunmadı";
}
   
echo(json_encode($result));

?>