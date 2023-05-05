<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="../getimage" method=post>

		image: <input type="number" name="imageId"> <input
			type="submit" value="display">

	</form>

	<%
	String imgFileName= (String)request.getAttribute("img");
	String imgId = (String)request.getAttribute("id");
%>


	<div>
		<table border="5px" style="width: 600px">
			<tr>
				<th>Id</th>
				<th>image</th>
			</tr>

			<%
		if(imgFileName!= "" && imgId != "")
		{
			
		
	%>
			<tr>
				<td><%=imgId %></td>
				<td><img src="C:/Users/sagar/eclipse-workspace/COURSE/src/main/webapp/IMAGES/<%=imgFileName%>"></td>
			</tr>

<%} %>

		</table>
	</div>

</body>
</html>