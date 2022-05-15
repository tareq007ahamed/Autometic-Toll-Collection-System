
<?php


  include("includes/db.php");
   ?>





<!DOCTYPE html >
<html>
<head>





<title>Automated Toll Management - Admin Area</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style.css" />

<script type="text/javascript" src="js/boxOver.js"></script>


<style type="text/css">
.style1 {
	width: 585px;
	float: left;
	padding: 5px 10px;
	text-align: center;
}
.style2 {
	width: 520px;
	height: 33px;
	float: left;
	padding: 0 0 0 40px;
	margin: 0 0 0 12px;
	_margin: 0 0 0 6px;
	line-height: 33px;
	font-size: 12px;
	color: #847676;
	font-weight: bold;
	background: url(images/bar_bg.gif) no-repeat center;
	text-align: center;
}
.style3 {
	width: 355px;
	float: left;
	padding: 0px 0 0 75px;
	text-align: left;
}
</style>


</head>
<body>
<div id="main_container">
  <div id="header">
    <div id="logo"> <a href="#"><img src="images/lintellogo.JPG" alt="" border="0" width="237" height="140" /></a> </div>
    <!-- end of oferte_content-->
  </div>
  <div id="main_content">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <!-- end of menu tab -->
    <!-- end of left content -->


    <div id="menu_tab">
      <div class="left_menu_corner"></div>
      <ul class="menu">
        <li><a href="../index.php" class="nav1"> Home</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="details.php" class="nav1">My Profile</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="index.php" class="nav1">Admin Home</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="insertdriver.php" class="nav1">Insert Driver Info</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="tollchart.php" class="nav1">Toll Chart</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="login.html" class="nav1">Admin_login</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        
       
        
        
      </ul>
      <div class="right_menu_corner"></div>
    </div>

    
    <form action="" method="POST" enctype="multipart/form-data">
    <div class="style1">
      <div class="style2">INSERT NEW CAR INFORMATION</div>
      <div class="prod_box_big">
        <div class="top_prod_box_big"></div>
        <div class="center_prod_box_big">

          <div class="style3">
           
            
      
             <div class="form_row">
              <label class="contact"><strong>Car Model:</strong></label>
              <input type="text" class="contact_input" name="car_model" required />
            </div>

            <div class="form_row">
              <label class="contact"><strong>Rfid Number:</strong></label>
              <input type="text" class="contact_input" name="rfid_no" required />
            </div>
            
            
            <div class="form_row">
              <label class="contact"><strong>Car Type:</strong></label>
              
           <select name="car_type" required>
           
           <option></option>
           
           
           <?php
   
          	$get_cats = "select * from cartype";
          	$run_cats = mysqli_query($con,$get_cats);

          	while($row_cats=mysqli_fetch_array($run_cats)){

          	$cat_tit = $row_cats['type'];
              $cat_id = $row_cats['id'];
               echo "<option value=$cat_id>$cat_tit</option>";    

               }    
           ?>
           </select>
            </div>
            
            <div class="form_row">
              <label class="contact"><strong>Car Image:</strong></label>
              <input type="file" class="contact_input" name="product_image" required />
            </div>
            <div class="form_row">
              <label class="contact"><strong>Car's No. Plate:</strong></label>&nbsp;
              <input type="text" class="contact_input" name="no_plate" required /></div>
            <div class="form_row"> 
				

			 <div class="form_row">
                            &nbsp;<input type="submit" class="contact_input" name="index" value="INSERT" style="width: 336px"/>&nbsp;<br />
              
            
            </div>


	
 </div>
				
				
          </div>
        </div>
        
      
        
        <div class="bottom_prod_box_big"></div>
      </div>
    &nbsp;<br>
    </div>
    
</form>    
    <!-- end of center content -->
    <!-- end of right content -->
  </div>
  <!-- end of main content -->
  <div class="footer">
    <div class="left_footer">  </div>
    <div class="center_footer"> &nbsp;All Rights Reserved@TAREQ<br><br />
      	<br />
      <img src="images/payment.gif" alt="" /> </div>
    <div class="right_footer"> <a href="#">home</a> <a href="#">about</a> <a href="#">contact us</a> </div>
  </div>
</div>
<!-- end of main_container -->
</body>
</html>


<?php


if(isset($_POST['index'])){

    //getting text data
   $car_model = $_POST['car_model'];
   $rfid_no = $_POST['rfid_no'];
   $car_type = $_POST['car_type'];
   $no_plate = $_POST['no_plate'];
    //getting image data
   $product_image = $_FILES['product_image']['name'];
   $product_image_tmp = $_FILES['product_image']['tmp_name'];
   
   move_uploaded_file($product_image_tmp,"product_images/$product_image");
   
   $insert_product = "insert into car (model,rfid,image,no_plate,CarType_id) values ('$car_model','$rfid_no','$product_image','$no_plate','$car_type')";
   
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