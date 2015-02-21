package com.baifeg.models.nav;

import java.util.ArrayList;
import java.util.List;

public class Nav extends Menu
{
	public static final String XML_NODE_NAME = "nav";
	public static final String DEFAULT = "default";

	public Nav()
	{
		this("");
	}

	public Nav(String name)
	{
		setName(name);
	}

	public List<Menu> getBreadcrumb(String url)
	{
		List<Menu> menuList = new ArrayList<Menu>();
		Menu foundMenu = getMenuByUrl(url);
		while (null != foundMenu && !(foundMenu instanceof Nav))
		{
			menuList.add(0, foundMenu);
			foundMenu = foundMenu.getParentMenu();
		}

		return menuList;
	}

	public boolean isDefault()
	{
		return DEFAULT.equals(getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.baifeg.mode.nav.Menu#setName(java.lang.String)
	 */
	@Override
	public void setName(String name)
	{
		super.setName(name);
	}

}
