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
			<th scope="col">Term</th>
			<th scope="col">Summ</th>
			<th scope="col">Request time</th>
		</tr>
	</thead>
	  <tbody id="requestsList">
	    <#list requests as request>
		    <tr id="requestsRow" data-id="request.id}">
		      	<th scope="row">${request?counter}</th>
		      	<td><#if request.username?has_content>${request.username}<#else>null</#if></td>
      			<td><#if request.name?has_content>${request.name}<#else>null</#if></td>
      			<td><#if request.surname?has_content>${request.surname}<#else>null</#if></td>
      			<td><#if request.userId?has_content>${request.userId}<#else>null</#if></td>
      			<td><#if request.country?has_content>${request.country}<#else>null</#if></td>
      			<td><#if request.term?has_content>${request.term}<#else>null</#if></td>
      			<td><#if request.summ?has_content>${request.summ}<#else>null</#if></td>
      			<td><#if request.date?has_content>${request.date}<#else>null</#if></td>
		    </tr> 
	 	</#list>
	  </tbody>
</table>
</@macros.environment> 