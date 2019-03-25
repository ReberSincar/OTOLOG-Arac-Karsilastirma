<?php
include("ayar.php");

$id=$_POST["id"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$sorgu=mysqli_query($baglanti,"Select * from Markalar where id='$id'");

$kontrol=mysqli_num_rows($sorgu);

if($kontrol>0)
{
        $resim=mysqli_fetch_assoc($sorgu);
        $resim=$resim["logo"];
        $sil=mysqli_query($baglanti,"Delete from Markalar where id='$id'");
        
        if($sil)
        {        
                unlink($resim);
                $result->tf=true;
                $result->sonuc="Marka Kaldırıldı";
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
        $result->sonuc="Marka Bulunamadı";
}

echo(json_encode($result));


?>