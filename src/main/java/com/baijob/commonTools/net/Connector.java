package com.baijob.commonTools.net;

/**
 * 连接者对象，提供一些连接的基本信息
 * @author Luxiaolei
 *
 */
public class Connector {
	private String host;
	private int port;
	private String user;
	private String password;
	private String group;
	
	public Connector() {
	}
	
	public Connector(String user, String password, String group) {
		this.user = user;
		this.password = password;
		this.group = group;
	}

	public Connector(String host, int port, String user, String password) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	/**
	 * 获得主机名
	 * @return
	 */
	public String getHost() {
		return host;
	}
	/**
	 * 设定主机名
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * 获得端口号
	 * @return
	 */
	public int getPort() {
		return port;
	}
	/**
	 * 设定端口号
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * 获得用户名
	 * @return
	 */
	public String getUser() {
		return user;
	}
	/**
	 * 设定用户名
	 * @param name
	 */
	public void setUser(String name) {
		this.user = name;
	}
	
	/**
	 * 获得密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设定密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 获得用户组名
	 * @return
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * 设定用户组名
	 * @param group
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * ToString方法仅用于测试显示
	 */
	@Override
	public String toString() {
		return "Connector [host=" + host + ", port=" + port + ", user=" + user + ", password=" + password + "]";
	}
}
