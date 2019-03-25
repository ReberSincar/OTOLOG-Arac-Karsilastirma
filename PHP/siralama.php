<?php
include("ayar.php");

class Result
{
        public $markaid;
        public $modelid;
        public $markaad;
        public $modelad;
        public $puan;
        public $logo;
}

$result = new Result();

$sorgu = mysqli_query($baglanti,"Select * from Modeller order by puan desc limit 10");

$say=mysqli_num_rows($sorgu);

echo("[");

if($say>0)
{
        while($atama=mysqli_fetch_assoc($sorgu))
        {
                $sayac=$sayac+1;
                
                $result->markaid=$atama["marka_id"];
                $result->modelid=$atama["id"];
                
                $marka=mysqli_fetch_assoc(mysqli_query($baglanti,"Select marka_ad,logo from Markalar where id='$result->markaid'"));
                $result->markaad=$marka["marka_ad"];
                $result->logo=$marka["logo"];
                
                $result->modelad=$atama["model_ad"];
                $result->puan=$atama["puan"];
                
                echo(json_encode($result));
                
                if($say>$sayac)
                        echo(",");
         }
         

}
else
        echo(json_encode($result));

echo("]");

?>