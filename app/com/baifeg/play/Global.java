package com.baifeg.play;

import java.util.List;

import com.baifeg.models.nav.Menu;
import com.baifeg.models.nav.NavService;

import play.Application;
import play.GlobalSettings;
import play.cache.Cache;

public class Global extends GlobalSettings
{
	private static NavService navService;

	public Global()
	{
		String navDir = System.getProperty("user.dir") + "/conf/nav";
		navService = new NavService(navDir);
	}

	public static NavService getNavService()
	{
		return navService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see play.GlobalSettings#onStart(play.Application)
	 */
	@Override
	public void onStart(Application app)
	{
		List<Menu> topMenuList = navService.getTopMenuList();
		Cache.set("topMenuList", topMenuList);
		super.onStart(app);
	}

}
