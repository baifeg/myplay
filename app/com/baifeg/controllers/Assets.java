package com.baifeg.controllers;

import controllers.AssetsBuilder;
import play.api.mvc.Action;
import play.api.mvc.AnyContent;

public class Assets
{
	static AssetsBuilder builder = new AssetsBuilder();
	
	public static Action<AnyContent> at(String path, String file)
	{
		return builder.at(path, file);
	}
}
