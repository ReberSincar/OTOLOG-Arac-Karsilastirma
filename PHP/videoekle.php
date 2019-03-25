<?php

include("ayar.php");

$markaid=$_POST["markaid"];
$modelid=$_POST["modelid"];
$reklam=$_POST["reklam"];
$inceleme=$_POST["inceleme"];
$kaza=$_POST["kaza"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Videolar where marka_id='$markaid' and model_id='$modelid'");

$sayi = mysqli_num_rows($kontrol);

if($sayi==0)
{
        $ekle = mysqli_query($baglanti,"Insert into Videolar(marka_id,model_id,reklam,inceleme,kazatest) Values('$markaid','$modelid','$reklam','$inceleme','$kaza')");
        
        if($ekle)
        {
                $result->tf=true;
                $result->sonuc="Videolar Eklendi";
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
        $result->sonuc="Bu Modelin Zaten Videoları Mevcut İlk Önce Silin";
}

echo(json_encode($result));

?>