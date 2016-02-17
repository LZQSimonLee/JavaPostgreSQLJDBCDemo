package com.simonlee.postgresqljdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simonlee.postgresqljdbc.util.PostgreSQLJDBCBaseUtil;

public class PostgreSQLJDBC {
	/**
	 * 查询mgbase.companyinfo
	 * 
	 * @param jdbcDriverClassName
	 *            JDBC驱动名称
	 * @param jdbcURL
	 *            JDBC地址
	 * @param jdbcUser
	 *            JDBC登录名
	 * @param jdbcPassword
	 *            JDBC登录密码
	 * @return 查询结果
	 */
	public static List<Map<String, Object>> selectFromMGBaseCompanyinfo(
			String jdbcDriverClassName, String jdbcURL, String jdbcUser,
			String jdbcPassword) {
		List<Map<String, Object>> rsltList = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(jdbcDriverClassName).newInstance(); // 加载JDBC驱动

			connection = DriverManager.getConnection(jdbcURL, jdbcUser,
					jdbcPassword); // 获取数据库连接

			String sql = "select * from mgbase.companyinfo where companyid = ? or companyname like ?";

			preparedStatement = connection.prepareStatement(sql); // 创建Statement对象（每一个Statement为一次数据库执行请求）

			// 设置传入参数
			preparedStatement.setLong(1, 10100000);
			preparedStatement.setString(2, "%蓝弧%");

			resultSet = preparedStatement.executeQuery(); // 执行SQL语句

			// 处理查询结果（将查询结果转换成List<Map>格式）
			rsltList = new ArrayList<Map<String, Object>>();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); ++i) {
					String columnName = resultSetMetaData.getColumnName(i + 1);
					map.put(columnName, resultSet.getString(columnName));
				}
				rsltList.add(map);
			}
		} catch (Exception e) {
			System.out.println("[查询mgbase.companyinfo异常！异常 = " + e + "]");
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rsltList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PostgreSQLJDBCBaseUtil.init(0);
		System.out.println(selectFromMGBaseCompanyinfo(
				PostgreSQLJDBCBaseUtil.jdbcDriverClassName,
				PostgreSQLJDBCBaseUtil.jdbcURL,
				PostgreSQLJDBCBaseUtil.jdbcUser,
				PostgreSQLJDBCBaseUtil.jdbcPassword));
	}
}