<#import "elements/environment.ftl" as macros> 
<#include "elements/securityVars.ftl"> 

<@macros.environment> 
<form
	method="get"
	action="/requests"
	class="form-inline">
	<div class="form-group col-md-6 mt-5 mb-5">
		<#include "elements/selectFilter.ftl">
		<input
			type="text"
			name="filterValue"
			class="form-control"
			placeholder="${filterValue!}"/>
		<button
			type="submit"
			class="btn btn-primary ml-2">Search</button>
	</div>
</form>


<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">#</th>
			<th scope="col">Username</th>
			<th scope="col">Name</th>
			<th scope="col">Surname</th>
			<th scope="col">UserID</th>
			<th scope="col">Country</th>
			<th scope="col">Term</th>
			<th scope="col">Summ</th>
			<th scope="col">Request time</th>
		</tr>
	</thead>
	  <tbody id="requestsList">
	    <#list requests as request>
		    <tr id="requestRow" data-id="${request?counter}">
		      	<th scope="row">${request?counter}</th>
		      	<td><#if request.username?has_content>${request.username}<#else>null</#if></td>
      			<td><#if request.name?has_content>${request.name}<#else>null</#if></td>
      			<td><#if request.surname?has_content>${request.surname}<#else>null</#if></td>
      			<td><#if request.userId?has_content>${request.userId}<#else>null</#if></td>
      			<td><#if request.country?has_content>${request.country}<#else>null</#if></td>
      			<td id="term"><#if request.term?has_content>${request.term}<#else>null</#if></td>
      			<td id="summ"><#if request.summ?has_content>${request.summ}<#else>null</#if></td>
      			<td><#if request.date?has_content>${request.date}<#else>null</#if></td>
		    </tr> 
	 	</#list>
	  </tbody>
</table>
</@macros.environment> 