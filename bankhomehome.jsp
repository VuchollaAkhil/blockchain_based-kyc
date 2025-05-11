<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>KYC</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="css/style.css">
      <!-- Responsive-->
      <link rel="stylesheet" href="css/responsive.css">
      <!-- fevicon -->
      <link rel="icon" href="images/fevicon.png" type="image/gif" />
      <!-- Scrollbar Custom CSS -->
      <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
      <!-- Tweaks for older IEs-->
      <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<body class="main-layout">
      <!-- loader  -->
      <div class="loader_bg">
         <div class="loader"><img src="images/loading.gif" alt="#" /></div>
      </div>
      <!-- end loader -->
      <!-- header -->
      <header>
         <!-- header inner -->
         <div class="header">
            <div class="white_bg">
               <div class="container-fluid">
                  <div class="row">
                     <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                        <div class="full">
                           <div class="center-desk">
                              <div class="logo">
                                 <a href="index.html"><img src="images/logo.png" alt="#" /></a>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
                        <nav class="navigation navbar navbar-expand-md navbar-dark ">
                           <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                           <span class="navbar-toggler-icon"></span>
                           </button>
                           <div class="collapse navbar-collapse" id="navbarsExample04">
                              <ul class="navbar-nav mr-auto">
                                 
                                 <li class="nav-item">
                                    <a class="nav-link" href="customerreq.jsp">New Customers</a>
                                 </li>
                                 <li class="nav-item">
                                    <a class="nav-link" href="index.html">Logout </a>
                                 </li>
                                 
                                 
                                 
                              </ul>
                           </div>
                        </nav>
                     </div>
                  </div>
               </div>
               
               
               <!-- services section --> 
      <div class="services">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="titlepage">
                     <h3> <span class="yellow"><h2>Services</h2></span><br>The purpose of banking is to facilitate financial transactions and provide services that support the functioning of an economy. </h3>
                  </div>
               </div>
               <div class="col-md-3 col-sm-6">
                  <div id="ho_color" class="services_main">
                     <i><img src="images/services_icon1.png" alt="#"/></i>
                     <img class="ho" src="images/customerinfo.jpg" alt="#"/>
                     <h3>Customer Information</h3>
                     
                     <a class="read_more" href="customerdetails.jsp">Customer Info</a>
                  </div>
               </div>
               <div class="col-md-3 col-sm-6">
                  <div id="ho_color" class="services_main">
                     <i><img src="images/services_icon2.png" alt="#"/></i>
                     <img class="ho" src="images/online-banking.jpg" alt="#"/>
                     <h3> Transaction List</h3>
                     
                     <a class="read_more" href="customerdeta.jsp">Customers Transactions</a>
                  </div>
               </div>
               <div class="col-md-3 col-sm-6">
                  <div id="ho_color" class="services_main">
                     <i><img src="images/services_icon3.png" alt="#"/></i>
                     <img class="ho" src="images/OIP.jpg" alt="#"/>
                     <h3> security</h3>
                     
                     <a class="read_more" href="provideservices.jsp">We Provide</a>
                  </div>
               </div>
               <div class="col-md-3 col-sm-6">
                  <div id="ho_color" class="services_main">
                     <i><img src="images/services_icon4.png" alt="#"/></i>
                     <img class="ho" src="images/bank1.jpg" alt="#"/>
                     <h3>Credit</h3>
                    
                     <a class="read_more" href="#">About Bank</a>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- end services section -->
	<!-- <a href="debit.jsp">Debit</a>
	<a href="credit.jsp">Credit</a>

	<a href="view_balance.jsp">View Balance</a>
	<a href="transaction.jsp">Transaction</a>
	<a href="view_statement.jsp">View Statement</a>
	<a href="customer_login.jsp">Logout</a> -->
	
	
	 <!-- Javascript files-->
      <script src="js/jquery.min.js"></script>
      <script src="js/popper.min.js"></script>
      <script src="js/bootstrap.bundle.min.js"></script>
      <script src="js/jquery-3.0.0.min.js"></script>
      <!-- sidebar -->
      <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="js/custom.js"></script>
	


</body>
</html>