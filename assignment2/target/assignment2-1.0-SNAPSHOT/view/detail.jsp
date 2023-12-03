<%@ include file="theme/navbar.jsp" %>
<div class="container mt-3">
    <div class="d-flex">
        <h3 style="flex:1">${video.title}</h3>

        <c:if test="${not empty sessionScope.username}">
            <div class="d-flex">
                <form method="post" action="detail">
                    <input type="hidden" name="action" value="like">
                    <input type="hidden" name="videoId" value="${video.id}">

                    <c:if test="${sessionScope.check == 'kook' }">
                        <button type="submit" class="btn btn-sm border-primary">Like</button>
                    </c:if>
                </form>
                <form method="post" action="detail">
                    <input type="hidden" name="action" value="unlike">
                    <input type="hidden" name="videoId" value="${video.id}">
                    <c:if test="${sessionScope.check == 'ok' }">
                        <button type="submit"  class="btn btn-sm border-primary">Unlike</button>
                    </c:if>
                    <c:if test="${sessionScope.check == 'kook' }">
                        <button type="submit" disabled class="btn btn-sm border-primary">Unlike</button>
                    </c:if>
                </form>
                    <%--                --%>
<%--                <form method="post" action="detail">--%>
<%--                    <input type="hidden" name="action" value="like">--%>
<%--                    <input type="hidden" name="videoId" value="${video.id}">--%>
<%--                    <button type="submit" class="btn btn-sm border-primary">Like</button>--%>

<%--                </form>--%>
<%--                <form method="post" action="detail">--%>
<%--                    <input type="hidden" name="action" value="unlike">--%>
<%--                    <input type="hidden" name="videoId" value="${video.id}">--%>
<%--                    <button type="submit" class="btn btn-sm border-primary">Unlike</button>--%>
<%--                </form>--%>
            </div>
        </c:if>
    </div>
    <iframe width="100%" height="600px" src="https://www.youtube.com/embed/${video.id}" allowfullscreen></iframe>
</div>
