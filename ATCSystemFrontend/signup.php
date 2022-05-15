<?php

include("functions/functions.php");
include("includes/db.php");


?>

<?php

include("header-index.php");

?>


    <!-- end of menu tab -->

    
    
   <form action="" method="POST" enctype="multipart/form-data">
    <div class="style1">
      <div class="center_title_bar">Sign Up For A Normal User</div>
      <div class="prod_box_big">
        <div class="top_prod_box_big"></div>
        
        <div class="center_prod_box_big">
        
        
        
          <div class="style3">
           
            
      
             <div class="form_row">
              <label class="contact"><strong>User Type:</strong></label>
              <select class="contact_input" name="user_type" required>
              <option value="normal">Normal</option>
            </select>
            </div>

            <div class="form_row">
              <label class="contact"><strong>Email:</strong></label>&nbsp;
              <input type="emali" class="contact_input" name="email" required /></div>

              <div class="form_row">
              <label class="contact"><strong>Password:</strong></label>&nbsp;
              <input type="password" class="contact_input" name="password" required /></div>

            
       <div class="form_row">
                            &nbsp;<input type="submit" class="contact_input" name="index" value="INSERT" style="width: 336px"/>&nbsp;<br />
                  
            </div>
          </div>
        </div>
        <div class="bottom_prod_box_big"></div>
      </div>
    &nbsp;<br>
    </div>
    
</form>


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


<?php


if(isset($_POST['index'])){

    //getting text data
   $user_type = "normal";
   $email = $_POST['email'];
   $password = $_POST['password'];


   $insert_product = "insert into user (email,password,user_type) values ('$email','$password','$user_type')";
   
   $run_product = mysqli_query($con,$insert_product);
   
   if($run_product){
   
   echo"<script>alert('Product Has been inserted')</script>";
   echo"<script>window.open('index.php?tn=$r','_self')</script>";
   }
   else{
    echo"<script>alert('Not inserted')</script>";


   }


}


?>