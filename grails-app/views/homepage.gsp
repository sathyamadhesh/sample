<!DOCTYPE html>
<html lang="en">
<head>
  <script type="text/javascript">
  
window.onload = function preventback() 
  {
  window.history.forward();
  }
setTimeout("preventback()",1);
window.onunload=function(){null};

</script>
  <title>My new webpage</title>
<!--   <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
     <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
     <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
     <link rel="stylesheet" href="/resources/demos/style.css" />-->


     <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}">
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}">
   <script src="${resource(dir: 'js', file: 'jquery-2.0.3.js')}"></script>
<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>
<script type="text/javascript">
  
//  $(document).ready(function(){
//  setTimeout(function(){
//  $(".flash").fadeOut("slow", function () {
//  $(".flash").remove();
//      }); }, 2000);
    
$(document).ready(function(){
  $("#bar1").click(function(){
    
    $("#div2").hide().delay( 1000 );
    $("#div1").show("slow");
    
  });
});
$(document).ready(function(){
  $("#bar2").click(function(){

    $("#div1").hide().delay( 1000 );    
    $("#div2").show("slow");

  });
});

</script>
<!--<style type="text/css">
    .flash{
            width:759px;
            padding-top:8px;
            padding-bottom:8px;
            background-color: #fff;
            font-weight:bold;
            font-size:20px;-moz-border-radius: 6px;-webkit-border-radius: 6px;
          }
</style>-->
</head>
<body>

  
<div class="wrapper">
 <div class="container pull-right" style="margin-right:-1.2%">
<ul class="menu" >
<li id="bar1"><a ><button type="button" class="btn btn-link" style="color:#1CE62A;">Home</button></a></li>
<li id="bar2"><a href="#"><button type="button" class="btn btn-link" style="color:#1CE62A;" onclick="#account">My Account</button></a></li>
<li id="bar3"><a href="http://localhost:8080/trac/user/logout?token=${token}"><button type="button" class="btn btn-link" style="color:#1CE62A;">Log Out</button></a></li>
</ul>
</div>
</div>
<div class="container">
  <div class="col-md-3" style="margin-top:5%;margin-left:-4%">
    
<nav>
    <div class="menu-item alpha">
      <h4><a href="#">Home</a></h4>
      <p>Welcome To The Profile Page.Have fun.</p>
    </div>
       
    <div class="menu-item" id="menu-item-1">
      <h4><a href="#">Tickets</a></h4>
      <ul>
        <li id="VT"><a href="#">View Tickets</a></li>
        <li id="NT"><a href="#">New Tickets</a></li>
        <li><a href="#">Wiki</a></li>
        <li><a href="#">Search</a></li>
      </ul>
    </div>
       
    <div class="menu-item" id="menu-item-2">
      <h4><a href="#">Projects</a></h4>
      <ul>
        <li><a href="#">View projects</a></li>
        <li><a href="#">New Projects</a></li>
        <li><a href="#">Wiki</a></li>
        <li><a href="#">Search</a></li>
      </ul>
    </div>
       
    <div class="menu-item" id="menu-item-3">
      <h4><a href="#">Contact</a></h4>
      <ul>
        <li><a href="#">Phone</a></li>
        <li><a href="#">Email</a></li>
        <li><a href="#">Location</a></li>
      </ul>
    </div>
</nav>
</div>

<div class="col-md-9 body-boder" style="margin-top:9.3%;margin-left:0%">
  <div id="div1">
 <h1 align="center" style="color:#289EF2">Body Part</h1>
  <p class="flash"> 
    
    ${flash.message}
    
    <br>
    Its a humble request to write your all your code in this tag only. Don't Create unnecessary div every time. For further Assistance please Contact UI/UX Developer
  </p>
</div>
<div id = "div2">
  <div class="container" >
              
    <ul class="list-inline ">
      <li class="col-md-6">
     <h3 align="center" style="color:#289EF2">Change Password</h3>
     <div class="container">
      <ul class="list-inline">
        <li class="col-md-6">
          <p style="margin-top:5%">Old Password</p>
          <p style="margin-top:14%">New Password</p>
          <p style="margin-top:18%">Confirm Password</p>
        </li>
        <li class="col-md-6">
      <g:form method="post" action="modifyPassword" controller="user">
        <input type="hidden" name="token" value="${token}"/>
       <p style="margin-left:-3%"><input type="Password" class="form-control" name="oldpassword" value="" placeholder="Old Password"></p>
        <p style="margin-left:-3%"><input type="Password" class="form-control" name="newpassword" value="" placeholder="New Password"></p>
      <p style="margin-left:-3%"><input type="Password" class="form-control" name="confirmpassword" value="" placeholder="Confirm Password"></p>
      <p class="submit"><input type="submit" class="btn btn-primary pull-right" value="Submit"></p>
     </g:form>
   </li>
 </ul>
</div>
   </li>
<li class="col-md-6">
<h3 align="center" style="color:#289EF2">Personal Information</h3>
<div class="container">
      <ul class="list-inline">
        <li class="col-md-6">
          <p style="margin-top:5%">Name</p>
          <p style="margin-top:14%">Email Address</p>
          <p style="margin-top:18%">Date Of Birth</p>
          <p style="margin-top:15%">Designation</p>
        </li>
        <li class="col-md-6">
      <form method="post" action="save">
        
<!--       <input type="hidden" name="token" value="${token}"/> -->
      <p style="margin-left:-3%"><input type="text" class="form-control" name="Name" value="" placeholder="Name"></p>
      <p style="margin-left:-3%"><input type="text" class="form-control" name="Email" value="" placeholder="Email Address"></p>
      <p style="margin-left:-3%"><input type="text" class="form-control" name="Date Of Birth" value="" placeholder="Date Of Birth"></p>
      <p style="margin-left:-3%"><input type="text" class="form-control" name="Designation" value="" placeholder="Designation"></p>
      <p class="submit"><input type="submit" class="btn btn-primary pull-right" value="Submit"></p>
     </form>
   </li>
 </ul>
</div>
  </li>
</ul>
</div>
<hr/>
<h3 align="center" style="color:#F90D0D">Delete Account</h3>
<p><label style="width:25%;margin-left:37.5%"><input type="Password" class="form-control" name="password" value="" placeholder="Confirm Password"></label></p>
      <p class="submit" style="margin-left:47%"><input type="submit" class="btn btn-primary" value="Submit"></p>
</div>
</div>
</body>