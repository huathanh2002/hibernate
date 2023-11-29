<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-3">
    <form>
        <div class="row">
            <div class="col-9">
                <input class="form-control" placeholder="Enter keyword to search" name="keyword" value="${param.keyword}">
            </div>
            <div class="col-3">
                <button class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>
    <ul class="list-unstyled row">
        <c:forEach items="${items}" var="vd">
            <li class="col-4">
                <a href="detail?id=${vd.id}" class="text-decoration-none">
                    <div class="card">
                        <c:if test="${vd.poster != null}">
                            <img class="card-img-top" src="${vd.poster}" alt="">
                        </c:if>
                        <c:if test="${vd.poster == null}">
                            <img class="card-img-top" src="https://img.myloview.com/stickers/social-media-video-icon-outline-social-media-video-vector-icon-for-web-design-isolated-on-white-background-700-247279065.jpg" alt="">
                        </c:if>
                        <div class="card-body">
                            <h5 class="card-title">${vd.title}</h5>
                        </div>
                    </div>
                </a>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${items.size() == 0}">
        <div class="mt-2">No video found</div>
    </c:if>
</div>