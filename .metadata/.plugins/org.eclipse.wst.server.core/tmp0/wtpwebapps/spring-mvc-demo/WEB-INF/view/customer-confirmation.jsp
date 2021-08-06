<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
<title>Customer Confirmation</title>
</head>

<body>

The customer is confirmed: ${customer.firstName} ${customer.lastName}
<br><br>
Free passes: ${customer.freePasses}
<br><br>
Postal Code: ${customer.postalCode}
<br><br>
Course Code: ${customer.courseCode}

<!--  <br><br>

Country: ${student.country}

<br><br>

Favourite language: ${student.favouriteLanguage}

<br><br>

Operating Systems:

<ul>
	<c:forEach var="temp" items="${student.operatingSystems}">
	
		<li> ${temp} <li>
		
	</c:forEach>
</ul>-->

</body>
</html>