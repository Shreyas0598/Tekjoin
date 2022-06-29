<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style media="screen">
      body{
        background-image: url("css/images/img2.jpg");
        opacity: 0.82;
        z-index: -1;
        /* Center and scale the image nicely */
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;

      }
      .signup{
        border-radius: 12px;
        text-align: center;
        height: 300px;
        width: 800px;
        background-color:rgba(10,21,68,0.9);
        color: #ffffff;
        margin-top: 150px;
        padding: 30px;
        margin-left: 255px;
        display:flex;
      }
      .form{
        height: 300px;
        width: 500px;
        border-right: 1px solid white;
      }
      input{

        border: 0px;
        float: right;
        margin-left: 40px;
        margin-right: 40px;
        padding: 6px;
        border-radius: 20px;
        clear: both;
        font-size: 16px;
      }
      label{
      padding-top: 10px;
      clear: both;
      text-align: right;
      margin-right:15px;
      float: right;
      margin-bottom: 20px;
      display: inline-block;

         }
         input:focus{
           outline: none;

         }
         #sub{

           padding: 5px 0;
           width: 210px;
           margin-right: 55px;
           background-color: #FF7F00;
           cursor: pointer;
         }
         #div2{
           text-align: center;
           margin-left: 65px;
         }
         button{
           padding:8px 55px;
           margin: 10px 0;
           font-size: 12px;
           border-radius: 20px;
           border: 0px;
           cursor: pointer;
           color: WHITE;
           }
           button:focus{
             outline: none;
           }
           .switch {
             margin-top: 40px;
       position: absolute;
       display: inline-block;
       width: 25px;
       height: 2px;
       margin-left: 60px;
     }

    </style>
  </head>
  <body>

    <div class="signup">
      <div>
        <form class=" form" action="login"  method="post" >
          <label for="username"> UserName :<input type="text" name="username" value="" id="username" required></label><br><br>
          <label for="pass1"> Password :<input type="password" name="password" value="" id="pass1" required ></label><br><br>
          <input type="submit" name="Submit" value="Login" id="sub"><br><br><br>
                  </form>
                  <a href="#" style="float:right ;margin-top:-140px;margin-right:60px;">ForgetPassword</a>

      </div>
      <div id="div2">
          <h2>Quote</h2>
          <br>

          <q style="float:left"> Talent Win games ,
          but teamwork and intelligence
          wins championships</q>
              </div>
          </div>
  </body>
</html>
