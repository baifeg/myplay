package com.baifeg.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class Table2Class
{
	private final static String JDBC_URL = "jdbc:h2:db/baifeg";
	private final static String PACKAGE = "com.baifeg.models.entity";
	private final static Configuration cfg = new Configuration(new Version("2.2"));
	private final static String TEMP_DIR_NAME = System.getProperty("user.dir") + "/tmp";
	private final static String ENTITY_FTL_NAME = "entity.ftl";

	static
	{
		cfg.setClassForTemplateLoading(Table2Class.class, "");
		try
		{
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		String[] tableNames = new String[] { "Company", "User", "Vm", "Hardware", "HardwarePrice",
				"VmMonthBill" };

		try
		{
			Connection conn = DriverManager.getConnection(JDBC_URL);

			DatabaseMetaData metaData = conn.getMetaData();

			ResultSet columns;
			for (String tableName : tableNames)
			{
				columns = metaData.getColumns(null, null, tableName.toUpperCase(), null);

				generateEntity(tableName, columns);
			}

			conn.close();
		} catch (SQLException | IOException e)
		{
			e.printStackTrace();
		}
		
	}

	private static void generateEntity(String tableName, ResultSet columns) throws SQLException,
			IOException
	{
		// init option
		boolean hasId = false;
		boolean useTimestamp = false;

		List<Column> columnList = new ArrayList<Column>();
		while (columns.next())
		{
			String columnName = columns.getString("COLUMN_NAME");
			int dataType = columns.getInt("DATA_TYPE");
			int nullable = columns.getInt("NULLABLE");
			Column column = new Column(columnName.toLowerCase());
			if (column.isId())
			{
				hasId = true;
				column.addAnnotation("@Id");
				column.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
			}
			else if (0 == nullable)
			{
				column.addAnnotation("@Column(nullable=false)");
			}
			else
			{
				column.addAnnotation("@Column");
			}

			switch (dataType)
			{
			case Types.BIGINT:
				column.setType("long");
				break;
			case Types.TINYINT:
				column.setType("int");
				break;
			case Types.INTEGER:
				column.setType("int");
				break;
			case Types.TIMESTAMP:
				column.setType("Timestamp");
				useTimestamp = true;
				break;
			default:
				column.setType("String");
				break;
			}

			columnList.add(column);

			generateJavaFile(tableName, columnList, hasId, useTimestamp);
		}
	}

	private static void generateJavaFile(String tableName, List<Column> columnList, boolean hasId,
			boolean useTimestamp) throws IOException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("package", PACKAGE);
		map.put("tableName", tableName);
		map.put("columns", columnList);
		map.put("hasId", hasId);
		map.put("useTimestamp", useTimestamp);

		String javaFileName = tableName + ".java";

		File javaFile = new File(TEMP_DIR_NAME + "/" + javaFileName);

		Writer out = new StringWriter();
		FileWriter fileWriter = new FileWriter(javaFile);
		try
		{
			cfg.getTemplate(ENTITY_FTL_NAME).process(map, out);
			fileWriter.write(out.toString());
			fileWriter.flush();
			out.flush();
		} catch (TemplateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			fileWriter.close();
		}
	}

	public static class Column
	{
		private String name;
		private String type;
		private List<String> annotations;

		public Column(String name)
		{
			this.name = name;
			annotations = new ArrayList<>();
		}

		public boolean isId()
		{
			return "id".equalsIgnoreCase(name);
		}

		public String getGetMethod()
		{
			return "get" + getProcessedName();
		}

		public String getSetMethod()
		{
			return "set" + getProcessedName();
		}

		private String getProcessedName()
		{
			return name.substring(0, 1).toUpperCase() + name.substring(1);
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
		 * @return the type
		 */
		public String getType()
		{
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(String type)
		{
			this.type = type;
		}

		/**
		 * @return the annotations
		 */
		public List<String> getAnnotations()
		{
			return annotations;
		}

		/**
		 * @param annotation
		 *            the annotations to set
		 */
		public void addAnnotation(String annotation)
		{
			if (!annotations.contains(annotation))
				annotations.add(annotation);
		}
	}
}
