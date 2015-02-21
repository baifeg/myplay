package com.baifeg.play;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import play.Play;
import play.api.templates.Html;
import play.cache.Cache;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class FreemarkerPage
{
	private final static Configuration cfg = new Configuration(new Version("2.2"));
	private final static String FTL_DIR = "/views";

	static
	{
		cfg.setClassForTemplateLoading(FreemarkerPage.class, FTL_DIR);
		if (Play.isDev())
		{
			cfg.setTemplateUpdateDelay(0);
		}
	}

	@SafeVarargs
	public static Html render(String template, Entry<String, Object>... entries)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		for (Entry<String, Object> entry : entries)
		{
			map.put(entry.getKey(), entry.getValue());
		}

		return render(template, map);
	}

	public static Html render(String template, Map<String, Object> map)
	{
		Writer out = new StringWriter();
		map.put("cache", new Cache());
		try
		{
			cfg.getTemplate(template).process(map, out);
			out.flush();
		} catch (TemplateException | IOException e)
		{
			e.printStackTrace();
		}
		return Html.apply(out.toString());
	}
}
