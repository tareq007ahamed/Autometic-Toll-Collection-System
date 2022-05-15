<!DOCTYPE html>
<html>

<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>

<body>



<?php

  include("includes/db.php");
   ?>











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
        <li><a href="index.php" class="nav1">Admin Home</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="insertdriver.php" class="nav1">Insert Driver Info</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="tollchart.php" class="nav1">Toll Chart</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="fixtoll.php" class="nav1">Update Toll Ammount</a></li>
        <li class="divider"></li>
        <li class="divider"></li>
        <li><a href="../recharge/index.php" class="nav5">Recharge Balance</a></li>
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
      <div class="style2">TOLL Chart</div>
      <div class="prod_box_big">
        <div class="top_prod_box_big"></div>
        <div class="center_prod_box_big">
        
        
        
          <div class="style3">
           
            <?php  



          $get_toll = "select * from toll INNER JOIN cartype ORDER by id";
              $run_pro = mysqli_query($con,$get_toll);
              


              


          echo"<table>
           <tr>
              
              <th>Car Type</th>
              <th>Toll Ammount</th>
            </tr>";
            while ($row_pro = mysqli_fetch_array($run_pro)){
                  $car_type = $row_pro['type'];
                  $car_toll = $row_pro['amount'];
                 
                

           

          echo "<tr>";

          echo "<td>" . $row_pro['type'] . "</td>";
          echo "<td>" . $row_pro['amount'] . "</td>";
          echo "</tr>";
          }
          echo "</table>";

          mysqli_close($con);
          ?>
    
            
            
				
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
   $car_type = $_POST['car_type'];
   $toll_amm = $_POST['toll_amm'];
   
   
   $insert_product = "insert into toll (amount,CarType_id) values ('$toll_amm','$car_type')";
   
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