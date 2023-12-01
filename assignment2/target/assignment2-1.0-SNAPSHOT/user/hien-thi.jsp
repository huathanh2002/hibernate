<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../view/theme/navbar.jsp"%>
<div class="container">
    <h3>Quản Lý User</h3>
    <a href="user-add">
        <button class="btn btn-success">Thêm User</button>
    </a>
    <table class="table table-bordered">
        <tr>
            <th>username</th>
            <th>password</th>
            <th>fullname</th>
            <th>email</th>
            <th>photo</th>
            <th>trạng thái</th>
            <th>Quyền</th>
            <th></th>
        </tr>
        <c:forEach items="${list}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.password}</td>
                <td>${u.fullname}</td>
                <td>${u.email}</td>
                <td>${u.photo}</td>
                <td>${u.activated? "hoạt động" : "không hoạt động"}</td>
                <td>${u.admin? "admin" : "user"}</td>
                <td>
                    <a href="user-update?id=${u.id}">
                        <button class="btn btn-outline-warning">Update</button>
                    </a>
                    <a href="user-delete?id=${u.id}">
                        <button class="btn btn-outline-danger">Delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
