<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../view/theme/navbar.jsp"%>
<div class="container">
    <h3>
        Delete User
    </h3>
    <form method="post">
        <label class="form-label">Username:</label>
        <input class="form-control" name="id" value="${nv.id}" disabled>

        <label class="form-label">password:</label>
        <input class="form-control" type="text" name="password" value="${nv.password}" disabled>

        <label class="form-label">fullname:</label>
        <input class="form-control" name="fullname" value="${nv.fullname}" disabled>

        <label class="form-label">email:</label>
        <input class="form-control" name="email" value="${nv.email}" disabled>

        <label class="form-label">photo:</label>
        <input class="form-control" name="photo" value="${nv.photo}" disabled>

        <label class="form-label">hoạt động:</label>
        <input type="checkbox" name="activated" ${nv.activated?"checked":""} disabled >
        <br>
        <label class="form-label">admin:</label>
        <input type="checkbox" name="admin" ${nv.admin?"checked":""} disabled>
        <br>
        <button class="btn btn-success">Delete</button>
    </form>
</div>