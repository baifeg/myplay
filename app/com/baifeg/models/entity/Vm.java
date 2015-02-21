package com.baifeg.models.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vm")
public class Vm
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private long userId;
	
	@Column(nullable= false)
	private Timestamp createTime;
	
	// 1: running, 2: destroyed
	@Column(nullable = false)
	private int status;

	@Column(nullable = false)
	private long hardwareId;

	/**
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the userId
	 */
	public long getUserId()
	{
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime()
	{
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Timestamp createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * @return the status
	 */
	public int getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status)
	{
		this.status = status;
	}

	/**
	 * @return the hardwareId
	 */
	public long getHardwareId()
	{
		return hardwareId;
	}

	/**
	 * @param hardwareId
	 *            the hardwareId to set
	 */
	public void setHardwareId(long hardwareId)
	{
		this.hardwareId = hardwareId;
	}
}
