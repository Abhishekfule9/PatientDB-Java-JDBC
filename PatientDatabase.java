package com.projects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PatientDatabase 
{
	String driver = "oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";
	String dbUName="system";
	String dbPwd="nareshit";
	
	Scanner  Sc  = new Scanner (System.in);
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			 con = DriverManager.getConnection(dbUrl, dbUName, dbPwd);
			 System.out.println("Connection Created !! ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return con;
		
	}
	
	void patient_operation()
	{
		System.out.println("Implimenting PreparedStatement\n");
		
		try
		{
			Connection con = connect();
		    PreparedStatement pstm1 = con.prepareStatement("insert into patient values (?,?,?,?)");
		   PreparedStatement pstm2 =  con.prepareStatement("select * from patient");
		   PreparedStatement pstm3= con.prepareStatement("select * from patient where pid= ? ");
		   PreparedStatement pstm4 = con.prepareStatement(" update patient  set age = ? where pid =?");
		    PreparedStatement pstm5 = con.prepareStatement(" Delete patient where pid=?");
		
			while(true)
			{
				System.out.println("\nWelcom to Patient Database");
				System.out.println("1) Add patient data\n2) View patient data\n3) Retrivive patient Data\n4) Update patient data\n5) Delete patient data\n6) Exit");
				System.out.println("Please enter your Choice :: ");
				int choice =Integer.parseInt(Sc.nextLine());
				switch(choice)
				{
				case 1:
					System.out.println("Welcome to reception\n : ");
					System.out.println("Enter patient id : ");
					String pat_id = Sc.nextLine();
					
					System.out.println("Enter patient Name : ");
					String pat_name = Sc.nextLine();
					
					System.out.println("Enter patient Age : ");
					int pat_age = Integer.parseInt(Sc.nextLine());
					
					System.out.println("Enter patient contact Number : ");
					long pat_contact = Long.parseLong(Sc.nextLine());
					
					pstm1.setString(1, pat_id);
					pstm1.setString(2, pat_name);
					pstm1.setInt(3, pat_age);
					pstm1.setLong(4, pat_contact);
					
					int rowCount =pstm1.executeUpdate();
					if (rowCount>0)
					{
						System.out.println("Data is updated !! ");
					}
					else
					{
						System.out.println("Data is not updated ");
					}
					break;
				case 2:
					System.out.println("\nWelcome to patient data ! ");
					ResultSet rs1 = pstm2.executeQuery();
					while(rs1.next())
					{
						System.out.println(rs1.getString(1) + " "+ rs1.getString(2) + " " +rs1.getInt(3) + " " +rs1.getLong(4));
					}
					System.out.println("Data is retrived ");
					break;
				default :System.out.println("You Entered invalid input ! ");
				}
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
			
public static void main(String[] args) 
{
	PatientDatabase obj = new PatientDatabase();
	obj.patient_operation();
	
	
}

}
