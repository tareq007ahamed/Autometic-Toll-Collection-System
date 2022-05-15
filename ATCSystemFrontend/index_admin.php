<?php

include("functions/functions.php");
include("includes/db.php");


?>

<?php

include("header.php");

?>


    <!-- end of menu tab -->
    <div class="crumb_navigation"> Navigation: <span class="current">Home</span>
    

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

     
      <!--<div class="banner_adds"> <a href="#"><img src="images/bann2.jpg" alt="" border="0" /></a> </div>-->
      
           <br>
 <br>
 <br>
     
 

    </div>
    
    <div class="center_content">
      <div class="center_title_bar">Latest added cars</div>


<?php  

getpro();
get_pro_cat();
get_pro_brand();

 ?>



<!-- end of center content
 <div class="center_title_bar">Recommended Products</div>
  
  
 <?php  getpro3(); ?>


  </div>
  
 
  
  
 
     
<div class="right_content">
      <div class="shopping_cart">
        <div class="cart_title"><i>Welcome Guest!</i><br>Shopping cart</div>
        <div class="cart_details"><font color="blue"><?php total_items(); ?></font>&nbsp;item(s) <br />
          <span class="border_cart"></span> Total:<span class="price"><?php total_price(); ?> </span> </div>
        <div class="cart_icon"><a href="cart.php" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img src="images/shoppingcart.png" alt="" width="48" height="48" border="0" /></a></div>
      </div>
      
       
       -->
     

     
 
      <!-- end of right content -->
</div>
  <!-- end of main content -->
  <div class="footer">
    <div class="left_footer">  </div>
    <div class="center_footer"> All Rights Reserved @TAREQ<br><br />
          <img src="images/payment.gif" alt="" /> </div>
     </div>
</div>
<!-- end of main_container -->
</body>
</html>
