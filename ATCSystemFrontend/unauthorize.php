<?php

include("functions/functions.php");
include("includes/db.php");

?>

<?php

include("header.php");

?>

 <div class="crumb_navigation"> Navigation: <span class="current">Unauthorized Cars</span><br> </div>
   <div class="center_content">
      <div class="center_title_bar">Pictures of Unauthorized Cars</div>





<?php

$dir_path = "../../TollSystem/una_image/";
$extensions_array = array('bmp','jpg','png','jpeg');

if(is_dir($dir_path))
{
    $files = scandir($dir_path);

    $x=count($files)-1;
    

    //for($i = 0; $i < count($files); $i++)
    for($i = $x; $i > 0; $i--)
    {
        if($files[$i] !='.' && $files[$i] !='..')
        {
            
            
            // get file extension
            $file = pathinfo($files[$i]);
            $extension = $file['extension'];
            //echo "File Extension-> $extension<br>";
            
           // check file extension
            if(in_array($extension, $extensions_array))
            {


            // show image
            echo " <br><br>  <img src='$dir_path$files[$i]' style='width:240px;height:320px;'><br>";
            // get file name
            echo "<h3>Passing Time:</h3><h2>$files[$i]</h2><br><br><br><ln>";

            
           
            }
        }
    }
}

?>














<div class="footer">
    <div class="left_footer">  </div>
    <div class="center_footer"> All Rights Reserved @TAREQ<br><br />
          <img src="images/payment.gif" alt="" /> </div>
     </div>

 </html>


