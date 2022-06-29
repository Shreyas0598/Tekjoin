package org.tekjoin.dao;
import org.tekjoin.bean.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
public class DatabaseService {
	public void insertSignup(UserDetails u) throws SQLException
	{
		Connection con = DBManager.getCon();
		String username = u.getName();
		String email = u.getEmail();
		String password = u.getPassword();
		String sql1 = "insert into login(username,password,email) values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql1);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.executeUpdate();
      	String sql2="select userId from login where username = '"+username+"'";
      	Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql2);
		int id=0;
		if(rs.next())
		{
			id=rs.getInt("userId");
			System.out.println("ID IN DS.JAVA "+id);
			u.setUserId(id);
			System.out.println("ID OF USER OBJ "+u.getUserId());
			String sql3 = "insert into tech(userId) values(?)";
			String sql4 = "insert into profile(userId) values(?)";
			PreparedStatement pstmt3 = con.prepareStatement(sql3);
			PreparedStatement pstmt4 = con.prepareStatement(sql4);
			pstmt3.setInt(1,id);
			pstmt3.executeUpdate();
			pstmt4.setInt(1,id);
			pstmt4.executeUpdate();
		}
		con.close();
	}
	public UserDetails loginAuthenticate(UserDetails u) throws SQLException
	{
		Connection con = DBManager.getCon();
		Statement stmt = con.createStatement();
		String uname = u.getName();
		String up = u.getPassword();
		System.out.println("In DataBase Service");
		System.out.println(uname+" "+up);
		String sql = "SELECT * FROM login where username='"+uname+"' and password='"+up+"'";
		ResultSet rs = stmt.executeQuery(sql);
		String email = " ";
		int uid = 0;
		if(rs.next())
		{
			email = rs.getString("email");
			u.setEmail(email);
			uid = rs.getInt("userId");
			u.setUserId(uid);
			con.close();
			return u;
		}
		con.close();
		return null;
	}
	public TechDetails getTechDetails(int userId) throws SQLException
	{
		TechDetails td = new TechDetails();
		Connection con = DBManager.getCon();
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM tech where userId='"+userId+"'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			td.setUserId(userId);
			td.setFields(rs.getString("fields"));
			td.setLength(rs.getInt("length"));
			con.close();
			return td;
		}
		con.close();
		return null;
	
	}
	public ProfileDetails getProfileDetails(int userId) throws SQLException
	{
		ProfileDetails pd = new ProfileDetails();
		Connection con = DBManager.getCon();
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM profile where userId='"+userId+"'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			pd.setUserId(userId);
			pd.setAbout(rs.getString("about"));
			pd.setDesign(rs.getString("design"));
			pd.setJob(rs.getString("job"));
			pd.setName(rs.getString("name"));
			pd.setType(rs.getString("type"));
			con.close();
			return pd;
		}
		con.close();
		return null;
	
	}
	
	
	public TechDetails InsertTechFields(TechDetails td) throws SQLException
	{
		Connection con = DBManager.getCon();
		String fields = td.getFields();
		int length = td.getLength();
		int userId = td.getUserId();
		System.out.println(userId);
		String s = "update tech set fields='"+fields+"', length='"+length+"' where userId='"+userId+"'";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(s);
		return td;
	}
	public ProfileDetails InsertProfileFields(ProfileDetails pd)throws SQLException
	{
		Connection con=DBManager.getCon();
		int userId=pd.getUserId();
		String name=pd.getName();
		String type=pd.getType();
		String design=pd.getDesign();
		String job=pd.getJob();
		String about=pd.getAbout();
		System.out.println("IN DBS UPDATE PRO");
		System.out.println(userId+" "+name+" "+type+" "+design+" "+job+" "+about+"");
		String s="update profile set name='"+name+"',type='"+type+"',design='"+design+"',job='"+job+"',about='"+about+"' where userId= '"+userId+"'";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(s);
		return pd;
	}
	public void MakeMatch(TechDetails td,int val) throws SQLException
	{
		Connection con = DBManager.getCon();
		int uid1 = td.getUserId();
		String fields1 = td.getFields();
		int length1 = td.getLength();
		String query = "select * from tech where userId<>'"+uid1+"'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next())
		{
			String fields2 = rs.getString("fields");
			int uid2 = rs.getInt("userId");
			int length2 = rs.getInt("length");
			if(val==1)
			{
				Statement stmt3=con.createStatement();
		        String del = "delete from matches where userId1='"+uid1+"' and userId2='"+uid2+"' or userId1='"+uid2+"' and userId2='"+uid1+"'";
				stmt3.executeUpdate(del);
			}
			String clash1="select * from mypeople where userId1='"+uid1+"'and userId2='"+uid2+"' or userId1='"+uid2+"' and userId2='"+uid1+"'";
			Statement stmt1=con.createStatement();
			ResultSet rs1=stmt1.executeQuery(clash1);
			String clash2="select * from matches where userId1='"+uid1+"'and userId2='"+uid2+"' or userId1='"+uid2+"' and userId2='"+uid1+"'";
			Statement stmt2=con.createStatement();
			ResultSet rs2=stmt2.executeQuery(clash2);
			System.out.println("Clash 2 ka "+uid1+" and dusra "+uid2);
			 if((!rs1.next())&&(!rs2.next()))
				{
					StringBuilder common=new StringBuilder("000000000000000000");	
					int count=0;
					for(int i=0;i<18;i++)
					{
						if(fields1.charAt(i)=='1'&& fields2.charAt(i)=='1')
						{
							common.setCharAt(i,'1');
							count++;			
						}
					}
					System.out.println("The calculated users are "+uid1+" and "+uid2);
					System.out.print("The Matched Attributes Are "+ common);
					System.out.println("\nThe Number Of Common Attributes Is "+count);
					double permatch = (count)/(double)Math.max(length1,length2)*100;
					System.out.println("The Percentage Match Is "+permatch);
					if((int)permatch >= 40)
					{
						String sql1 = "insert into matches(userId1,userId2,common) values(?,?,?)";
						PreparedStatement pstmt = con.prepareStatement(sql1);
				        pstmt.setInt(1, uid1);
				        pstmt.setInt(2, uid2);
				        pstmt.setString(3, common.toString());
				        pstmt.executeUpdate();
				      	
					}
				}
			}
	}
	public Vector<TechnicalInterest> showMatch(UserDetails u) throws SQLException
	{
		Connection con = DBManager.getCon();
		int id1 = u.getUserId();
		String q1="select * from matches where userId1 = '"+id1+"'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(q1);
		Statement stmt2 = con.createStatement();
		Vector<TechnicalInterest> v=new Vector<TechnicalInterest>();
		while(rs.next())
		{
			TechnicalInterest ti=new TechnicalInterest();
			int id2=rs.getInt("userId2");
			String q3="select * from profile where userId = '"+id2+"'";
			ResultSet rs2 = stmt2.executeQuery(q3);
			while(rs2.next())
			{
				String about = rs2.getString("about");
				String name = rs2.getString("name");
				String common=rs.getString("common");
				int count = rs.getInt("count");
				Vector<String> v2=new Vector<String>();
				for(int i=0;i<18;i++)
				{
					char ch=common.charAt(i);
					if(ch=='1')
					{
						String temp=Character.valueOf((char) (i+1))+"";
						String s=ti.getTech(temp);
						v2.add(s);
					}
				}
				ti.setUserId(id2);
				ti.setAbout(about);
				ti.setName(name);
				ti.setTechdetails(v2);
				ti.setCount(count);
				v.add(ti);
			}
		}
		String q2="select * from matches where userId2 = '"+id1+"'";
		rs = stmt.executeQuery(q2);
		while(rs.next())
		{
			TechnicalInterest ti=new TechnicalInterest();
			int id2=rs.getInt("userId1");
			String q3="select * from profile where userId = '"+id2+"'";
			ResultSet rs2 = stmt2.executeQuery(q3);
			while(rs2.next())
			{
				String about = rs2.getString("about");
				String name = rs2.getString("name");
				String common=rs.getString("common");
				Vector<String> v2=new Vector<String>();
				int count = rs.getInt("count");
				for(int i=0;i<18;i++)
				{
					char ch=common.charAt(i);
					if(ch=='1')
					{
						String temp=Character.valueOf((char) (i+1))+"";
						String s=ti.getTech(temp);
						v2.add(s);
					}
				}
				ti.setUserId(id2);
				ti.setAbout(about);
				ti.setName(name);
				ti.setTechdetails(v2);
				ti.setCount(count);
				v.add(ti);
			}
		}
		return v;
	}
	
	public void AcceptRequest(int count, int uid1, int uid2) throws SQLException
	{
		Connection con = DBManager.getCon();
		Statement stmt = con.createStatement();
		if(count == 1)
		{
			String sql1 = "update matches set count=1 where userId1='"+uid1+"' and userId2='"+uid2+"'";
			String sql2 = "update matches set count=1 where userId1='"+uid2+"' and userId2='"+uid1+"'";
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
		}
		else
		{
			String sql1 = "insert into mypeople(userId1,userId2) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql1);
	        pstmt.setInt(1, uid1);
	        pstmt.setInt(2, uid2);
	        pstmt.executeUpdate();
	        String del = "delete from matches where userId1='"+uid1+"' and userId2='"+uid2+"' or userId1='"+uid2+"' and userId2='"+uid1+"'";
			stmt.executeUpdate(del);
		}
	}
	
	public void InsertNotice(Notice n) throws SQLException {
		String notice = n.getNotice();
		int userId = n.getUserId();
		Connection con = DBManager.getCon();
		String sql1 = "insert into notice(userId,note) values(?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql1);
        pstmt.setInt(1, userId);
        pstmt.setString(2, notice);
        pstmt.executeUpdate();	
	}
	public Vector<Notice> getNotice() throws SQLException
	{
		Vector<Notice> vec=new Vector<Notice>();
		Connection con = DBManager.getCon();
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM notice";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			Notice note=new Notice();
			note.setUserId(rs.getInt("userId"));
			note.setNotice(rs.getString("note"));
			note.setNoticeId(rs.getInt("noticeId"));
			vec.add(note);
		}
		for(int i=0;i<vec.size();i++)
		{
			System.out.println(vec.elementAt(i).getNotice());
		}
		con.close();
		return vec;
	}
	public Vector<ProfileDetails> showPeopleProfile(int id) throws SQLException 
	{
		Connection con=DBManager.getCon();
		Statement stmt=con.createStatement();
		String sql1="select userId1 from mypeople where userId2= '"+id+"'";
		String sql2="select userId2 from mypeople where userId1= '"+id+"'";
		ProfileDetails pd=new ProfileDetails();
		Vector<ProfileDetails> vec=new Vector<ProfileDetails>();
		ResultSet rs=stmt.executeQuery(sql1);
		while(rs.next())
		{
			int id2=rs.getInt("userId1");
			pd=getProfileDetails(id2);
			vec.add(pd);
		}
		ResultSet rs2=stmt.executeQuery(sql2);
		while(rs2.next())
		{
			int id2=rs2.getInt("userId2");
			pd=getProfileDetails(id2);
			vec.add(pd);
		}
		return vec;
	}
	public Vector<String> dispmsg(int uid1) throws SQLException
	{
		Connection con=DBManager.getCon();
		String sql="select * from mypeople where userId1 = '"+uid1+"' or userId2= '"+uid1+"'";
		Statement stmt2=con.createStatement();
		ResultSet rs = stmt2.executeQuery(sql);
		Vector<String> msglist=new Vector<String>();
		while(rs.next())
		{
			msglist.add(rs.getString("message"));			
		}
		return msglist;
	}
	public void chat(int uid1, int uid2, String msg,String name) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=DBManager.getCon();
		String sql="select * from mypeople where userId1 = '"+uid1+"' and userId2= '"+uid2+"'";
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		String val_new="";
		if(rs.next())
		{
			String val_old=rs.getString("message");
			val_new=val_old+"\n"+name+":"+msg;
		}
		String sql3="select * from mypeople where userId1 = '"+uid2+"' and userId2= '"+uid1+"'";
		ResultSet rs2=stmt.executeQuery(sql3);
		if(rs2.next())
		{
			String val_old=rs2.getString("message");
			val_new=val_old+"\n"+name+":"+msg;
		}
		String sql2="update mypeople set message= '"+val_new+"' where userId1 = '"+uid1+"' and userId2= '"+uid2+"' or userId1 = '"+uid2+"' and userId2 = '"+uid1+"'";
		stmt.executeUpdate(sql2);
	}
	public Vector<String> gettech(int uid) throws SQLException
	{
		Connection con=DBManager.getCon();
		String sql="select * from tech where userId = '"+uid+"'";
		Statement stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String fields="";
		if(rs.next())
		{
			fields=rs.getString("fields");
		}
		System.out.println("IN GET TECH FUNC "+fields);
		Vector<String> vec=new Vector<String>();
		HashMap<Integer,String> map=(new Hash()).getHash();
		for(int i=0;i<18;i++)
		{
			if(fields.charAt(i)=='1')
			{
				vec.add(map.get(i+1));
			}
		}
		return vec;		
	}
}