<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../view/theme/navbar.jsp"%>
<div class="container">
    <h3>
        Thêm Video
    </h3>
    <form method="post">
        <label class="form-label">Id video:</label>
        <input class="form-control" name="id">

        <label class="form-label">Title video:</label>
        <input class="form-control"  name="title">

        <label class="form-label">Ảnh nền video:</label>
        <input class="form-control" name="poster">


        <button class="btn btn-success">Add</button>
    </form>
</div>