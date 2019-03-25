<?php

include("ayar.php");

$marka = mysqli_query($baglanti,"Select * from Markalar order by marka_ad");
$boyut = mysqli_num_rows($marka);
$sayac=0;

class Marka
{
        public $id;
        public $ad;
        public $logo;
        public $tf;
}

$result = new Marka();

echo("[");
if($boyut>0)
{
        while($atama=mysqli_fetch_assoc($marka))
        {
                $sayac=$sayac+1;
                $result->id=$atama["id"];
                $result->ad=$atama["marka_ad"];
                $result->logo=$atama["logo"];
                $result->tf=true;     
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