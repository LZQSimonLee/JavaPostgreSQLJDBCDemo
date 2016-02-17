package com.simonlee.postgresqljdbc.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PostgreSQLJDBCBaseUtil {
	private static final String BUNDLE_NAME = "postgresqljdbc";

	public static String jdbcDriverClassName; // JDBC驱动名称
	public static String jdbcURL; // JDBC地址
	public static String jdbcUser; // JDBC登录名
	public static String jdbcPassword; // JDBC登录密码

	/**
	 * 初始化
	 * 
	 * @param flag
	 *            初始化标识，0：初始化，其他：不初始化
	 */
	public static void init(int flag) {
		if (flag != 0)
			return;

		try {
			System.out
					.println("[--------------------初始化参数--------------------]");

			ResourceBundle resourceBundle = ResourceBundle
					.getBundle(BUNDLE_NAME);

			try {
				jdbcDriverClassName = resourceBundle
						.getString("jdbcDriverClassName");
			} catch (MissingResourceException e) {
				System.err.println("[" + BUNDLE_NAME
						+ ".properties中不存在jdbcDriverClassName！]");
				e.printStackTrace();
			}
			try {
				jdbcURL = resourceBundle.getString("jdbcURL");
			} catch (MissingResourceException e) {
				System.err.println("[" + BUNDLE_NAME
						+ ".properties中不存在jdbcURL！]");
				e.printStackTrace();
			}
			try {
				jdbcUser = resourceBundle.getString("jdbcUser");
			} catch (MissingResourceException e) {
				System.err.println("[" + BUNDLE_NAME
						+ ".properties中不存在jdbcUser！]");
				e.printStackTrace();
			}
			try {
				jdbcPassword = resourceBundle.getString("jdbcPassword");
			} catch (MissingResourceException e) {
				System.err.println("[" + BUNDLE_NAME
						+ ".properties中不存在jdbcPassword！]");
				e.printStackTrace();
			}

			System.out
					.println("[--------------------初始化参数完成--------------------]");
		} catch (MissingResourceException e) {
			System.err.println("[" + BUNDLE_NAME + ".properties不存在！]");
			e.printStackTrace();
		}
	}
}