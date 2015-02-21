package com.baifeg.models.nav;

import static org.junit.Assert.*;

import org.junit.Test;

public class NavTest
{

	@Test
	public void test()
	{
		NavParser navXmlParser = new NavXmlParser();

		String navDir = System.getProperty("user.dir")
				+ "/test/unit/resource/conf/nav";
		Nav nav = navXmlParser.parse(navDir);
		assertEquals(3, nav.getSubMenus().size());
		Menu menu = nav.getMenuByUrl("/");

		System.out.println("======> " + menu.getCssClass());

	}

}
