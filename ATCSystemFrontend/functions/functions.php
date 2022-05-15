<?php

 $con = mysqli_connect("localhost","root","","atcsystem");

 if(mysqli_connect_errno()){
  
  echo"Failed to connect : " . mysqli_connect_error(); 
  
}

//getting categories

function getcats(){

	global $con;

	$get_cats = "select * from cartype";
	$run_cats = mysqli_query($con,$get_cats);

	while($row_cats=mysqli_fetch_array($run_cats)){


		$cat_title = $row_cats['type'];
        $cat_id = $row_cats['id'];
	
    echo "<li class=even><a href='index.php?cat=$cat_id'>$cat_title</a></li>";
	}



}



//getting brands

function getbrands(){

	global $con;

	$get_brands = "select * from brands";
	$run_brands = mysqli_query($con,$get_brands);

	while($row_brands=mysqli_fetch_array($run_brands)){

		$brand_title = $row_brands['brand_title'];
        $brand_id = $row_brands['brand_id'];
	
    echo "<li class=even><a href='index.php?brand=$brand_id'>$brand_title</a></li>"; 

    
	}



}



function getpro(){


	if(!isset($_GET['cat'])){

		if(!isset($_GET['brand'])){

	global $con;

    $get_pro = "select * from car order by RAND() LIMIT 0,6";
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

    }

    }


}


function special(){

	global $con;

    $get_pro = "select * from products order by RAND() LIMIT 0,1";
    $run_pro = mysqli_query($con,$get_pro);
    


    while($row_pro = mysqli_fetch_array($run_pro)){


        $product_id = $row_pro['prd_id'];
        $product_title = $row_pro['prd_title']; 
        $product_image = $row_pro['prd_img'];
        $product_price= $row_pro['prd_price'];


        echo"

                <div class='border_box'>
        <div class='product_title'>$product_title</div>
        <div class='product_img'><a href='details.php?pro_id=$product_id'><img src='admin_area/product_images/$product_image' alt='' border='0' width=90 height=110 /></a></div>
        <div class='prod_price'> <span class='price'>tk $product_price</span></div>
      </div>





        ";

}
}







//details data

function details(){

  global $con;


  if(isset($_GET['pro_id'])){

    $prod_id = $_GET['pro_id']; 

     
    $get_pro = "SELECT *
FROM drivercar
  INNER JOIN driver
    ON driver.id = drivercar.Driver_id
  INNER JOIN car
    ON car.id = drivercar.Car_id
WHERE drivercar.Driver_id  = '$prod_id' ";
    
    //$get_pro = "select * from products where prd_id = '$prod_id' ";
   // $get_pro = "select * from products INNER JOIN products ON products.prd_brand=brands.brand_id INNER JOIN products ON products.prd_cat=categories.cat_id where products.prd_id = '$prod_id' ";
    $run_pro = mysqli_query($con,$get_pro);



   // if (mysqli_num_rows($run_pro)>0){
    


    while($row_pro = mysqli_fetch_array($run_pro)){

 $get_pro = "select * from drivercar INNER JOIN  driver ON driver.id=drivercar.Driver_id INNER JOIN car ON car.id=drivercar.Car_id where drivercar.Driver_id = '$prod_id'";


        $driver_name = $row_pro['driver.name'];
        $driver_license = $row_pro['driver.license_no'];
        $driver_address = $row_pro['driver.address'];
        $driver_balance = $row_pro['driver.balance'];
        $car_model = $row_pro['car.model'];
        $car_rfid = $row_pro['car.rfid'];
        $car_image = $row_pro['car.image'];
        $car_no_plate = $row_pro['car.no_plate'];
        


    echo"
                  

                 <div class='center_title_bar'>$driver_name</div>
                 <div class='prod_box_big'>
        <div class='top_prod_box_big'></div>
        <div class='center_prod_box_big'>
          <div class='product_img_big'> <img src='admin_area/product_images/$car_image' height=200 width=185 alt='' border='0' /></a>
            </div>
          <div class='details_big_box'>
            <div class='product_title_big'>$driver_name</div>
            <div class='specifications'>Address: $driver_address</div>
             <div class='product_title_big'>license No: $driver_license</div>
             <div class='product_title_big'>RFID No: $car_rfid</div>
             <div class='specifications'>Car Type: $car_model</div>
            <div class='specifications'>Car Brand: $car_no_plate</div>
            <div class='prod_price_big'> <span class='price'>Balance:tk $driver_balance</span></div>

            <a href='index_admin.php' class='compare'>Home</a> </div>
        </div>
        <div class='bottom_prod_box_big'></div>
      </div>







    ";
}

//}

}
}


