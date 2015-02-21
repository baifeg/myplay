package ${package};

import javax.persistence.Column;
import javax.persistence.Entity;
<#if hasId>
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
</#if>
<#if useTimestamp>
import java.sql.Timestamp;
</#if>
import javax.persistence.Table;

@Entity
@Table(name = "${tableName}")
public class ${tableName}
{
<#list columns as col>
	<#list col.getAnnotations() as ann>
	${ann}
	</#list>
	private ${col.getType()} ${col.getName()};
	
</#list>
<#list columns as col>
	public ${col.getType()} ${col.getGetMethod()}()
	{
		return ${col.getName()};
	}
	
	public void ${col.getSetMethod()}(${col.getType()} ${col.getName()})
	{
		this.${col.getName()} = ${col.getName()};
	}
	
</#list>
}
