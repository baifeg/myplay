 <header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
  <div class="container">
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
      <ul class="nav navbar-nav">
      	<#list cache.get("topMenuList") as menu>
      	<#if menu.isActive()>
      	<li class="active">
      	<#else>
      	<li>
      	</#if>
      	  <a href="${menu.getUrl()}" class="${menu.getCssClass()}">${menu.getName()}</a>
      	</li>
      	</#list>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li>
          <a href="../about">About</a>
        </li>
      </ul>
    </nav>
  </div>
</header>