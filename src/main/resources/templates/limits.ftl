<#import "elements/environment.ftl" as macros> 

<@macros.environment> 
<form
	method="post"
	action="/limits"
	class="form-inline">
	<div class="form-row mt-5 mb-5">
	<!-- 	<label class="col-sm-2 col-form-label">Set new limit for country:</label> -->
		<label class="col-sm-5.5 col-form-label">Set new requests limit per minute for country:</label>
			<input
				type="hidden"
				name="_csrf"
				value="${_csrf.token}" />
			<div class="col-sm">
				<#include "elements/selectCountry.ftl">
			</div>
			<div class="col-sm">
				<input 
					type="text"
					name="limit"
					class="form-control"
					placeholder="limit of requests" />
			</div>
			<div class="col-sm">
				<button
					type="submit"
					class="btn btn-primary"
					name="setLimit">Set</button>
			</div>
	</div>
</form>

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
		<tbody id="limitsList">
		   <#list limits as limit>
			   <tr id="limitRow" data-id="${limit?counter}">
			      	<th scope="row">${limit?counter}</th>
	      			<td id="country"><#if limit.country?has_content>${limit.country}<#else>null</#if></td>
	      			<td id="limit"><#if limit.limit?has_content>${limit.limit}<#else>null</#if></td>
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