function get_pro_cat(){


	if(isset($_GET['cat'])){


		$cat_id = $_GET['cat'];

		

	global $con;

    $get_pro_cat = "select * from products where prd_cat ='$cat_id' ";
    $run_pro_cat = mysqli_query($con,$get_pro_cat);
    
    $count_row = mysqli_num_rows($run_pro_cat);

     if($count_row==0){

    	echo "<script>alert('OUT OF STOCK')</script>";

    	echo "<script>window.open('index.php','_self')</script>";
    }


    while($row_pro_cat = mysqli_fetch_array($run_pro_cat)){

           


           $product_id = $row_pro_cat['prd_id'];
        $product_category = $row_pro_cat['prd_cat'];
        $product_brand = $row_pro_cat['prd_brand'];
        $product_title = $row_pro_cat['owner_name']; 
        $product_price = $row_pro_cat['balance'];
        $product_image = $row_pro_cat['prd_img'];
       
        

       
         



        echo"
                <div class='prod_box'>
        <div class='top_prod_box'></div>
        <div class='center_prod_box'>
          <div class='product_title'><a href='details.php?pro_id=$product_id'>$product_title</a></div>
          <div class='product_img'><a href='details.php?pro_id=$product_id'><img src='admin_area/product_images/$product_image' alt='' border='0' width='90' height='110' /></a></div>
          <div class='prod_price'><span class='price'>tk $product_price</span></div>
        </div>
        <div class='bottom_prod_box'></div>
        <div class='prod_details_tab'> <a href='index.php?pro_id=$product_id' title='header=[Add to cart] body=[&nbsp;] fade=[on]''><img src='images/cart.gif' alt='' border='0' class='left_bt' /></a>
          <a href='details.php?pro_id=$product_id' class='prod_details'>details</a> </div>
      </div>


               

              


        ";


    }

    }

    


}

function get_pro_brand(){


	

		if(isset($_GET['brand'])){

			$brand_id = $_GET['brand'];

	global $con;

    $get_pro = "select * from products where prd_brand = '$brand_id'";
    $run_pro = mysqli_query($con,$get_pro);
    


    while($row_pro = mysqli_fetch_array($run_pro)){


        $product_id = $row_pro['prd_id'];
        $product_category = $row_pro['prd_cat'];
        $product_brand = $row_pro['prd_brand'];
        $product_title = $row_pro['prd_title']; 
        $product_price = $row_pro['prd_price'];
        $product_image = $row_pro['prd_img'];
        
          /*echo "<img src = 'admin_area/product_images/$row_pro[prd_img]' height=110 width=90>";*/



        echo"
                <div class='prod_box'>
        <div class='top_prod_box'></div>
        <div class='center_prod_box'>
          <div class='product_title'><a href='details.php?pro_id=$product_id'>$product_title</a></div>
          <div class='product_img'><a href='details.php?pro_id=$product_id'><img src='admin_area/product_images/$product_image' alt='' border='0' width='90' height='110' /></a></div>
          <div class='prod_price'><span class='price'>tk $product_price</span></div>
        </div>
        <div class='bottom_prod_box'></div>
        <div class='prod_details_tab'> <a href='index.php?pro_id=$product_id' title='header=[Add to cart] body=[&nbsp;] fade=[on]''><img src='images/cart.gif' alt='' border='0' class='left_bt' /></a>
          <a href='details.php?pro_id=$product_id' class='prod_details'>details</a> </div>
      </div>


               

              


        ";


    

    }

    }


}








