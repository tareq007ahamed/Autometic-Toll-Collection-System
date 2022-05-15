
<?php

include("functions/functions.php");
include("includes/db.php");
?>
<?php

include("header.php");

?>


    <!-- end of menu tab -->
    <div class="crumb_navigation"> Navigation: <span class="current">Recharge Balance</span>
    

    <?php
    
    if(isset($_GET['cat'])){
    
    $get_id = $_GET['cat'];
    
    $query = "select cat_title from categories where cat_id='$get_id'";
    $run_query = mysqli_query($con,$query);
    
    $row = mysqli_fetch_array($run_query);
    
    $cat = $row['cat_title'];
    
    echo"<span class=current>>$cat</span>";
    
    }
    
    ?>


 </div>
    <div class="left_content">
      <div class="title_box">Categories</div>
      <ul class="left_menu">
        
 <?php 
     getcats(); 
     

 ?>
        
      </ul>
     
 <br>
 <br>

     
     <!-- <div class="banner_adds"> <a href="#"><img src="images/bann2.jpg" alt="" border="0" /></a> </div> -->
      
           <br>
 <br>
 <br>
     
 

    </div>
    <!-- end of left content -->
    <form action="" method="POST" enctype="multipart/form-data">
    <div class="center_content">
      <div class="center_title_bar">Recharge Balance</div><br><br>


    
      <div class="prod_box_big">
        <div class="top_prod_box_big"></div>
        <div class="center_prod_box_big">


  


           
        
        
        
         <div class="center_prod_box_big">
          <div class="style3">
           

              <div class="form_row">
              <label class="contact"><strong>Driver ID:</strong></label>
              <input type="number" class="contact_input" name="driver_id" required ></div><br><br><br>

              <div class="form_row">
              <!--<label class="contact"><strong>Recharge Ammount:</strong></label><br>-->
              <label class="contact"><strong>Recharge Ammount:</strong></label>
              <input type="text" class="contact_input" name="recharge_amm" required>
            </div>

            <div class="form_row">
                            &nbsp;<input type="submit" class="contact_input" name="index" value="Recharge" style="width: 336px"/>&nbsp;<br />
                            <br><br><br><br>
              
            
            </div></div></div>


            






<?php

echo"<br>";
?>
<div class="bottom_prod_box_big"></div>
      </div>
    &nbsp;<br>
    </div>
</form>
    

  <div class="footer">
    <div class="left_footer">  </div>
    <div class="center_footer"> All Rights Reserved @TAREQ<br><br />
           <img src="../images/payment.gif" alt="" /> </div>
    
  </div>
</div>
</body>
</html>

<?php


if(isset($_POST['index'])){

    //getting text data
   $recharge_amm = $_POST['recharge_amm'];
   $driver_id = $_POST['driver_id'];
   
   
   $recharge_bal = "UPDATE driver SET balance = balance+$recharge_amm WHERE id= $driver_id";
   
   $run_product = mysqli_query($con,$recharge_bal);
   
   if($run_product){
   
   echo"<script>alert('Balance Has been recharged')</script>";
   echo"<script>window.open('index.php?tn=$r','_self')</script>";
   }
   else{
    echo"<script>alert('Not recharged')</script>";


   }


}


?>
