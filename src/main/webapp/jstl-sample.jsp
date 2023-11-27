<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="msg" value="こんにちは、タグライブラーーー"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSTLサンプル</title>
</head>
<body>
    <c:out value="こんにちは、タグライブラリ！！" /><br>
    ${msg}<br>
    <c:out value="${msg}" /><br>
    ${1+2}
    リクエストパラメータの値は、name=${param['name']}です。aaaa

    <c:forEach var="i" begin="1" end="5">
        <h${i}>こんにちは、JSP!</h>
    </c:forEach>
</body>
</html>