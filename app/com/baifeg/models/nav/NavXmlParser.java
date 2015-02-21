package com.baifeg.models.nav;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NavXmlParser implements NavParser
{
	private static final String NAV_XML_REGEX = "nav-[0-9].xml";

	@Override
	public Nav parse(String navDir) throws ParseException
	{
		File dir = new File(navDir);
		if (!dir.exists() || dir.isFile())
			throw new ParseException("Directory [" + navDir
					+ "] does not exist.");
		
		File[] listFiles = dir.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				if (name.matches(NAV_XML_REGEX))
					return true;
				return false;
			}
		});

		return parse(Arrays.asList(listFiles));
	}

	@Override
	public Nav parse(List<File> navConfList) throws ParseException
	{
		Nav nav = new Nav(Nav.DEFAULT);
		for (File file : navConfList)
		{
			parse(file, nav);
		}

		return nav;
	}

	private void parse(File file, Nav defaultNav)
	{
		Nav nav = parse(file);
		if (null == nav || !nav.hasSubMenu())
			return;

		if (nav.isDefault())
			defaultNav.addSubMenus(nav.getSubMenus());
		else
			mergeNav(nav, defaultNav);
	}

	private void mergeNav(Nav nav, Nav defaultNav)
	{
		for (Menu menu : nav.getSubMenus())
		{
			String refer = menu.getRefer();
			if (null == refer || "".equals(refer))
				continue;

			mergeMenu(menu, defaultNav);
		}

	}

	private void mergeMenu(Menu menu, Nav defaultNav)
	{
		Menu menuByName = defaultNav.getMenuByName(menu.getRefer());
		if (null != menuByName)
			menuByName.addSubMenus(menu.getSubMenus());
	}

	@Override
	public Nav parse(File navConf) throws ParseException
	{
		Document document = XmlHelper.getDocument(navConf);
		if (null == document || !document.hasChildNodes())
			return null;

		NodeList navNodes = document.getChildNodes();
		Node navNode = navNodes.item(0);

		if (!Nav.XML_NODE_NAME.equals(navNode.getNodeName()))
			return null;
		return parseNav(navNode);
	}

	private Nav parseNav(Node navNode)
	{
		Nav nav = XmlHelper.newInstance(Nav.class, navNode);

		if (!navNode.hasChildNodes())
			return nav;

		NodeList menuNodes = navNode.getChildNodes();
		nav.addSubMenus(parseMenus(menuNodes));

		System.out.println("=====> " + nav);
		return nav;
	}

	private List<Menu> parseMenus(NodeList menuNodes)
	{
		List<Menu> menuList = new ArrayList<Menu>();
		for (int i = 0; i < menuNodes.getLength(); i++)
		{
			Node menuNode = menuNodes.item(i);
			if (!Menu.XML_NODE_NAME.equals(menuNode.getNodeName()))
				continue;
			Menu menu = XmlHelper.newInstance(Menu.class, menuNode);
			menuList.add(menu);

			if (!menuNode.hasChildNodes())
				continue;

			menu.addSubMenus(parseMenus(menuNode.getChildNodes()));
		}

		return menuList;
	}

}
