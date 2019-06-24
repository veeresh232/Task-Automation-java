package com.quinnox.dbauto.checker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quinnox.dbauto.connection.CreateConnection;
import com.quinnox.dbauto.constant.Constant;
import com.quinnox.dbauto.model.Query;

public class Functionality {
	private CreateConnection conObject;
	public List<Query> checkQuery(){
		conObject=new CreateConnection();
		Connection connection=conObject.createcon();
		PreparedStatement statement;
		List<Query> queries=new ArrayList<Query>();
		try {
			statement=connection.prepareStatement(Constant.queryToCheck);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				Query query=new Query();
				query.setSid(rs.getInt(2));
				query.setSerial(rs.getInt(3));
				query.setTime(rs.getLong(7));
				query.setSqlText(getSqlText(rs.getInt(2)));
				
				
				//System.out.println("SId "+rs.getInt(2));
				//System.out.println("Serial "+rs.getInt(3));
				//System.out.println("Waiting for "+ rs.getLong(7));
				queries.add(query);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return queries;
	}
	
	public String getSqlText(int sid) {
		conObject=new CreateConnection();
		Connection connection=conObject.createcon();
		String sqlText="";
		try {
			PreparedStatement statement=connection.prepareStatement(Constant.queryToSqltext);
			statement.setInt(1, sid);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString(1));
			 sqlText=sqlText+rs.getString("sql_text");
			}
			String sqlTextLinear= sqlText.replaceAll("\n", "");
			return sqlTextLinear;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
		
		
		
	}
	

}
