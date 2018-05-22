<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<table  style="margin: 0 auto;">
		<tr>
			<td colspan="3">
				<input id="${param.prefix}unitId" type="text" name="${param.prefix}Address.unitNo" placeholder="Unit / House No. Street"  required="required"  value="${param.unitNo}">
			</td>
		</tr>
		<tr>
			<td>
				<input id="${param.prefix}cityId" type="text" name="${param.prefix}Address.city" placeholder="City" style=" width: 30%;" required="required" value="${param.city}">
				<input id="${param.prefix}stateId" type="text" name="${param.prefix}Address.state" placeholder="Province/Region/State" style="width: 40%;margin-left: 10px;" required="required" value="${param.state}">
				<input id="${param.prefix}postalId" type="number" name="${param.prefix}Address.postalCode" max="99999" placeholder="Postal Code" required="required"  value="${param.postalCode}">
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<select id="${param.prefix}countryId" name="${param.prefix}Address.country"  required="required">
					<option value="">Please Select Country</option>
					<c:forEach var="country" items="${countryList}">
					   <option value="${country.key}" <c:if test="${param.country == country.key}">selected</c:if>>${country.value}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>