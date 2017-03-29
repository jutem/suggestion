<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script src="${ctx}/static/jquery-3.2.0.min.js"></script>

<html>
<body>
<h1>auto complete</h1>
<input id="searchInput" type="text" autocomplete="off" ajax="true" url="/findTopK?k=5" paramname="word"/>
</body>
</html>

<script>
    function autoComplete() {
        console.log("auto Complete start");
        var word = $("#searchInput").val();
        $.ajax({
            type : "get",
            url : "/findTopK",
            dataType : "json",
            data : {word : word, k : 5},
            success : function(data) {
                console.log(data);
            },
            error: function () {
                alert("服务器发送错误");
            }
        });
    }
</script>

