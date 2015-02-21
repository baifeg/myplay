package com.baifeg.models.nav;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import play.mvc.Http;
import play.mvc.Http.Request;

public class Menu
{
	private String name;
	private String url;
	private String cssClass;
	private String refer;
	private List<Menu> subMenus;
	private Menu parentMenu;

	public static final String XML_NODE_NAME = "menu";

	public Menu()
	{
		cssClass = "";
		subMenus = new ArrayList<Menu>();
	}

	public Menu getMenuByName(String name)
	{
		return getMenuByName(getSubMenus(), name);
	}

	private Menu getMenuByName(List<Menu> menus, String name)
	{
		for (Menu menu : menus)
		{
			if (name.equals(menu.getName()))
				return menu;

			Menu foundMenu = getMenuByName(menu.getSubMenus(), name);
			if (null != foundMenu)
				return foundMenu;
		}

		return null;
	}

	public Menu getMenuByUrl(String url)
	{
		if (url.equalsIgnoreCase(getUrl()))
			return this;
		return getMenuByUrl(getSubMenus(), url);
	}

	private Menu getMenuByUrl(List<Menu> menus, String url)
	{
		for (Menu menu : menus)
		{
			if (!menu.isAvailable())
				continue;
			if (url.equalsIgnoreCase(menu.getUrl()))
				return menu;

			Menu foundMenu = getMenuByUrl(menu.getSubMenus(), url);
			if (null != foundMenu)
				return foundMenu;
		}

		return null;
	}

	public boolean isActive()
	{
		Request request = Http.Context.current().request();
		return isActive(request.path());
	}

	public boolean isActive(String currentUrl)
	{
		if (currentUrl == null || "".equals(currentUrl))
			return false;
		if (!currentUrl.startsWith(currentUrl))
			return false;

		if (currentUrl.contains("?"))
			currentUrl = currentUrl.split("\\?")[0];

		if ("/".equals(getUrl()))
		{
			List<Menu> menus = getParentMenu().getSubMenus();
			for (Menu menu : menus)
			{
				if (menu == this)
					continue;
				if (menu.isActive(currentUrl))
					return false;
			}

			return true;
		}

		Menu foundMenu = getMenuByUrl(currentUrl);
		if (null != foundMenu && !(foundMenu instanceof Nav))
		{
			return true;
		}

		return false;
	}

	public boolean hasSubMenu()
	{
		return !subMenus.isEmpty();
	}

	public boolean isAvailable()
	{
		if (null != name && !"".equals(name))
			return true;

		if (null != refer && !"".equals(refer))
			return true;

		return false;
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
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass()
	{
		return cssClass;
	}

	/**
	 * @param cssClass
	 *            the cssClass to set
	 */
	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}

	/**
	 * @return the refer
	 */
	public String getRefer()
	{
		return refer;
	}

	/**
	 * @param refer
	 *            the refer to set
	 */
	public void setRefer(String refer)
	{
		this.refer = refer;
	}

	/**
	 * @return the subMenus
	 */
	public List<Menu> getSubMenus()
	{
		return subMenus;
	}

	/**
	 * @param subMenus
	 *            the subMenus to set
	 */
	public void addSubMenus(List<Menu> subMenus)
	{
		for (Menu menu : subMenus)
		{
			addSubMenu(menu);
		}
	}

	public void addSubMenu(Menu subMenu)
	{
		if (null == subMenu || !subMenu.isAvailable())
			return;

		if (!subMenus.contains(subMenu))
		{
			subMenus.add(subMenu);
			subMenu.setParentMenu(this);
		}
	}

	public Menu getParentMenu()
	{
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu)
	{
		this.parentMenu = parentMenu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String[] subMenuName = new String[subMenus.size()];
		for (int i = 0; i < subMenuName.length; i++)
		{
			subMenuName[i] = subMenus.get(i).getName();
		}
		return "Menu [name=" + name + ", url=" + url + ", cssClass=" + cssClass
				+ ", refer=" + refer + ", subMenus="
				+ Arrays.toString(subMenuName)
				+ ", parentMenu=" + parentMenu + "]";
	}
}
