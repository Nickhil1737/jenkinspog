<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books List</title>
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
    <h2>Books Listing</h2>
    </div>
    <div align="right">
        <a class="btn btn-primary" href="bookform">Add Book</a>
    </div>

    <div align="center">
        
        <table border="1">
          <tr>
            <th>Book Code</th>
            <th>Book Name</th>
            <th>Author</th>
            <th>Date Added</th>
            <th>Options</th>
          </tr>
          <c:forEach var="product" items="${products}" varStatus="ctr">
                <tr>
                    <td><c:out value="${product.bcode}" /></td>
                    <td><c:out value="${product.bname}" /></td>
                    <td><c:out value="${product.bauthor}" /></td>
                    <td><c:out value="${product.addedDate}" /></td>
                    <td>
                     <a href="/springhibimpl/edit?id=${product.bcode}">Edit</a>
         				&nbsp;&nbsp;&nbsp;
                     <a href="/springhibimpl/delete?id=${product.bcode}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
          
        </table>
        
    </div>
</body>
</html>