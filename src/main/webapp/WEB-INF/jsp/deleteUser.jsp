<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 13.06.2021
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DeleteUserPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
        integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous"></script>

<form method="post">
    <div class="container m-3">
        <center>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">LastName</th>
                    <th scope="col">Password</th>
                    <th scope="col">UserRole</th>
                    <th scope="col">Email</th>
                    <th scope="col">ActionButton</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:forEach items="${list}" var="name">
                <tr>
                    <td><c:out value="${name.userID}"/></td>
                    <td><c:out value="${name.firstName}"/></td>
                    <td><c:out value="${name.lastName}"/></td>
                    <td><c:out value="${name.password}"/></td>
                    <td><c:out value="${name.userRole}"/></td>
                    <td><c:out value="${name.email}"/></td>
                    <td>
                        <div class="d-grid gap-2">
                            <input type="hidden" id="firstName" name="firstName" value=${name.firstName}/>
                            <input type="hidden" id="lastName" name="lastName" value=${name.lastName}/>
                            <button name="Submit" value="wtf" type="Submit" class="btn btn-primary">
                                Delete
                            </button>
                        </div>
                    </td>
                </tr>
                </c:forEach>
                </tr>

                </tbody>
            </table>
        </center>
    </div>
</form>
</body>
</html>
