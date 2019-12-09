<select class="custom-select" name="filter">
    <option value="">Choose filter...</option>
    <#if admin> 
    	<option value="username">Username</option>
    </#if>
    <option value="name">Name</option>
    <option value="surname">Surname</option>
    <option value="userId">UserID</option>
    <option value="country">Country</option>
    <option value="term">Term (yyyy-mm-dd)</option>
    <option value="summ">Summ</option>
    <option value="date">Request time (yyyy-mm-dd)</option>
</select>
