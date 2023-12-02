<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../view/theme/navbar.jsp"%>
<div class="container">
    <h3>Quản Lý Video</h3>
    <a href="video-add">
        <button class="btn btn-success">Thêm Video</button>
    </a>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Ảnh nền</th>
            <th></th>
        </tr>
        <c:forEach items="${list}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.title}</td>
                <td>${u.poster}</td>
                <td>
                    <a href="video-update?id=${u.id}">
                        <button class="btn btn-outline-warning">Update</button>
                    </a>
                    <a href="video-delete?id=${u.id}">
                        <button class="btn btn-outline-danger">Delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
