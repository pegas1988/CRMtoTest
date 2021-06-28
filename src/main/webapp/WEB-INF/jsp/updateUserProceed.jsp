<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>UpdateUser</title>
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
    <div class="container mb-3 mt-3 w-50 p-3">
        <center>
            <h1>Update proceed...</h1>
            <p class="text-primary">Please fill in this form to update a User</p>
        </center>
        <div class="mb-3">
            <input type="text" class="form-control" placeholder="Please enter new name"
                   name="firstName" required>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" placeholder="Please enter new lastname"
                   name="lastName" required>
        </div>
        <div class="mb-3">
            <select class="form-select" aria-label="Choose the role" name="userRole">
                <option selected>Click here to show the list of roles</option>
                <option value="DOCTOR">The Doctor</option>
                <option value="MASTER">The Master</option>
                <option value="DESIGNER">The Designer</option>
                <option value="ADMIN">The GOD</option>
            </select>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control" placeholder="Enter Password" name="psw" required>
        </div>
        <div class="d-grid gap-2">
            <button name="Submit" value="Update" type="Submit" class="btn btn-primary">Update</button>
        </div>
    </div>
</form>
</body>
</html>