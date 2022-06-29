<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.tekjoin.dao.*,org.tekjoin.bean.*,org.tekjoin.servlet.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
  <title>Notice</title>
<link rel="stylesheet" href="css/nav.css">
<link rel="stylesheet" href="css/notice.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
$(document).ready(function(){
  $(".container").click(function(){
    $("#nav").slideToggle("slow");
  });
});
</script>
<style>

body{
        background-image: url("css/images/img2.jpg");
        opacity: 0.82;
        z-index: -1;
        /* Center and scale the image nicely */
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;

      }
.main{
background-color:white;
border-radius:12px;

}


</style>

</head>
<body>

<div class="container" onclick="myFunction(this)" >
  <div class="bar1"></div>
  <div class="bar2"></div>
  <div class="bar3"></div>
</div>
<div  id="nav" >
  <br>
<a href="profile.jsp" style="color:white;text-decoration:none" ><button type="button" name="button" >  Home</button></a><br><br>
<a href="Chat.xhtml" style="color:white;text-decoration:none" ><button type="button" name="button" >  Discussion Forum</button></a><br><br>
<a href="editprofile.jsp" style="color:white;text-decoration:none"><button type="button" name="button"> Edit Profile</button></a><br><br>
<a href="dashboard.jsp" style="color:white;text-decoration:none"><button type="button" name="button">Edit TechSpecs</button></a><br><br>
<a href="mypeople.jsp" style="color:white;text-decoration:none"><button type="button" name="button">My People</button></a><br><br>
<a href="logout.jsp" style="color:white;text-decoration:none"><button type="button" name="button">Log Out</button></a><br><br>
</div>

<div class="main" style=";display:flex">
<form class=""action="notice"  method="post">
  <textarea name="notice" rows="8" cols="80" placeholder="Add Your Notice Here"></textarea>
  <button type="submit" name="button">ADD NOTICE</button>
</form>

<%DatabaseService db=new DatabaseService();
	Vector<Notice> notice=db.getNotice();
%>
<div class="myfield">
<h1 style="text-align:left">PREVIOUS NOTICES</h1>
<%for(int i=0;i<notice.size();i++)
{%>
	<%=notice.elementAt(i).getNotice()%><br><br>

<%}%>
</div>
</div>


<script>
function myFunction(x) {
  x.classList.toggle("change");
}
</script>
</body>
</html>
