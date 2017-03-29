<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" href="${ctx}/static/css/jquery-ui.min.css">

<script src="${ctx}/static/js/jquery/jquery-3.2.0.min.js"></script>
<script src="${ctx}/static/js/jquery/jquery-ui.min.js"></script>

<html>
<body>
<h1>auto complete</h1>
<input id="searchInput" type="text" onkeyup="autoComplete()"/>
</body>
</html>

<script>
    function autoComplete() {
        var word = $("#searchInput").val();
        $.ajax({
            type : "get",
            url : "/findTopK",
            dataType : "json",
            data : {word : word, k : 5},
            success : function(data) {
                $("#searchInput").autocomplete({
                    source:data
                });
            },
            error: function () {
                alert("服务器发送错误");
            }
        });
    }
</script>

