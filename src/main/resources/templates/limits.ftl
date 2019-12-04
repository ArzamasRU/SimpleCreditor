<#import "elements/environment.ftl" as macros> 

<@macros.environment> 
<div class="form-row mt-5 mb-5">
<!-- 	<label class="col-sm-2 col-form-label">Set new limit for country:</label> -->
	<label class="col-sm-4 col-form-label">Set new limit of requests per minute for country:</label>
		<form
			method="post"
			action="/limits"
			class="form-inline">
			<input
				type="hidden"
				name="_csrf"
				value="${_csrf.token}" />
			<div class="col-sm-4">
				<#include "elements/selectCountry.ftl">
			</div>
			<div class="col-sm-4">
				<input 
					type="text"
					name="limit"
					class="form-control"
					placeholder="limit of requests" />
			</div>
			<div class="col-sm-4">
			<button
				type="submit"
				class="btn btn-primary"
				name="setLimit">Set</button>
			</div>
		</form>
</div>

<#if limits??>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">#</th>
				<th scope="col">Country</th>
				<th scope="col">Limit</th>
				<th scope="col">Remove limit</th>
			</tr>
		</thead>
		<tbody>
		   <#list limits as limit>
			   <tr>
			      	<th scope="row">${limit?counter}</th>
	      			<td><#if limit.country?has_content>${limit.country}<#else>null</#if></td>
	      			<td><#if limit.limit?has_content>${limit.limit}<#else>null</#if></td>
					<td> 
						<form 
					  		action="/limits"
							method="post">
							<input
								type="hidden"
								name="_csrf"
								value="${_csrf.token}" />
							<input
								type="hidden"
								name="country"
								value="${limit.country}" />	
							<input
								type="hidden"
								name="limit"
								value="${limit.limit}" />
								
							<button
								type="submit"
								class="btn btn-primary"
								name="removeLimit">Remove</button>
						</form>	
					</td>
			    </tr> 
			</#list>
		</tbody>
	 </table>
</#if>
</@macros.environment> 