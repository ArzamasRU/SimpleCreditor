<#include "securityVars.ftl"> 
<#import "signOut.ftl" as macrosOut> 
<#import "signIn.ftl" as macrosIn>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand"
		href="/">SimpleCreditor</a>
	<button
		class="navbar-toggler"
		type="button"
		data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent"
		aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<div
		class="collapse navbar-collapse"
		id="navbarSupportedContent">
		<#if user??>
			<ul class="navbar-nav mr-auto">
				<#if admin>
					<li class="nav-item"><a
						class="nav-link"
						href="/requests">Requests</a></li>
					<li class="nav-item"><a
						class="nav-link"
						href="/blacklist">Blacklist</a></li>
					<li class="nav-item"><a
						class="nav-link"
						href="/limits">Limits</a></li>
				<#else>
					<li class="nav-item"><a
						class="nav-link"
						href="/requests">My requests</a></li>
					<li class="nav-item"><a
						class="nav-link"
						href="/apply">Apply for credit</a></li>
				</#if>
			</ul>
			<div class="navbar-text mr-5">${username!}</div>
			<@macrosOut.signOut/>
		<#else>
			<ul class="navbar-nav mr-auto"></ul>
			<@macrosIn.signIn/>
		</#if>
	</div>
</nav>