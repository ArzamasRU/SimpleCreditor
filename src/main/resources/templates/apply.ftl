<#import "elements/environment.ftl" as macros> 
<#include "elements/securityVars.ftl">

<@macros.environment> 
<form 
	method="post"
	action="/apply">
	<input
		type="hidden"
		name="_csrf"
		value="${_csrf.token}" />
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Name:</label>
		<div class="col-sm-6">
			<input
				type="text"
				name="name"
				class="form-control"
				value="${name!}"
				disabled="true"/>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Surname:</label>
		<div class="col-sm-6">
			<input
				type="text"
				name="surname"
				class="form-control"
				value="${surname!}"
				disabled="true"/>
		</div>
	</div>
	<div class="form-group row"> 
		<label class="col-sm-2 col-form-label">ID:</label>
		<div class="col-sm-6">
			<input
				type="text"
				name="id"
				class="form-control"
				value="${id!}"
				disabled="true"/>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Country:</label>
		<div class="col-sm-6">
			<input
				type="text"
				name="country"
				class="form-control"
				value="${country!}"
				disabled="true"/>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Term:</label>
		<div class="col-sm-6">
			<input class="form-control" type="date" value="2020-01-01" name="term"/>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 col-form-label">Summ:</label>
		<div class="col-sm-6">
			<input
				type="text"
				name="summ"
				class="form-control"/>
		</div>
	</div>
	
	<button
		type="submit"
		class="btn btn-primary">Apply for credit</button>
</form>
</@macros.environment>