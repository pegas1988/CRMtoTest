<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 14.06.2021
  Time: 23:18
  To change this template use File | Settings | File Templates.
    --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GOD_Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<center>
    <h2>Choose the option, Master:</h2>
    <div class="row">
        <div class="col-sm-3">
            <div class="card m-3" style="width: 18rem;">
                <img src="CRM_war/.../icons/001_rats.jpg" class="card-img-top" alt="users"/>
                <div class="card-body">
                    <h5 class="card-title">Users</h5>
                    <p class="card-text">Choose the option:</p>
                    <a href="/CRM_war/add" style="width: 6rem;" class="btn btn-primary m-1">Create</a>
                    <a href="/CRM_war/CRM_war/updateUser" style="width: 6rem;" class="btn btn-primary m-1">Update</a>
                    <a href="/CRM_war/CRM_war/deleteUser" style="width: 6rem;" class="btn btn-primary m-1">Delete</a>
                    <a href="/CRM_war/list" style="width: 6rem;" class="btn btn-primary m-1">See all</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="card m-3" style="width: 18rem;">
                <img src="icons/materials.png" class="card-img-top" alt="Card image"/>
                <div class="card-body">
                    <h5 class="card-title">Materials</h5>
                    <p class="card-text">Choose the option:</p>
                    <a href="/CRM_war//createMaterial" style="width: 6rem;"
                       class="btn btn-primary m-1">Create</a>
                    <a href="/CRM_war/updateMaterial" style="width: 6rem;" class="btn btn-primary m-1">Update</a>
                    <a href="/CRM_war/deleteMaterial" style="width: 6rem;" class="btn btn-primary m-1">Delete</a>
                    <a href="/CRM_war/findAllMaterials" style="width: 6rem;" class="btn btn-primary m-1">See all</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="card m-3" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}icons/products.png" class="card-img-top" alt="..."/>
                <div class="card-body">
                    <h5 class="card-title">Products</h5>
                    <p class="card-text">Choose the option:</p>
                    <a href="/CRM_war/addProduct" style="width: 6rem;" class="btn btn-primary m-1">Create</a>
                    <a href="/CRM_war/updateProduct" style="width: 6rem;" class="btn btn-primary m-1">Update</a>
                    <a href="/CRM_war/deleteProduct" style="width: 6rem;" class="btn btn-primary m-1">Delete</a>
                    <a href="/CRM_war/findAllProducts" style="width: 6rem;" class="btn btn-primary m-1">See all</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="card m-3" style="width: 18rem;">
                <img src="${pageContext.request.contextPath}icon/leads.png" class="card-img-top" alt="..."/>
                <div class="card-body">
                    <h5 class="card-title">Leads</h5>
                    <p class="card-text">Choose the option:</p>
                    <a href="/CRM_war/addLead" style="width: 6rem;" class="btn btn-primary m-1">Creat</a>
                    <a href="/CRM_war/updateLead" style="width: 6rem;" class="btn btn-primary m-1">Update</a>
                    <a href="/CRM_war/deleteLead" style="width: 6rem;" class="btn btn-primary m-1">Delete</a>
                    <a href="/CRM_war/findAllLeads" style="width: 6rem;" class="btn btn-primary m-1">See all</a>
                </div>
            </div>
        </div>
    </div>


</center>
</form>
</body>
</html>
