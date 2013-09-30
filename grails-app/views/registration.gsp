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
          
          
          window.onload = function preventback() 
  {
  window.history.forward();
  }
setTimeout("preventback()",1);
window.onunload=function(){null};

 $(document).ready(function(){
  setTimeout(function(){
  $(".flash").fadeOut("slow", function () {
  $(".flash").remove();
      }); }, 2000);
 
  //Header animation    
   setInterval(function () {
        if ($('#lblNameError').css('display') != 'none') {
           //$('#lblError').css('display', 'none');
           $('#lblNameError').hide('slow');
       }
       if ($('#lblEmailError').css('display') != 'none') {
           $('#lblEmailError').hide('slow');
       }
       if ($('#lblPasswordError').css('display') != 'none') {
           $('#lblPasswordError').hide('slow');
       }
       if ($('#lblConfirmPasswordError').css('display') != 'none') {
           $('#lblConfirmPasswordError').hide('slow');
       }
       if ($('#lblError').css('display') != 'none') {
           //$('#lblError').css('display', 'none');
           $('#lblError').hide('slow');
       }
       
      
   }, 2000);
   
    //Validations
    //Email Validation
    
    $('#signUpEmail').change(function () {
        var validEmail = /[a-z0-9!#$%&'*+/=?^{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum|in)\b/
        if (!validEmail.test(this.value)) {
            $('#signUpEmail').css('background-color', 'white');
            $('#signUpEmail').val('');
            $('#signUpEmail').focus();
        }
        else {
            if ($('#signUpEmail').val().length > 0)
                $('#signUpEmail').css('background-color', 'white');
            else
                $('#signUpEmail').css('background-color', 'white');
           
        }
    });

    /*/Contact Validation
    $('#signUpContact').keydown(function (event) {      
        //Numbers(48 - 57) , Num Lock Numbers (96-105) , Del(46), Backspace (8) , Lef arrow (37) , Right arrow (39) , + (187) , Num Lock + (107)
        if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || (event.keyCode == 107 || event.keyCode == 187 || event.keyCode == 8 || event.keyCode == 46 || event.keyCode == 37 || event.keyCode == 39))) {
            event.preventDefault();
            return false;
        }
        else {
            if ($('#signUpContact').val().length > 0 && ((event.keyCode == 187 || (event.keyCode == 107)))) {
                event.preventDefault();
                return false;
            }
        }
        
    });*/

    //Validation for Confirm Password
    $('#signUpConfirmPassword').change(function () {
        var password = $('#signUpPassword').val()
        var confirmpassword = $('#signUpConfirmPassword').val();
        if (password != confirmpassword) {
            $('#signUpConfirmPassword').css('background-color', 'white');
            $('#signUpConfirmPassword').val('');
            $('#signUpConfirmPassword').focus();
        }
        else {
            
            if($('#signUpPassword').val().length>0)
                $('#signUpPassword').css('background-color', 'white');
            else
                $('#signUpPassword').css('background-color', 'white');
            if ($('#signUpConfirmPassword').val().length > 0)
                $('#signUpConfirmPassword').css('background-color', 'white ');
            else
                $('#signUpConfirmPassword').css('background-color', 'white');
        }
    });

    $('#signUpPassword').change(function () {
        if (($('#signUpConfirmPassword').val().length > 0) && ($('#signUpPassword').val() != $('#signUpConfirmPassword').val())) {
            $('#signUpConfirmPassword').css('background-color', 'white');
            $('#signUpConfirmPassword').val('');
            $('#signUpConfirmPassword').focus();
        }
        if ($('#signUpPassword').val().length > 0)
            $('#signUpPassword').css('background-color', 'white ');
        else
            $('#signUpPassword').css('background-color', 'white');
    });
    
    //Change background color of username
    $('#signUpName').change(function () {
        if ($('#signUpName').val().length > 0)
            $('#signUpName').css('background-color', 'white');
        else
            $('#signUpName').css('background-color', 'white');
    });

    
   
    //$('#signUpBtn').mouseover(function () {
    //    ValidateUserRecord();
    //});

});

//Required field validation
 function ValidateUserRecord() {
    //alert("Hi");
    var username, userpassword, confirmuserpassword, useremail;
    username = userpassword = confirmuserpassword = useremail = null;
    username = $('#signUpName').val();
    userpassword = $('#signUpPassword').val();
    confirmuserpassword = $('#signUpConfirmPassword').val();
    useremail = $('#signUpEmail').val();
   
    if ((username == null || username.length == 0)){
        $('#signUpName').focus();
        $('#lblNameError').css('display','block');
        $('#lblNameError').text('Name field is required.');
        
        return false;
    }
    else if((useremail == null || useremail.length == 0))
    {
        $('#signUpEmail').focus();
        $('#lblEmailError').css('display','block');
        $('#lblEmailError').text('Email field is required.');
        return false;
    }
    else if((userpassword == null ))
    {
        $('#signUpPassword').focus();
        $('#lblPasswordError').css('display','block');
        $('#lblPasswordError').text('Password field is required.');
        return false;
    }
    else if(userpassword.length < 5)
    {
      $('#signUpPassword').focus();
        $('#lblPasswordError').css('display','block');
        $('#lblPasswordError').text('Password Must be atleast 5 chars');
        return false;
    }
    else if((confirmuserpassword == null || confirmuserpassword.length == 0))
    {
        $('#signUpConfirmPassword').focus();
        $('#lblConfirmPasswordError').css('display','block');
        $('#lblConfirmPasswordError').text('ConfirmPassword field is required.');
        return false;
    }
    
   
   
}
 
function ValidateSignInRecord() {
    //alert("Hi");
    var userpassword,useremail;
    userpassword =useremail = null;
    userpassword = $('#signInPassword').val();
    useremail = $('#signInEmail').val();
   
    if((useremail == null || useremail.length == 0))
    {
        $('#signInEmail').focus();
        $('#lblError').css('display','block');
        $('#lblError').text('Email field is required.');
        return false;
    }
    else if((userpassword == null || userpassword.length == 0))
    {
        $('#signInPassword').focus();
        $('#lblError').css('display','block');
        $('#lblError').text('Password field is required.');
        return false;
    }
    
   
}
  
 
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
    .alert{
            padding: 5px;
            margin-bottom: 10px;
            border: 1px solid transparent;
            border-radius: 2px;
            font-size: 13px;
            width:150px;
          }

    .alert h4 {
            margin-top: 0;
            color: inherit;
          }


.alert-info {
  color: #3a87ad;
  background-color: #D3D3D3;
  border-color: #7FFF00;
  size:5px;
}

.alert-info hr {
  border-top-color: #a6e1ec;
}

.alert-info .alert-link {
  color: #C0C0C0;
}
    
    
    
    .co1
    {
      background-color:#06293F;
    }
    .co
    {
      background-color:#0B293C;
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
        ${flash.message}
        </div>
        </div>
      </li>
      <li class="col-md-4"></li>
   
      <li class="col-md-4 pull-right">
        <g:form action="signIn" method="post" controller="user" onsubmit="return ValidateSignInRecord();">
             <font color="white"><h3>SIGN IN</h3></font>
             <input id="signInEmail" type="text" placeholder="Email" class="form-control" name="email"><br>
             
             <input id="signInPassword" type="password" placeholder="Password" class="form-control"name="password">
            <a href="http://localhost:8080/trac/user/forgotPassword">Forgot Password?</a><br>
            <button type="submit" class="btn btn-default btn-primary "value="SIGN IN">SIGNIN</button> 
            <div id="lblError" class="alert alert-info" style="display:none"></div>
        </g:form>             
          
        
      <g:form action="addUser" controller="user" method="POST" onsubmit="return ValidateUserRecord();">
                <font color="white"><h3>SIGN UP</h3></font>
                <input type="text" id="signUpName" class="form-control" placeholder="Name*" name="name" autofocus><br>
        <div id="lblNameError" class="alert alert-info" style="display:none"></div>
        <input type="text" id="signUpEmail" class="form-control" placeholder="Email Address*" name="email" ><br>
        <div id="lblEmailError" class="alert alert-info" style="display:none"></div>
        <input type="password" id="signUpPassword" class="form-control "  placeholder="Password*" name="password"><br>
        <div id="lblPasswordError" class="alert alert-info" style="display:none"></div>
        <input type="password" id="signUpConfirmPassword" class="form-control "  placeholder="Confirm Password*" name="confirmPassword">
        <div id="lblConfirmPasswordError" class="alert alert-info" style="display:none"></div>
        <br>
                <button type="submit" class="btn btn-default btn-primary">SIGNUP</button>
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
