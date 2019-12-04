<#import "elements/environment.ftl" as macros> 

<@macros.environment> 
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">#</th>
			<th scope="col">Username</th>
			<th scope="col">Name</th>
			<th scope="col">Surname</th>
			<th scope="col">ID</th>
			<th scope="col">Country</th>
			<th scope="col">In blacklist</th>
			<th scope="col">Remove/Add to blacklist</th>
		</tr>
	</thead>
	<tbody>
	   <#list users as user> 
		  	<tr>
		      	<th scope="row">${user?counter}</th>
	      		<td><#if user.username?has_content>${user.username}<#else>null</#if></td>
      			<td><#if user.name?has_content>${user.name}<#else>null</#if></td>
      			<td><#if user.surname?has_content>${user.surname}<#else>null</#if></td>
      			<td><#if user.id?has_content>${user.id}<#else>null</#if></td>
      			<td><#if user.country?has_content>${user.country}<#else>null</#if></td>
				<td><#if user.inBlacklist?has_content>${user.inBlacklist?c}<#else>null</#if></td>
				<td>
					 <form 
				   		action="/blacklist"
						method="post">
						<input
							type="hidden"
							name="_csrf"
							value="${_csrf.token}" />
						<input
							type="hidden"
							name="username"
							value="${user.username}" />	
						<input
							type="hidden"
							name="inBlacklist"
							value="${user.inBlacklist?c}" />
							
						<#if user.inBlacklist>
							<button
								type="submit"
								class="btn btn-primary">Remove</button>
						<#else>
							<button
								type="submit"
								class="btn btn-primary">Add</button>
						</#if>
					</form>	
				</td>
		    </tr> 
		</#list>
	</tbody>
</table>
</@macros.environment> 