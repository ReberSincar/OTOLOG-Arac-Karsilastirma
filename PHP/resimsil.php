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

$kontrol = mysqli_query($baglanti,"Select * from Resimler where marka_id='$markaid' and model_id='$modelid'");

$sayi = mysqli_num_rows($kontrol);

if($sayi>0)
{
        $silresim=mysqli_query($baglanti,"Delete from Resimler where marka_id='$markaid' and model_id='$modelid'");
        
        if($silresim)
        {
                $resimsayi=mysqli_num_rows($kontrol);
                while($atama=mysqli_fetch_assoc($kontrol))
                {
                        $resimpath=$atama["resim"];
                        unlink($resimpath);
                }
                
                $result->tf=true;
                $result->sonuc="Seçtiğiniz Modelin Resimleri Silindi";
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
        $result->sonuc="Seçtiğiniz Modelin Resimleri Zaten Yok";
}
   
echo(json_encode($result));

?>