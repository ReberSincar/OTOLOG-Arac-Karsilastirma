<?php
include("ayar.php");

$markaid=$_GET["markaid"];
$modelid=$_GET["modelid"];

$sorgu = mysqli_query($baglanti,"Select * from Resimler where marka_id='$markaid' and model_id='$modelid'");

class Result
{
        public $resim;
}

$result = new Result();

$say=mysqli_num_rows($sorgu);

echo("[");

if($say>0)
{
        $sayac=0;
        
        while($atama=mysqli_fetch_assoc($sorgu))
        {
                $sayac=$sayac+1;
                
                $result->resim=$atama["resim"];
                
                echo(json_encode($result));
                
                if($say>$sayac)
                        echo(",");
        }
               

}

else
        echo(json_encode($result));

echo("]");

?>