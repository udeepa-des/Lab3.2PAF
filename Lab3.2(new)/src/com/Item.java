package com;

import java.sql.*;

public class Item {

	public Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test",
					"root", "");
			//For testing
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem (String code,String name,String price,String desc) {

		String output = "";

		try {
			Connection con = connect();

			if(con == null) {
				return "error while connecting to the database";
			}

			String query = "insert into item ('itemID,'itemCode','itemName','itemPrice','itemDesc')" + "values (?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,code);
			preparedStmt.setString(3,name);
			preparedStmt.setDouble(4,Double.parseDouble(price));
			preparedStmt.setString(5,desc);

			preparedStmt.execute();
			con.close();

			output = "inserted successfully";

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return output;

	}

	public String readItems() {

		String output = "";

		try {
			Connection con = connect();

			if(con == null) {
				return "Error while connecting to the database for reading.";
			}

			////prepare the html table to be displayed

			output = "<table border = '1'> <tr><th>Item Code </th> "
					+ "<th>Item Name</th> <th>Item Price</th>"
					+"<th>Item Description</th>"
					+ "<th>Update</th> <th>Remove</th> </tr> ";

			String query = "select * from item";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			//iterating through the rows in the result set

			while (rs.next())
			{
				String itemId = Integer.toString(rs.getInt("itemID"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String itemPrice= rs.getString("itemPrice");	
				String itemDesc = rs.getString("itemDesc");

				//add a row into html table

				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' "
						+ " type='button' value='Update'></td>"
						+ "<td><form method='post' action='itemsps.jsp'>"
						+ "<input name='btnRemove' "
						+ " type='submit' value='Remove'>"
						+ "<input name='itemID' type='hidden' "
						+ " value='" + itemId + "'>" + "</form></td></tr>";
			}

			con.close();

			//complete the html table
			output +="</table>";



		} catch (Exception e) {

			output= "error while reading the items";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
