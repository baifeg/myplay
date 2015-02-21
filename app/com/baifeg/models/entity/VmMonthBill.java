package com.baifeg.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VmMonthBill")
public class VmMonthBill
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private long vmId;

	@Column(nullable = false)
	private int year;

	@Column(nullable = false)
	private int month;

	@Column(nullable = false)
	private int useDays;

	@Column(nullable = false)
	private int charge;

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
	 * @return the vmId
	 */
	public long getVmId()
	{
		return vmId;
	}

	/**
	 * @param vmId
	 *            the vmId to set
	 */
	public void setVmId(long vmId)
	{
		this.vmId = vmId;
	}

	/**
	 * @return the year
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}

	/**
	 * @return the useDays
	 */
	public int getUseDays()
	{
		return useDays;
	}

	/**
	 * @param useDays
	 *            the useDays to set
	 */
	public void setUseDays(int useDays)
	{
		this.useDays = useDays;
	}

	/**
	 * @return the charge
	 */
	public int getCharge()
	{
		return charge;
	}

	/**
	 * @param charge
	 *            the charge to set
	 */
	public void setCharge(int charge)
	{
		this.charge = charge;
	}
}
