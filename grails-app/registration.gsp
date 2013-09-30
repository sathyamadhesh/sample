<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
    <head>
        <link href="Downloads/bootstrap-3.0.0/dist/css/bootstrap.css" rel="stylesheet">
        <title>Login Page</title> 
         <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
     <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
     <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
     <link rel="stylesheet" href="/resources/demos/style.css" />


     <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <script type="text/javascript" >
 $(document).ready(function(){
  setTimeout(function(){
  $(".flash").fadeOut("slow", function () {
  $(".flash").remove();
      }); }, 2000);
 });
</script>



<style type="text/css">
    .flash{
width:759px;
padding-top:8px;
padding-bottom:8px;
background-color: #fff;
font-weight:bold;
font-size:20px;-moz-border-radius: 6px;-webkit-border-radius: 6px;

}
     .marginn
    {
      margin-top:50px;
    }
    .size30
    {
      font-size:30px;
    }
    .leng
    {
      margin-top:28px;
    }
    .size25
    {
      font-size:25px;
    }
    .bgcolor
    {
      background-color:LightGoldenRodYellow;
    }
    .norm
    {
      font-size:13px;
    }
    .len8
    {
      margin-top:8px;
    }
    .len22
    {
      margin-top:22px;
    }
    .size16
    {
      font-size:16px;
    }
    .margin20
    {
      margin-top:20px;
    }
    .margin40
    {
      margin-top:40px;
    }
    .grrr
    {
      background-color:#708090;
      color: white;
    }
    .colorbg
    {
      background-color:#5F6D7A;
      color:white;
    }
    .co1
    {
      background-color:#06293F;
    }
    .co
    {
      background-color:#0B293C;
    }
    .textbg
    {
      color: white;
    }
    .glyphicon
    {
      color:#7ccf29
    }
    .bg
    {
      color: #f9fafb;
    }
    .color1
    {
      color:black;
      background-color:#FFFFF1;
    }
    .wrapper 
    {
      position: absolute;
      bottom:0px;
      width: 100%;
    }
    img
    {
     width:100%;
     height:100%;
        position:fixed;
        z-index:-1;
        top:0%;
    }
  </style>    
    </head>
    <body>
      <img src="${resource(dir: 'images', file: 'image1.jpg')}" alt="Grails" class="img-responsive"/>
      
      
    <div class="container" style="margin-top:5%">
      <ul class="list-inline">
      <li class="col-md-4">
        <div align="center">
                    <font color="white"><h3>NEEV-TRAC</h3></font>
                   
                    <div class="flash co1">
                                
                                ${flash.message}</div>
           <!--  <font color="red"><h1> YOU WANT TO KNOW THE</h1></font>
            <font color="red"><h1>   NEEV-TRAC         </h1></font> -->

                    
              </div>
      </li>
      <li class="col-md-4"></li>
   
      <li class="col-md-4 pull-right">
                        <g:form action="#" controller="#">
                          <font color="white"><h3>SIGN IN</h3></font>
                          <input type="text" placeholder="Enter the Email EmailId" class="form-control" name="email" autofocus required="Enter the Email"><br>
                            <input type="password" placeholder="Password" class="form-control"name="password" autofocus required="Enter the Password">
                            <g:form action="#" controller="#" method="POST">
                                <a href="#">Forgot Password?</a>
                            </g:form>
                            <button type="submit" class="btn btn-default btn-primary "value="SIGN IN">SIGNIN</button>          
                        </g:form>             
          
        
          <g:form action="addUser" controller="user" method="POST">
                <font color="white"><h3>SIGN UP</h3></font>
                <input type="text"  placeholder="Enter the First Name" class="form-control" name="firstName" autofocus="Hello" required="Enter the First Name"><br>
                <input type="text"  placeholder="Enter the Last  Name" class="form-control" name="lastName" autofocus required="Enter the Last Name"><br>
                <input type="email" placeholder="Enetr the Email Id  " class="form-control" name="email" autofocus required="Enter the Email"><br>
                <input type="password" placeholder="Enter the Password" class="form-control" name="password" autofocus required="Enter the Password"><br>
                <button type="submit" class="btn btn-default btn-primary">SIGNUP</button>
<!--                 <input type="submit" value="SIGN UP"> -->
          </g:form>
      </li>
      </ul>
        </div>
        
   

  
     <div class="container wrapper ">
      <div class="col-md-6 textbg ">
            <font size="2">2013 &#169 Unify. ALL Rights Recentserved. <a href="#" class="glyphicon">Privacy Policy</a> | <a href="#" class="glyphicon">Terms of Service</a></font>
      </div>

     </div>
 
   </body>
</html>
