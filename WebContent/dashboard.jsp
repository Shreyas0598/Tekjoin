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
<link rel="stylesheet" href="css/technical.css">

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

<form style="display:inline-block;margin-left:340px" class="action" action="techservlet" method="post">
    <div class="select">
       <input type="checkbox" name="card1" value="1" id="card1" >
  <label for="card1"><img src="css/images/img1.jpg" alt=""> <h3>Algorithms</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="2" id="card2" >
  <label for="card2"><img src="css/images/img1.jpg" alt=""> <h3>Android</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="3" id="card3" >
  <label for="card3"><img src="css/images/img1.jpg" alt=""> <h3>AI</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="4" id="card4" >
  <label for="card4"><img src="css/images/img1.jpg" alt=""> <h3>Big Data</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="5" id="card5" >
  <label for="card5"><img src="css/images/img1.jpg" alt=""> <h3>Blockchain</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="6" id="card6" >
  <label for="card6"><img src="css/images/img1.jpg" alt=""> <h3>Cloud</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="7" id="card7" >
  <label for="card7"><img src="css/images/img1.jpg" alt=""> <h3>Graphics</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="8" id="card8" >
  <label for="card8"><img src="css/images/img1.jpg" alt=""> <h3>Networks</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="9" id="card9" >
  <label for="card9"><img src="css/images/img1.jpg" alt=""> <h3>Computing Languages</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="10" id="card10" >
  <label for="card10"><img src="css/images/img1.jpg" alt=""> <h3>Cryptography</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="11" id="card11" >
  <label for="card11"><img src="css/images/img1.jpg" alt=""> <h3>Data Mining</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="12" id="card12" >
  <label for="card12"><img src="css/images/img1.jpg" alt=""> <h3>NLP</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="13" id="card13" >
  <label for="card13"><img src="css/images/img1.jpg" alt=""> <h3>HCI</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="14" id="card14" >
  <label for="card14"><img src="css/images/img1.jpg" alt=""> <h3>Ethical Hacking</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="15" id="card15" >
  <label for="card15"><img src="css/images/img1.jpg" alt=""> <h3>IOT</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="16" id="card16" >
  <label for="card16"><img src="css/images/img1.jpg" alt=""> <h3>Robotics</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="17" id="card17" >
  <label for="card17"><img src="css/images/img1.jpg" alt=""> <h3>Gaming</h3> <br> </label>
    </div>
    <div class="select">
       <input type="checkbox" name="card1" value="18" id="card18" >
  <label for="card18"><img src="css/images/img1.jpg" alt=""> <h3>Web</h3> <br> </label>
</div> <br>
    <button type="submit" name="button"  >Add Fields</button>
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
