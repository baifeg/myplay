package com.baifeg.models.nav;

import java.io.File;
import java.util.List;

import play.mvc.Http;
import play.mvc.Http.Request;

public class NavService
{
	private Nav nav;
	private NavParser navXmlParser = new NavXmlParser();

	public NavService(String navDir)
	{
		nav = navXmlParser.parse(navDir);
	}

	public NavService(List<File> navConfList)
	{
		nav = navXmlParser.parse(navConfList);
	}

	public NavService(File navConf)
	{
		nav = navXmlParser.parse(navConf);
	}

	public Nav getNav()
	{
		return nav;
	}

	public List<Menu> getTopMenuList()
	{
		return nav.getSubMenus();
	}

	public List<Menu> getLeftMenuList(String url)
	{
		Menu foundMenu = nav.getMenuByUrl(url);
		return foundMenu.getSubMenus();
	}

	public List<Menu> getLeftMenuList()
	{
		Request request = Http.Context.current().request();
		return getLeftMenuList(request.path());
	}
}
