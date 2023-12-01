<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../view/theme/navbar.jsp"%>
<div class="container">
    <h3>
        Thêm Mới User
    </h3>
    <form method="post">
        <label class="form-label">Username:</label>
        <input class="form-control" name="id">

        <label class="form-label">password:</label>
        <input class="form-control" type="password" name="password">

        <label class="form-label">fullname:</label>
        <input class="form-control" name="fullname">

        <label class="form-label">email:</label>
        <input class="form-control" name="email">

        <label class="form-label">photo:</label>
        <input class="form-control" name="photo">

        <label class="form-label">hoạt động:</label>
        <input type="checkbox" name="activated">
            <br>
        <label class="form-label">admin:</label>
        <input type="checkbox" name="admin">
        <br>
        <button class="btn btn-success">Add</button>
    </form>
</div>