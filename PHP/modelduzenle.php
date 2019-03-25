<?php
include("ayar.php");

$markaid=$_POST["markaid"];
$modelid=$_POST["modelid"];
$modelad=$_POST["modelad"];
$modelyil=$_POST["modelyil"];
$yakitid=$_POST["yakitid"];
$vitesid=$_POST["vitesid"];
$viteskademe=$_POST["viteskademe"];
$motorgucu=$_POST["motorgucu"];
$silindirsayisi=$_POST["silindirsayisi"];
$koltuksayisi=$_POST["koltuksayisi"];
$kapisayisi=$_POST["kapisayisi"];
$yakitdepohacmi=$_POST["yakitdepohacmi"];
$agirlik=$_POST["agirlik"];
$hiz=$_POST["hiz"];
$fiyat=$_POST["fiyat"];
$puan=$_POST["puan"];

/*$markaid=2;
$modelid=9;
$modelad="26";
$modelyil="24";
$yakitid="5";
$vitesid="2";
$viteskademe=24;
$motorgucu=2.24;
$silindirsayisi=24;
$koltuksayisi=24;
$kapisayisi=24;
$yakitdepohacmi=42;
$agirlik=24;
$hiz=24;
$fiyat=24;
$puan=24;*/

class Result
        {
                public $markaid;
                public $modelid;
                public $tf;
                public $sonuc;
        }
        
$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Modeller where marka_id='$markaid' and id='$modelid'");
$kontrol = mysqli_num_rows($kontrol);

if($kontrol>0)
{
        
        $duzenle = mysqli_query($baglanti,"Update Modeller Set
        model_ad='$modelad',
        model_yil='$modelyil',
        yakit_id='$yakitid',
        vites_id='$vitesid',
        vites_kademe='$viteskademe',
        motor_gucu='$motorgucu',
        silindir_sayisi='$silindirsayisi',
        koltuk_sayisi='$koltuksayisi',
        kapi_sayisi='$kapisayisi',
        yakit_depo_hacmi='$yakitdepohacmi',
        agirlik='$agirlik',
        hiz='$hiz',
        fiyat='$fiyat',
        puan='$puan' Where id='$modelid' and marka_id='$markaid'");
        
        if($duzenle)
        {
                $result->markaid=$markaid;
                $result->modelid=$modelid;
                $result->tf=true;
                $result->sonuc="Model Güncellendi";
        }
        else
        {
                $result->tf=false;
                $result->sonuc="Model Güncellenemedi";
        }
}
else
{
        $result->tf=false;
        $result->sonuc="Model Bulunamadı";
}

echo(json_encode($result));



?>