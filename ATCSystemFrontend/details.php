
<?php
include("functions/functions.php");
?>

<?php

include("header.php");




?>




    <!-- end of menu tab 


    <div class="crumb_navigation"> Navigation: <a href="#">Home</a> &lt; <span class="current">Category name</span> </div>
    <div class="left_content">
   


      <div class="title_box">Special Product</div>
     

<?php

special();


?>



      <br><br><br><br>

 <div class="title_box">Popular Product</div>
     

<?php

special();


?>
        
        <br><br><br><br>
      <div class="banner_adds"> <a href="details.php?pro_id=20"><img src="images/bann2.jpg" alt="" border="0" /></a> </div>
    </div>
    -->
    
    
    <!-- end of left content -->
    <div class="center_content">
     
      
<?php

details();

?>



      <!-- end of center content 

      <div class="center_title_bar">Similar products</div>
    


  <?php
  
  getpro3();
  
  ?>
     
      
      
      
    </div>

    <div class="right_content">
      <div class="shopping_cart">
        <div class="cart_title"><i>Welcome Guest!</i><br>Shopping cart</div>
        <div class="cart_details"><font color="blue"><?php total_items(); ?></font>&nbsp;item(s)<br />
          <span class="border_cart"></span> Total: <span class="price"><?php total_price(); ?></span> </div>
        <div class="cart_icon"><a href="#" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img src="images/shoppingcart.png" alt="" width="48" height="48" border="0" /></a></div>
      </div>
      
      <br><br><br><br>
      
      
      <div class="title_box">What’s new</div>
     

<?php

special();


?>     -->
     
     
     <br /><br /><br /><br />
     
     
      
    </div>
    <!-- end of right content -->
  </div>
  <!-- end of main content -->
  <div class="footer">
    <div class="left_footer">  </div>
    <div class="center_footer"> All Rights Reserved @TAREQ<br><br />
      
      <img src="images/payment.gif" alt="" /> </div>
    <div class="right_footer"> <a href="#">home</a> <a href="#">about</a> <a href="#">sitemap</a> <a href="#">rss</a> <a href="contact.html">contact us</a> </div>
  </div>
</div>
</body>
</html>
