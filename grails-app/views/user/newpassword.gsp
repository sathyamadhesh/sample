<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css" />
    <script type="text/javascript">
      function ValidateUserRecord() 
      {
    //alert("Hi");
    var userpassword;
    userpassword null;
    userpassword = $('#signInPassword').val();
    if((userpassword == null ))
    {
        $('#signInPassword').focus();
        $('#lblError').css('display','block');
        $('#lblError').text('Password field is required.');
        return false;
    }
    else if(userpassword.length == 0)
    {
      $('#signInPassword').focus();
        $('#lblError').css('display','block');
        $('#lblError').text('Password Length Must Be atleast 5');
        return false;
    }
    
   
}
  
      $(document).ready(function(){
  setTimeout(function(){
  $(".flash").fadeOut("slow", function () {
  $(".flash").remove();
      }); }, 2000);
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
      
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head>
  <body>
    <h1>Enter Your New Password</h1>
    <g:form action="update" controller="user"  onsubmit="return ValidateUserRecord();">
      <input type="hidden" name="email" value="${email}">
      <input id="signInPassword" type="password" name="password">
      <button type="submit">EnterPassword</button>
    </g:form>
   <div class="flash">
              ${flash.message}
  </div>
  </body>
</html>
