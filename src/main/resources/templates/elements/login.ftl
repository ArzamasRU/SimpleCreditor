<#macro login path isRegisterForm>
<form
	action="${path}"
	method="post">
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">User Name:</label>
		<div class="col-sm-6">
			<input
				type="text"
				name="username"
				class="form-control"
				placeholder="User name" />
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Password:</label>
		<div class="col-sm-6">
			<input
				type="password"
				name="password"
				class="form-control"
				placeholder="Password" />
		</div>
	</div>
	
	<#if isRegisterForm>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Name:</label>
			<div class="col-sm-6">
				<input
					type="text"
					name="name"
					class="form-control"
					placeholder="Name" />
			</div>
		</div> 
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Surname:</label>
			<div class="col-sm-6">
				<input
					type="text"
					name="surname"
					class="form-control"
					placeholder="Surname" />
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">Country:</label>
			<div class="col-sm-6">
				<#include "selectCountry.ftl">
			</div>
		</div>
	</#if>
	
	<input
		type="hidden"
		name="_csrf"
		value="${_csrf.token}" />
	<button
		class="btn btn-primary"
		type="submit">
		<#if isRegisterForm>Create<#else>Sign In</#if>
	</button>
	<#if !isRegisterForm>
		<a>or </a><a href="/registration">add new user</a>
	</#if>
</form>
</#macro>


