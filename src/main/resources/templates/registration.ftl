<#import "elements/environment.ftl" as	macros> 
<#import "elements/login.ftl" as macrosL> 

<@macros.environment>
<h5>
	<div class="mb-1">Add new user</div>
</h5>
<@macrosL.login path="/registration" isRegisterForm=true/> 
</@macros.environment>  