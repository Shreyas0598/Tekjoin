<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="org.tekjoin.dao.*,org.tekjoin.bean.*,org.tekjoin.servlet.*" import="java.util.*"%>
 <%UserDetails u = (UserDetails)request.getSession().getAttribute("user"); %>
<%DatabaseService db=new DatabaseService();
ProfileDetails pd=db.getProfileDetails(u.getUserId());
Vector<String> field=db.gettech(u.getUserId());
%>
<!DOCTYPE html>
<html>
<head>
  <title>Notice</title>
<link rel="stylesheet" href="css/nav.css">
<link rel="stylesheet" href="css/editprofile.css">

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
.myinfo{
background-color:white;
border-radius:12px;
height:650px;
}
form{
background-color:white;

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

<div class="myinfo" style="position:absolute;min-height:500px;height:auto"  ><br>
<img src="css/images/img4.jpg" alt="" height="200px" width="200px" style="border-radius:8px"><br><br>
<b><%=pd.getName().toUpperCase() %> </b> <p style="float:right;margin-right:20px;margin-top:-1px " ><%!String type = " ";%>
		<%String num = pd.getType();
		
		if(num.equals("1"))
			type = "Student";
		else if(num.equals("2"))
			type = "Academician";
		else if(num.equals("3"))
			type = "Entrepreneur";
		else
			type = "Executive";
			
			%>		<%=type %>	 </p><br>
<br>
  <b style="float:left;margin-left:10px;width:150px" >Contact Details :</b>
  <p style="text-align:right;margin-right:20px;margin-top:-1px " > <%=u.getEmail() %> <br>
  </p>
 <br>
<b style="float:left;margin-left:10px;width:150px">
Organization </b><p style="text-align:right;margin-right:20px;margin-top:-1px " > <%=pd.getJob() %> <br>
  </p><br>
<b style="float:left;margin-left:10px;width:150px">
Designation</b>
<p style="text-align:right;margin-right:20px;margin-top:-1px " > <%=pd.getDesign() %> <br>
  </p>
<br>
 <br>
<p >
<b style="float:left;margin-left:10px;width:150px">About Me: </b><br><p style="text-align:right;margin-right:20px;margin-top:-1px " > <%=pd.getAbout() %> <br>
  </p>
</p>
<br>
<b style="float:left;margin-left:10px;width:150px">
Total Selections></b> <br><p style="text-align:right;margin-right:20px;margin-top:-1px " > <%=field.size() %> <br>
  </p>
<p >
<b style="float:left;margin-left:10px;width:150px">MY INTERESTS : </b><br>
<ul style="height:70px ;overflow-y:scroll">
<%for(int i=0; i<field.size(); i++){ %>
<li style="float:left;margin-left:20px"><%=field.elementAt(i) %></li>
<%} %>
</ul>
</p>

<a href="notice.jsp" style="color:orange ;font-size:18px" > ADD / REMOVE NOTICE</a>
</div>


<form style="display:inline-block;margin-left:340px" action="profile" method="post" >
<br><br>  <label for="username"> <b> UserName </b> </label>
  <input type="text" name="name" value="" id="username" placeholder="Add Your UserName " >
<br><br><br>
<label for="designation"><b> Designation</b>  </label>
<input type="text" name="design" value="" id="designation" placeholder="Enter Your Designation " >
<br><br><br>
<label for="job"><b> Organization </b> </label>
<input type="text" name="org" value="" id="job" placeholder="Add Your Job " >
<br><br><br>
<label for="aboutme"> <b> Add Your bio</b>  </label>
<input type="textarea" name="about" value="" id="aboutme" placeholder="Add Your Own Content " >
<br><br><br>
<select class="s" name="type">
          <option value="1">Student</option>
          <option value="2">Academician</option>
          <option value="3">Entrepreneur</option>
          <option value="4">Executive</option>
        </select><br><br><br>
        <button type="submit" name="button">Submit Your Profile</button>
    </form>


<script>
function myFunction(x) {
  x.classList.toggle("change");
}
</script>

     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"><script type="text/javascript">
       </script>
       <script type="text/javascript">
       $(document).ready(function(){
    $(".select").hover(function(){
        $(this).find('h3').css({
    transform: 'scale(' + 1.2 + ')' , "top":"38%" ,"left":"40%",
}) ;
    });
    $(".select").mouseleave(function(){
        $(this).find('h3').css({
    transform: 'scale(' + 1 + ')',"top":"40%" ,"left":"40%",
}) ;
    });
    $('input[type="checkbox"]'). click(function(){
  if($(this). prop("checked") == true){
  $(this).parent().css({"background-color" :"black" ,"opacity":"1"}) ;
}
  else {
    $(this).parent().css({"background-color" :"white" ,"opacity":"1"}) ;
  }
});
});
       </script>
</body>
</html>
