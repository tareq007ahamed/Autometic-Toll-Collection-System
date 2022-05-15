
<?php

include("functions/functions.php");
include("includes/db.php");
?>
<?php

include("header.php");

?>


    <!-- end of menu tab -->
    <div class="crumb_navigation"> Navigation: <span class="current">All Car Information</span>
    

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
    <div class="center_content">
      <div class="center_title_bar">All Car Information</div>


<?php  



$get_pro = "select * from car";
    $run_pro = mysqli_query($con,$get_pro);
    


    while($row_pro = mysqli_fetch_array($run_pro)){


        $product_id = $row_pro['id'];
        $product_model = $row_pro['model'];
        $product_rfid = $row_pro['rfid'];
        $product_image = $row_pro['image']; 
        $product_no_plate = $row_pro['no_plate'];
        $product_type = $row_pro['CarType_id'];
   


        echo"
                
                <div class='prod_box'>
        <div class='top_prod_box'></div>
        <div class='center_prod_box'>
          <div class='product_title'><a href='details.php?pro_id=$product_id'>$product_model</a></div>
          <div class='product_img'><a href='details.php?pro_id=$product_id'><img src='admin_area/product_images/$product_image' alt='' border='0' width='90' height='110' /></a></div>
          <div class='prod_price'><span class='price'>$product_no_plate</span></div>
        </div>
        <div class='bottom_prod_box'></div>
        <div class='prod_details_tab'> <a href='index.php?addcart=$product_id' title='header=[Add to cart] body=[&nbsp;] fade=[on]''><img src='images/cart.gif' alt='' border='0' class='left_bt' /></a>
          <a href='details.php?pro_id=$product_id' class='prod_details'>details</a> </div>
      </div>

               

              


        ";


    }









 ?>




  
 
  </div>
  
 
    <!-- end of center content 
<div class="right_content">
      <div class="shopping_cart">
        <div class="cart_title"><i>Welcome Guest!</i><br>Shopping cart</div>
        <div class="cart_details"><font color="blue"><?php total_items(); ?></font>&nbsp;item(s)<br />
          <span class="border_cart"></span> Total: <span class="price"><?php total_price(); ?></span> </div>
        <div class="cart_icon"><a href="cart.php" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img src="images/shoppingcart.png" alt="" width="48" height="48" border="0" /></a></div>
      </div>
      
       
       <br>
     
            

        <br>
     
 
        
             <br>
     
 
        
     <br>
     
 

      
         -->
</div>

  <div class="footer">
    <div class="left_footer">  </div>
    <div class="center_footer"> All Rights Reserved @TAREQ<br><br />
           <img src="images/payment.gif" alt="" /> </div>
    
  </div>
</div>
</body>
</html>
