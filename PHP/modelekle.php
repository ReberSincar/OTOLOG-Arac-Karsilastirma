<?php
include("ayar.php");

$markaid=$_POST["markaid"];
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
$puan=0;

/*$markaid=19;
$modelad="5.20";
$modelyil="2018";
$yakitid="1";
$vitesid="1";
$viteskademe=6;
$motorgucu=1.8;
$silindirsayisi=4;
$koltuksayisi=5;
$kapisayisi=4;
$yakitdepohacmi=45;
$agirlik=1600;
$hiz=285;
$fiyat=260000;
$puan=0;*/

class Result
        {
                public $markaid;
                public $modelid;
                public $tf;
                public $sonuc;
        }
        
$result = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Modeller where marka_id='$markaid' and model_ad='$modelad'");
$kontrol = mysqli_num_rows($kontrol);

if($kontrol==0)
{
        
        $ekle = mysqli_query($baglanti,"insert into Modeller(
        marka_id,
        model_ad,
        model_yil,
        yakit_id,
        vites_id,
        vites_kademe,
        motor_gucu,
        silindir_sayisi,
        koltuk_sayisi,
        kapi_sayisi,
        yakit_depo_hacmi,
        agirlik,
        hiz,
        fiyat,
        puan)
        Values(
        '$markaid',
        '$modelad',
        '$modelyil',
        '$yakitid',
        '$vitesid',
        '$viteskademe',
        '$motorgucu',
        '$silindirsayisi',
        '$koltuksayisi',
        '$kapisayisi',
        '$yakitdepohacmi',
        '$agirlik',
        '$hiz',
        '$fiyat',
        '$puan')");
        
        if($ekle)
        {
                $sorgu=mysqli_fetch_assoc(mysqli_query($baglanti,"Select id,marka_id from Modeller where model_ad='$modelad'"));
                $result->markaid=$sorgu["marka_id"];
                $result->modelid=$sorgu["id"];
                $result->tf=true;
                $result->sonuc="Model Eklendi";
        }
        else
        {
                $result->tf=false;
                $result->sonuc="Model Eklenemedi";
        }
}
else
{
        $result->tf=false;
        $result->sonuc="Model Zaten Var";
}

echo(json_encode($result));



?>