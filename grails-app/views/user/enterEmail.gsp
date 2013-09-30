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


    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <script type="text/javascript">
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
      .co1
      {
        background-color:#06293F;
      }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head>
  <body>
    <h1>Enter Your Email and We Will Send a Link to reset the Password</h1>
  <g:form action="reset" controller="user">
    <input type="email" name="email" placeholder="Email Address*">
    <button type="submit">Reset</button>
  </g:form>
  <div class="flash co1">
${flash.message}
  </div>
</body>
</html>
