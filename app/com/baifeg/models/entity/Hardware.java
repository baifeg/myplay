package com.baifeg.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Hardware")
public class Hardware
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String name;

	@Column
	private int cpu;

	@Column
	private int memory;

	@Column
	private int disk;

	@Column
	private int bandwidth;

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
	 * @return the cpu
	 */
	public int getCpu()
	{
		return cpu;
	}

	/**
	 * @param cpu
	 *            the cpu to set
	 */
	public void setCpu(int cpu)
	{
		this.cpu = cpu;
	}

	/**
	 * @return the memory
	 */
	public int getMemory()
	{
		return memory;
	}

	/**
	 * @param memory
	 *            the memory to set
	 */
	public void setMemory(int memory)
	{
		this.memory = memory;
	}

	/**
	 * @return the disk
	 */
	public int getDisk()
	{
		return disk;
	}

	/**
	 * @param disk
	 *            the disk to set
	 */
	public void setDisk(int disk)
	{
		this.disk = disk;
	}

	/**
	 * @return the bandwidth
	 */
	public int getBandwidth()
	{
		return bandwidth;
	}

	/**
	 * @param bandwidth
	 *            the bandwidth to set
	 */
	public void setBandwidth(int bandwidth)
	{
		this.bandwidth = bandwidth;
	}
}
