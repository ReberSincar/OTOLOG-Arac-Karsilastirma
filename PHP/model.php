<?php

include("ayar.php");
$marka_id=$_POST["marka_id"];

$model = mysqli_query($baglanti,"Select * from Modeller where marka_id='$marka_id' order by model_ad");
$boyut = mysqli_num_rows($model);
$sayac=0;

class Model
{
        public $modelid;
        public $markaid;
        public $markaad;
        public $modelad;
        public $modelyil;
        public $yakitid;
        public $vitesid;
        public $viteskademe;
        public $motorgucu;
        public $silindirsayisi;
        public $koltuksayisi;
        public $kapisayisi;
        public $yakitdepohacmi;
        public $agirlik;
        public $hiz;
        public $fiyat;
        public $puan;
}

$result = new Model();

echo("[");
if($boyut>0)
{
        while($atama=mysqli_fetch_assoc($model))
        {
                $sayac=$sayac+1;
                $result->modelid=$atama["id"];
                $result->markaid=$atama["marka_id"];
                $marka = mysqli_fetch_assoc(mysqli_query($baglanti,"Select marka_ad from Markalar where id='$result->markaid'"));
                $result->markaad=$marka["marka_ad"];
                $result->modelad=$atama["model_ad"];
                $result->modelyil=$atama["model_yil"];
                $result->yakitid=$atama["yakit_id"];
                $yakit=mysqli_fetch_assoc(mysqli_query($baglanti,"Select yakit_turu from Yakitlar where id='$result->yakitid'"));
                $result->yakitid=$yakit["yakit_turu"];
                $result->vitesid=$atama["vites_id"];
                $vites=mysqli_fetch_assoc(mysqli_query($baglanti,"Select vites_turu from Vitesler where id='$result->vitesid'"));
                $result->vitesid=$vites["vites_turu"];
                $result->viteskademe=$atama["vites_kademe"];
                $result->motorgucu=$atama["motor_gucu"];
                $result->silindirsayisi=$atama["silindir_sayisi"];
                $result->koltuksayisi=$atama["koltuk_sayisi"];
                $result->kapisayisi=$atama["kapi_sayisi"];
                $result->yakitdepohacmi=$atama["yakit_depo_hacmi"];
                $result->agirlik=$atama["agirlik"];
                $result->hiz=$atama["hiz"];
                $result->fiyat=$atama["fiyat"];
                $result->puan=$atama["puan"];
                
                echo(json_encode($result));
                
                if($boyut>$sayac)
                {
                        echo(",");
                }
                

        }
}
else
{
        $result->tf=false;
        echo(json_encode($result));
}
echo("]");

















?>