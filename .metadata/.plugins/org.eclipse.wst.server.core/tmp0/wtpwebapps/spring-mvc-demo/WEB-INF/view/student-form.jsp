<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Student Registration Form</title>
</head>

<body>

	<form:form action="processForm" modelAttribute="student">
	
		First Name: <form:input path="firstName" />

		<br></br>

		Last Name: <form:input path="lastName" />

		<br></br>
		
		Country:
		
		<form:select path="country">
		
			<form:options items="${student.countryOptions}" />
			
		</form:select>

		<br></br>
		
		Favourite Language:
		
		<!-- <br></br>
		opciones cuando esta hard code 		
		Java <form:radiobutton path="favouriteLanguage" value="Java"/>
		C# <form:radiobutton path="favouriteLanguage" value="C#"/>
		PHP <form:radiobutton path="favouriteLanguage" value="PHP"/>
		Ruby <form:radiobutton path="favouriteLanguage" value="Ruby"/> -->
		
		<form:select path="favouriteLanguage">
		
			<form:options items="${student.favouriteLanguageOptions}" />
			
		</form:select>
		

		<br></br>
		
		Operating Systems:
		
		Linux<form:checkbox path="operatingSystems" value="Linux"/>
		Mac OS<form:checkbox path="operatingSystems" value="Mac"/>
		Ms Windows<form:checkbox path="operatingSystems" value="Microsoft Windows"/>
		
		
		<br></br>

		<input type="submit" value="Submit">

	</form:form>



</body>
</html>