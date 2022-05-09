<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <% if (session.getAttribute("user")==null) {
    	response.sendRedirect("index.jsp");
    }
    %>
  <div align="right">
    Hello ${user.username}
    <a href="logout">Logout</a>
   </div>
  <div align="center">
    <h3>Add Book-Details</h3>
  </div>
  <div >
  <form action="add" method="post">
  <table>
    <tr>
    <td>Book Code:</td>
    <td><input type="text" name="bcode" /></td>
    </tr>
    <tr>
    <td>Book Name:</td>
    <td><input type="text" name="bname" /></td>
    </tr>
    <tr>
    <td>
    <label for="bauthor">Book Author:</label>
    </td>
    <td>
    <select name="bauthor" id="bauthor" >
          <option value="none" selected disabled >Select an Option</option>
          <c:forEach var="product" items="${products}" varStatus="ctr">
            <option value="${product.name }">${product.name }</option>
        </c:forEach>
    </select>
    </td>
    </tr>

    <tr>
        <td>
            Date:
        </td>
        <td>
            <input type="text" name="addedDate" value="${date}" readonly/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" value="Submit" />
            <input type="reset" value="Cancel" />
        </td>
    </tr>
  </table>
  </form>
  </div>


</body>
</html>