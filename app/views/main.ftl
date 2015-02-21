<#macro page title>
<!DOCTYPE html>

<html>
    <head>
        <title>${title?html}</title>
        <link rel="stylesheet" media="screen" href="/assets/stylesheets/main.css">
        <link rel="stylesheet" media="screen" href="/assets/stylesheets/bootstrap.min.css">
        <link rel="stylesheet" media="screen" href="/assets/stylesheets/bootstrap-theme.min.css">
        <link rel="stylesheet" media="screen" href="/assets/stylesheets/main.css">
        <link rel="shortcut icon" type="image/png" href="/assets/images/favicon.png">
        <script src="/assets/javascripts/jquery-1.9.0.min.js" type="text/javascript"></script>
        <script src="/assets/javascripts/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
    	<#include "topNav.ftl"/>
        <#nested/>
    </body>
</html>
</#macro>