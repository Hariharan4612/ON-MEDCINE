package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class register {
	String url="jdbc:mysql://localhost:3306/project1";
	String user="root";
	String pass="hari1234";
	
	Connection con;
	public register() throws SQLException {
		con=DriverManager.getConnection(url,user,pass);
	}
	public boolean managerlogin(String user,String pass) throws SQLException
	{
		boolean r=false;
		String q="select*from  managerlogin where mname=? and mpass=?";
		PreparedStatement p=con.prepareStatement(q);
		p.setString(1,user);
		p.setString(2,pass);
		ResultSet dd=p.executeQuery();
		while(dd.next())
		{
			r=true;
		}
		return r;
	}
	public boolean userlogin(String user,String pass) throws SQLException
	{
		boolean r=false;
		String q="select*from  userlogin where usernmae=? and upass=?";
		PreparedStatement p=con.prepareStatement(q);
		p.setString(1,user);
		p.setString(2,pass);
		ResultSet re=p.executeQuery();
		while(re.next())
		{
			r=true;
		}
		return r;
	}
	public void reg(String user,String pass) throws SQLException 
	{
		String q="insert into userlogin(usernmae,upass)values(?,?)";
		PreparedStatement p=con.prepareStatement(q);
		p.setString(1,user);
		p.setString(2,pass);
	    int re=p.executeUpdate();
	    if (re>=1)
	    {
	    	System.out.print("REGISTERED");
	    }
	    else
	    {
	    	System.out.print("NOT REGISTERED !");
	    }
	}
	public boolean medview(String diseasename) throws SQLException
	{
		boolean w=false;
		String q="select*from medcine where disease=?";
		PreparedStatement p=con.prepareStatement(q);
		p.setString(1,diseasename);
		ResultSet r=p.executeQuery();
		System.out.println("-----------------------------------------");
		while(r.next())
		{
			w=true;
			int tid=r.getInt(1);
			String medname=r.getString(2);
			String disease=r.getString(3);
			int price=r.getInt(4);
			System.out.println(tid+"\t"+medname+"\t"+disease+"\t"+price);
		}
		
		System.out.println("-----------------------------------------");
		return w;
	}
	public void medcineview() throws SQLException
	{
		String q="select*from medcine";
		PreparedStatement p=con.prepareStatement(q);
		ResultSet r=p.executeQuery();
		System.out.println("-----------------------------------------");
		while(r.next())
		{
			int tid=r.getInt(1);
			String medname=r.getString(2);
			String disease=r.getString(3);
			int price=r.getInt(4);
			System.out.println(tid+"\t"+medname+"\t"+disease+"\t"+price);
		}
		System.out.println("-----------------------------------------");
	}
	public boolean userdetail(String medname,String username) throws SQLException
	{
		boolean w=false;
		String q="insert into medicalrecords(medname,uname)values(?,?)";
		PreparedStatement p=con.prepareStatement(q);
		p.setString(1,medname);
		p.setString(2,username);
	    int re=p.executeUpdate();
	    if (re>=1)
	    {
	    	w=true;
	    	System.out.println("THANKYOU FOR PURCHASING");
	    }
		return w;
	}
	public void udetailview() throws SQLException
	{
		String q="select*from medicalrecords";
		PreparedStatement p=con.prepareStatement(q);
		ResultSet r=p.executeQuery();
		System.out.println("-----------------------------------------");
		while(r.next())
		{
		
			String medname=r.getString(1);
			String uname=r.getString(2);
			
			System.out.println(medname+"\t"+uname);
		}
		System.out.println("-----------------------------------------");
	}

  public void medupdate(int price,String medname) throws SQLException
  {
	  String q="update medcine set price=? where medname=?";
		PreparedStatement p=con.prepareStatement(q);
		p.setInt(1,price);
		p.setString(2,medname);
	    int re=p.executeUpdate();
	    if (re>=1)
	    {
	    	System.out.print("UPDATED");
	    }
	    else
	    {
	    	System.out.print("UPDATE PENDING !");
	    }
  }
  public void medremove(int tid) throws SQLException
  {
	  String q="delete from medcine  where tid=?";
		PreparedStatement p=con.prepareStatement(q);
		p.setInt(1,tid);
	    int re=p.executeUpdate();
	    if (re>=1)
	    {
	    	System.out.print("REMOVED");
	    }
	    else
	    {
	    	System.out.print("NOT REMOVED");
	    }
  }
  


	public static void main(String[] args) throws SQLException {
		register a=new register();
		Scanner b=new Scanner(System.in);
		System.out.println("---WELCOME TO ONLINE MEDICAL SHOP ---");
		System.out.println("1 - REGISTER");
		System.out.println("2 - SIGNUP");
		int c=b.nextInt ();
		if(c==1)
		{
			System.out.print("ENTER THE USERNAME");
			b.nextLine();
			String d=b.nextLine();
			System.out.print("ENTER THE PASSWORD");
			String e=b.nextLine();
			a.reg(d,e);
		}
		else if(c==2)
		{
			System.out.println("---WELCOME USER---");
			System.out.println("1 - USER LOGIN");
			System.out.println("2 - MANAGER LOGIN");
			int f=b.nextInt();
			if(f==1) 
			{
				System.out.print("ENTER THE USERNAME");
				b.nextLine();
				String un=b.nextLine();
				System.out.print("ENTER THE PASSWORD");
				String up=b.nextLine();
				boolean re=a.userlogin(un, up);
				if(re==true)
				{
				System.out.println("hello !"+un);
				System.out.println("ENTER YOUR PROBLEMS");
				System.out.println(" => FEVER");
				System.out.println(" => HEADACHE");
				System.out.println(" => ALERGY");
				System.out.println(" => COUGH");
				System.out.println(" => STOMACHUPSET");
				String dn=b.nextLine();
				boolean fe=a.medview(dn);
				if(fe==true)
				{
					System.out.println("PLEASE ENTER YOUR NAME");
					String una=b.nextLine();
					System.out.println("ENTER THE MEDCINE NAME");
					String mn=b.nextLine();
				   boolean ex= a.userdetail(mn, una);
				   if(ex==true)
				   {
						System.out.print("YOU HAVE BEEN REGISTERED");
				   }
				   else
				   {
					   System.out.print("YOU HAVE BEEN NOT REGISTRED");
				   }
				}
				else
				{
					System.out.println("SORRY MEDCINE NOT FOUND");
				}
					
				}
				else
				{
					System.out.println("INVALID USER");
				}
			}
			else if(f==2)
			{
				System.out.print("ENTER THE USERNAME");
				b.nextLine();
				String mn=b.nextLine();
				System.out.print("ENTER THE PASSWORD");
				String mp=b.nextLine();
				boolean fe=a.managerlogin(mn, mp);
				if(fe==true)
				{
				System.out.println(" HELLO !"+mn);
				System.out.println("1 PURCHASED DETAILS");
				System.out.println("2 MEDCINE UPDATE");
				System.out.println("3 MEDCINE REMOVE");
				System.out.println("4 MEDCINE VIEW");
				
				int op=b.nextInt ();
				if(op==1)
				{
					a.udetailview();
				}
				else if(op==2)
				{
					a.medcineview();
					System.out.println("MEDCINE PRICE");
					int ty=b.nextInt();
					b.nextLine();
					System.out.println("MEDCINE NAME");
					String tl=b.nextLine();
					a.medupdate(ty, tl);
				}
				else if(op==3)
				{
					System.out.println("ENTER MEDCINE ID");
					int tk=b.nextInt();
					a.medremove(tk);
					
				}
				else if(op==4)
				{
					a.medcineview();
				}
				else
				{
					System.out.print("INVALID OPTION");
				}
			
				}
				else
				{
					System.out.print("MANAGER NOT FOUND");
				}
				}
			
			else
			{
				System.out.print("---INVALID---");
			}
		}
		else
		{
			System.out.print("---INVALID---");
		}
		
		
        
	}

}
