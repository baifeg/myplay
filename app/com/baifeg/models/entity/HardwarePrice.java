package com.baifeg.models.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HardwarePrice")
public class HardwarePrice
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private long hardwareId;

	@Column(nullable = false)
	private int pricePerDay;

	@Column(nullable = false)
	private Timestamp createTime;

	@Column(nullable = false)
	private Timestamp startTime;

	@Column
	private Timestamp endTime;

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

	/**
	 * @return the pricePerDay
	 */
	public int getPricePerDay()
	{
		return pricePerDay;
	}

	/**
	 * @param pricePerDay
	 *            the pricePerDay to set
	 */
	public void setPricePerDay(int pricePerDay)
	{
		this.pricePerDay = pricePerDay;
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
	 * @return the startTime
	 */
	public Timestamp getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Timestamp startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Timestamp endTime)
	{
		this.endTime = endTime;
	}
}
