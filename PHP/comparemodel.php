<?php

include("ayar.php");
$marka_id=$_POST["marka"];
$model_id=$_POST["model"];

$model = mysqli_query($baglanti,"Select * from Modeller where id='$model_id' and marka_id='$marka_id'");
$boyut = mysqli_num_rows($model);

class Model
{
        public $mdlid;
        public $mrkid;
        public $mrkad;
        public $mdlad;
        public $mdlyil;
        public $yktid;
        public $vtsid;
        public $vtskademe;
        public $mtrgucu;
        public $slndrsayisi;
        public $kltksayisi;
        public $kpsayisi;
        public $yktdepohacmi;
        public $agrlk;
        public $hz;
        public $fyt;
        public $point;
}

$result = new Model();


if($boyut>0)
{

        $atama=mysqli_fetch_assoc($model);

        $result->mdlid=$atama["id"];
        $result->mrkid=$atama["marka_id"];
        $marka = mysqli_fetch_assoc(mysqli_query($baglanti,"Select marka_ad from Markalar where id='$result->mrkid'"));
        $result->mrkad=$marka["marka_ad"];
        $result->mdlad=$atama["model_ad"];
        $result->mdlyil=$atama["model_yil"];
        $result->yktid=$atama["yakit_id"];
        $yakit=mysqli_fetch_assoc(mysqli_query($baglanti,"Select yakit_turu from Yakitlar where id='$result->yktid'"));
        $result->yktid=$yakit["yakit_turu"];
        $result->vtsid=$atama["vites_id"];
        $vites=mysqli_fetch_assoc(mysqli_query($baglanti,"Select vites_turu from Vitesler where id='$result->vtsid'"));
        $result->vtsid=$vites["vites_turu"];
        $result->vtskademe=$atama["vites_kademe"];
        $result->mtrgucu=$atama["motor_gucu"];
        $result->slndrsayisi=$atama["silindir_sayisi"];
        $result->kltksayisi=$atama["koltuk_sayisi"];
        $result->kpsayisi=$atama["kapi_sayisi"];
        $result->yktdepohacmi=$atama["yakit_depo_hacmi"];
        $result->agrlk=$atama["agirlik"];
        $result->hz=$atama["hiz"];
        $result->fyt=$atama["fiyat"];
        $result->point=$atama["puan"];

}

echo(json_encode($result));

?>