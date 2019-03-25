<?php
include("ayar.php");

$markaid=$_POST["markaid"];
$modelid=$_POST["modelid"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$sorgu=mysqli_query($baglanti,"Select * from Modeller where id='$modelid' and marka_id='$markaid'");

$kontrol=mysqli_num_rows($sorgu);

if($kontrol>0)
{
        $resimsorgu=mysqli_query($baglanti,"Select * from Resimler where marka_id='$markaid' and model_id='$modelid'");
        $resimsayi=mysqli_num_rows($resimsorgu);
        while($atama=mysqli_fetch_assoc($resimsorgu))
        {
                $resimpath=$atama["resim"];
                unlink($resimpath);
        }
        
        $silresim=mysqli_query($baglanti,"Delete from Resimler where marka_id='$markaid' and model_id='$modelid'");
        $silvideo=mysqli_query($baglanti,"Delete from Videolar where marka_id='$markaid' and model_id='$modelid'");
        $silpuan=mysqli_query($baglanti,"Delete from Puanlar where marka_id='$markaid' and model_id='$modelid'");  
        $silkayit=mysqli_query($baglanti,"Delete from Modeller where id='$modelid' and marka_id='$markaid'");
        
        if($silkayit && $silvideo && $silresim && $silpuan)
        {
                $result->tf=true;
                $result->sonuc="Model Kaldırıldı";
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
        $result->sonuc="Model Bulunamadı";
}

echo(json_encode($result));


?>