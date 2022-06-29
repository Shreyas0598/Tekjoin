<%@ page import="java.util.*,org.tekjoin.dao.*,org.tekjoin.bean.*,org.tekjoin.servlet.*" %>
 <%UserDetails u = (UserDetails)request.getSession().getAttribute("user"); %>
<%DatabaseService db=new DatabaseService();
Vector<ProfileDetails> pd2=db.showPeopleProfile(u.getUserId());
ProfileDetails pd=db.getProfileDetails(u.getUserId());
Vector<String> field=db.gettech(u.getUserId());
Vector<String> display=db.dispmsg(u.getUserId()); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/dashboard.css">
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
		min-height:650px;		
      }
.myinfo{
background-color:white;
border-radius:12px;
min-height:650px;
}
.block{
background-color:rgba(10,21,68,0.9);
}
</style>

</head>

<body>
<%response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setHeader("Expires","0");
if(session.getAttribute("user")==null)
{
	System.out.println("user is null in mypeople");
	response.sendRedirect("log.jsp");
}
	%>
<div class="container" onclick="myFunction(this)"  >
  <div class="bar1"></div>
  <div class="bar2"></div>
  <div class="bar3"></div>
</div>
<div  id="nav">
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
<%
if(pd2!=null){
	
for(int i=0; i<pd2.size(); i++){%>
     
<div class="block" style="color:white;padding:10px" >
 <form   method="post" action="chatservlet" >
<p style="float:left ;width:200px" >
  <input type="text" name="b2" value="" style="display:none">
     	<%int id2 = pd2.elementAt(i).getUserId(); %> 
    	 Name :<%=pd2.elementAt(i).getName() %> 
		<br> <br><br>
		 Designation :<%=pd2.elementAt(i).getDesign() %><br>
  		 About Me :<%=pd2.elementAt(i).getAbout() %><br>
 		 Job :<%=pd2.elementAt(i).getJob() %><br>
  <input type="radio" name="b2" value=<%=id2 %>  style="display:none" checked  >
 </p>
<div style="height:150px;width:300px;background-color:white;float:right;color:black;overflow-y:scroll;margin-left:120px">
<%if(display != null) {%>
<%=display.elementAt(i)%>
<%}%>
</div>
<input type="textarea" name="msg" placeholder="Enter your message" style="font-size:16px;padding:4px;border-radius:6px;width:180px;border:0px;">
<button type="submit" name="button" style="border-radius:8px;background-color:orange;border:0;font-size:16px;padding:4px;" >Send </button>
<input type="radio" name="receiver" value="<%=pd2.elementAt(i).getUserId()%>" style="display:none" checked >
 </form>
     </div>
     <%}} %>
 <script>
function myFunction(x) {
  x.classList.toggle("change");
}
</script>
</body>
</html>