function getpro3(){

	global $con;

    $get_pro = "select * from products order by RAND() LIMIT 0,3";
    $run_pro = mysqli_query($con,$get_pro);
    


    while($row_pro = mysqli_fetch_array($run_pro)){


        $product_id = $row_pro['prd_id'];
        $product_category = $row_pro['prd_cat'];
        $product_brand = $row_pro['prd_brand'];
        $product_title = $row_pro['owner_name']; 
        $product_price = $row_pro['balance'];
        $product_image = $row_pro['prd_img'];
        
          /*echo "<img src = 'admin_area/product_images/$row_pro[prd_img]' height=110 width=90>";*/



        echo"
                <div class='prod_box'>
        <div class='top_prod_box'></div>
        <div class='center_prod_box'>
          <div class='product_title'><a href='details.php?pro_id=$product_id'>$product_title</a></div>
          <div class='product_img'><a href='details.php?pro_id=$product_id'><img src='admin_area/product_images/$product_image' alt='' border='0' width='90' height='110' /></a></div>
          <div class='prod_price'><span class='price'>tk $product_price</span></div>
        </div>
        <div class='bottom_prod_box'></div>
        <div class='prod_details_tab'> <a href='index.php?addcart=$product_id' title='header=[Add to cart] body=[&nbsp;] fade=[on]''><img src='images/cart.gif' alt='' border='0' class='left_bt' /></a>
          <a href='details.php?pro_id=$product_id' class='prod_details'>details</a> </div>
      </div>

        ";


    }

}


function getip(){

   $ip = $_SERVER['REMOTE_ADDR'];


   if(!empty($_SERVER['HTTP_CLIENT_IP'])){
     
     $ip = $_SERVER['HTTP_CLIENT_IP'];


   }elseif(!empty($_SERVER['HTTP_X_FORWARDED_FOR'])){

     $ip = $_SERVER['HTTP_X_FORWARDED_FOR'];


   }

    return $ip;
}


/*function cart(){
   
   if(isset($_GET['addcart'])) {

     global $con;
     $ip = getip();
     $pro_id = $_GET['addcart'];

     $checkpro = "select * from cart where ip_add = '$ip' AND p_id = '$pro_id'";
     $run_checkpro = mysqli_query($con,$checkpro);


     if(mysqli_num_rows($run_checkpro)>0){


       echo "";


     }else{


               $insertpro = "insert into cart (p_id,ip_add,qty) values ('$pro_id','$ip','1')";  


               $run_insertpro = mysqli_query($con,$insertpro);
               echo $ip;

               echo "<script>window.open('','_self')</script>";        



     }



     
    

   }

}*/

function total_items(){


   if(isset($_GET['addcart'])){

     global $con;

     $ip = getip();

     $getitems = "select * from cart where ip_add = '$ip'";
     $run_getitems = mysqli_query($con,$getitems);

     $count_items = mysqli_num_rows($run_getitems);


   }

  else{

     global $con;

     $ip = getip();

     $getitems = "select * from cart where ip_add = '$ip'";
     $run_getitems = mysqli_query($con,$getitems);

     $count_items = mysqli_num_rows($run_getitems);


  }

   echo $count_items;
}


function total_price(){

   $total = 0;
   global $con;

   $ip = getip();

   $price = "select * from cart where ip_add = '$ip'";

   $run_price = mysqli_query($con,$price) ;

   while($pprice = mysqli_fetch_array($run_price)){

      $pro_id = $pprice['p_id'];
      
      $prod_price = "select * from products where prd_id = '$pro_id'";

      $run_pro_price = mysqli_query($con,$prod_price);


      while($ppprice = mysqli_fetch_array($run_pro_price)){

         $product_price = array($ppprice['prd_price']);

         $price_sum = array_sum($product_price);

         $total +=$price_sum;


      }
      
      

   }

   echo  $total. "&nbsp;tk";   

}
 /*function getInstance() {
    if(!self::$_instance) { // If no instance then make one
      self::$_instance = new self();
    }
    return self::$_instance;
  }


function showcarlog(){
  
  $db = Database::getInstance();
  $mysqli = $db->getConnection();
  $query = "SELECT * FROM crlog ";
  $stmt= $mysqli->query($query);
  return $stmt;
  
}

 function getConnection() {
    return $this->_connection;
  }*/


?>