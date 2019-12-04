<#import "elements/environment.ftl" as macros>
<#include "elements/securityVars.ftl"> 
<#import "elements/notice.ftl" as macrosN>

<@macros.environment>
<#if !user??>
	<@macrosN.notice str="Please, sign in"/>
</#if>
<h5>Hello ${username!} !</h5>
<div>This is a simple service of providing credits</div>
</@macros.environment>  
