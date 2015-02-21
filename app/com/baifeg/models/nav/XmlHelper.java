package com.baifeg.models.nav;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlHelper
{
	public static Document getDocument(File file)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try
		{
			db = dbf.newDocumentBuilder();
			return db.parse(file);
		} catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
			throw new ParseException("Failed to parse [" + file.getName()
					+ "].", e);
		}
	}
	
	public static <T> T newInstance(Class<T> clazz, Node node)
	{
		try
		{
			T newInstance = clazz.newInstance();

			if (!node.hasAttributes())
				return newInstance;

			NamedNodeMap attributes = node.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++)
			{
				Node item = attributes.item(i);
				String methodName = getSetMethodName(item.getNodeName());
				Method method = clazz.getMethod(methodName, String.class);
				method.invoke(newInstance, item.getNodeValue());
			}
			return newInstance;

		} catch (Exception e)
		{
			e.printStackTrace();
			throw new ParseException(e);
		}
	}

	private static String getSetMethodName(String nodeName)
	{
		String firstLetter = nodeName.substring(0, 1);
		String otherLetter = nodeName.substring(1);
		return "set" + firstLetter.toUpperCase() + otherLetter;
	}
}
