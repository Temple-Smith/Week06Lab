<%-- 
    Document   : shoppingList
    Created on : 24-Oct-2022
    Author     : Drew Temple-Smith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        Hello, ${username} <a href="ShoppingList?action=logout">Logout</a>
        <form action="" method="post">
            <h2>List</h2>
            Add item: <input type="text" name="item"> <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <form action="" method="post">
            <c:if test="${items.size() > 0}">
                <ul style="list-style-type: none; padding: 0; margin: 5px;">
                    <c:forEach var="item" items="${items}">
                        <li style="padding: 3px;"><input type="radio" name="item" value="<c:out value='${item}'/>"><c:out value="${item}"/></li>
                    </c:forEach>
                </ul>
                <input type="submit" value="Delete">
                <input type="hidden" name="action" value="delete">
            </c:if>
        </form>
        <c:if test="${error}">
            <p>Error: Item cannot be empty</p>
        </c:if>
    </body>
</html>
