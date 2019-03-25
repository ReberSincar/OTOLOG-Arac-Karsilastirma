<?php

include("ayar.php");

$markaid = $_POST["markaid"];
$modelid = $_POST["modelid"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Videolar where marka_id='$markaid' and model_id='$modelid'");

$sayi = mysqli_num_rows($kontrol);

if($sayi>0)
{
        $sorgu = mysqli_query($baglanti,"Delete from Videolar where marka_id='$markaid' and model_id='$modelid'");
        
        if($sorgu)
        {
                $result->tf=true;
                $result->sonuc="Seçtiğiniz Modelin Videoları Silindi";
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
        $result->sonuc="Seçtiğiniz Modelin Videoları Zaten Yok";
}

echo(json_encode($result));

?>